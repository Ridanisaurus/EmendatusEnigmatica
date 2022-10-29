package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
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
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.SphereOreFeatureConfig;
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
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Optional;

@ObjectHolder(Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VanillaDepositProcessor implements IDepositProcessor {

    private JsonObject object;
    private VanillaDepositModel model;
    private OreFeature feature;
    private MaterialModel placeholder = new MaterialModel("", "", "", new ArrayList<>(), null, null, null, null);
    private Table<MaterialModel, StrataModel, Holder<ConfiguredFeature<OreConfiguration, ?>>> configured = HashBasedTable.create();
//	public static final DeferredRegister<Feature<?>> VANILLA_DEPOSIT_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Reference.MOD_ID);

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
        int maxYLevel = model.getConfig().getMaxYLevel();
        int minYLevel = model.getConfig().getMinYLevel();
        int baseline = minYLevel + maxYLevel / 2;
        int spread = maxYLevel - baseline;
        // TODO: [BUUZ] The whole ConfiguredFeature generation system seems to have changed, and I can't seem to figure out how to shift the below code to use the new Holder system
        if (WorldGenHelper.biomeCheck(event, model.getWhitelistBiomes(), model.getBlacklistBiomes())) {
            if (model.getConfig().getBlock() != null) {
                ResourceLocation blockResourceLocation = new ResourceLocation(model.getConfig().getBlock());
                Block oreBlock = ForgeRegistries.BLOCKS.getValue(blockResourceLocation);
                for (StrataModel stratum : EELoader.STRATA) {
                    if (model.getConfig().getFillerTypes().contains(stratum.getId())) {
                        Block stratumBlock = ForgeRegistries.BLOCKS.getValue(stratum.getFillerType());
                        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, getOreFeature(placeholder, stratum, model.getConfig().getChance(), model.getConfig().getSize(), baseline, spread, new BlockMatchTest(stratumBlock), oreBlock.defaultBlockState()));
//						VANILLA_DEPOSIT_FEATURES.register(new ResourceLocation(Reference.MOD_ID, model.getName()).toString(), () -> new VanillaOreFeature(OreFeatureConfig.CODEC, model));
                    }
                }
            } else if (model.getConfig().getMaterial() != null) {
                for (MaterialModel material : EELoader.MATERIALS) {
                    if (material.getId().equals(model.getConfig().getMaterial())) {
                        for (StrataModel stratum : EELoader.STRATA) {
                            if (model.getConfig().getFillerTypes().contains(stratum.getId())) {
                                Block stratumBlock = ForgeRegistries.BLOCKS.getValue(stratum.getFillerType());
                                BlockState oreBlockstate = EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get().defaultBlockState();
                                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, getOreFeature(material, stratum, model.getConfig().getChance(), model.getConfig().getSize(), baseline, spread, new BlockMatchTest(stratumBlock), oreBlockstate));
//								VANILLA_DEPOSIT_FEATURES.register(new ResourceLocation(Reference.MOD_ID, model.getName()).toString(), () -> new VanillaOreFeature(OreFeatureConfig.CODEC, model));
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public Feature<?> getFeature() {
        return feature.setRegistryName(Reference.MOD_ID, model.getName());
    }

    private Holder<PlacedFeature> getOreFeature(MaterialModel material, StrataModel stratum, int count, int size, int baseline, int spread, RuleTest filler, BlockState state) {
        var registered = configured.get(material, stratum);
        var placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(baseline), VerticalAnchor.absolute(spread));
        return PlacementUtils.register(model.getName(), registered, placement);
    }

    //	@SubscribeEvent5
//	public void featureRegistry(final RegistryEvent.Register<Feature<?>> registryEvent) {
//		registryEvent.getRegistry().register(new VanillaOreFeature(OreFeatureConfig.CODEC, model).setRegistryName(new ResourceLocation(Reference.MOD_ID, model.getName())));
//	}
    @Override
    public void setup() {
        if (model.getConfig().getBlock() != null) {
            ResourceLocation blockResourceLocation = new ResourceLocation(model.getConfig().getBlock());
            Block oreBlock = ForgeRegistries.BLOCKS.getValue(blockResourceLocation);
            for (StrataModel stratum : EELoader.STRATA) {
                if (model.getConfig().getFillerTypes().contains(stratum.getId())) {
                    Block stratumBlock = ForgeRegistries.BLOCKS.getValue(stratum.getFillerType());
                    configured.put(placeholder, stratum, FeatureUtils.register(model.getName(), feature, new OreConfiguration(new BlockMatchTest(stratumBlock), oreBlock.defaultBlockState(), model.getConfig().getSize())));
                }
            }
        } else if (model.getConfig().getMaterial() != null) {
            for (MaterialModel material : EELoader.MATERIALS) {
                if (material.getId().equals(model.getConfig().getMaterial())) {
                    for (StrataModel stratum : EELoader.STRATA) {
                        if (model.getConfig().getFillerTypes().contains(stratum.getId())) {
                            Block stratumBlock = ForgeRegistries.BLOCKS.getValue(stratum.getFillerType());
                            BlockState oreBlockstate = EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get().defaultBlockState();
                            configured.put(material, stratum, FeatureUtils.register(model.getName(), feature, new OreConfiguration(new BlockMatchTest(stratumBlock), oreBlockstate, model.getConfig().getSize())));
                        }
                    }
                    break;
                }
            }
        }
    }
}