package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.VanillaOreFeature;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ObjectHolder(Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VanillaDepositProcessor implements IDepositProcessor {

    private final List<OreConfiguration.TargetBlockState> ORE_LIST = new ArrayList<>();
    private JsonObject object;
    private VanillaDepositModel model;
    private OreFeature feature;
    private Holder<ConfiguredFeature<OreConfiguration, ?>> configureded;

    public VanillaDepositProcessor(JsonObject object) {
        this.object = object;
    }

    @Override
    public void load() {
        Optional<Pair<VanillaDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(VanillaDepositModel.CODEC).apply(object).result();
        if (!result.isPresent()) {
            return;
        }
        model = result.get().getFirst();
        feature = new VanillaOreFeature(OreConfiguration.CODEC, model);
    }

    @Override
    public void setupOres(BiomeLoadingEvent event) {
        if (WorldGenHelper.biomeCheck(event, model.getWhitelistBiomes(), model.getBlacklistBiomes())) {
            Holder<ConfiguredFeature<OreConfiguration, ?>> oreFeature = getOreFeatureZ();
            HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
            var placed = PlacementUtils.register(model.getName(), oreFeature, placement);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placed);
        }
    }

    @Override
    public Feature<?> getFeature() {
        return feature.setRegistryName(model.getName());
    }

    private Holder<ConfiguredFeature<OreConfiguration, ?>> getOreFeatureZ() {
        return configureded;
    }

    @Override
    public void setup() {
        if (model.getConfig().getBlock() != null) {
            ResourceLocation blockResourceLocation = new ResourceLocation(model.getConfig().getBlock());
            Block oreBlock = ForgeRegistries.BLOCKS.getValue(blockResourceLocation);
            for (StrataModel stratum : EELoader.STRATA) {
                if (model.getConfig().getFillerTypes().contains(stratum.getId())) {
                    Block stratumBlock = ForgeRegistries.BLOCKS.getValue(stratum.getFillerType());
                    ORE_LIST.add(OreConfiguration.target(new BlockMatchTest(stratumBlock), oreBlock.defaultBlockState()));
                }
            }
        } else if (model.getConfig().getMaterial() != null) {
            for (MaterialModel material : EELoader.MATERIALS) {
                if (material.getId().equals(model.getConfig().getMaterial())) {
                    for (StrataModel stratum : EELoader.STRATA) {
                        if (model.getConfig().getFillerTypes().contains(stratum.getId())) {
                            Block stratumBlock = ForgeRegistries.BLOCKS.getValue(stratum.getFillerType());
                            BlockState oreBlockstate = EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get().defaultBlockState();
                            ORE_LIST.add(OreConfiguration.target(new BlockMatchTest(stratumBlock), oreBlockstate));
                        }
                    }
                    break;
                }
            }
        }
        // TODO: [RID] Fix Chance to be a Float instead of an INT and then add it to the OreConfiguration
        configureded = FeatureUtils.register(model.getName(), feature, new OreConfiguration(ORE_LIST, model.getConfig().getSize()));
    }
}