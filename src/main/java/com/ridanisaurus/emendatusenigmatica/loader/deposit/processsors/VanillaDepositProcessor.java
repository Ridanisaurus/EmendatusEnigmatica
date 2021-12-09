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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Optional;

@ObjectHolder(Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VanillaDepositProcessor implements IDepositProcessor {

	private JsonObject object;
	private VanillaDepositModel model;
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
	}

	@Override
	public void setupOres(BiomeLoadingEvent event) {
		int maxYLevel = model.getConfig().getMaxYLevel();
		int minYLevel = model.getConfig().getMinYLevel();
		int baseline = minYLevel + maxYLevel / 2;
		int spread = maxYLevel - baseline;

		if (WorldGenHelper.biomeCheck(event, model.getWhitelistBiomes(), model.getBlacklistBiomes())) {
			if (model.getConfig().getBlock() != null) {
				ResourceLocation blockResourceLocation = new ResourceLocation(model.getConfig().getBlock());
				Block oreBlock = ForgeRegistries.BLOCKS.getValue(blockResourceLocation);
				for (StrataModel stratum : EELoader.STRATA) {
					if (model.getConfig().getFillerTypes().contains(stratum.getId())) {
						Block stratumBlock = ForgeRegistries.BLOCKS.getValue(stratum.getFillerType());
						event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, getOreFeature(model.getConfig().getChance(), model.getConfig().getSize(), baseline, spread, new BlockMatchRuleTest(stratumBlock), oreBlock.defaultBlockState()));
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
								event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, getOreFeature(model.getConfig().getChance(), model.getConfig().getSize(), baseline, spread, new BlockMatchRuleTest(stratumBlock), oreBlockstate));
//								VANILLA_DEPOSIT_FEATURES.register(new ResourceLocation(Reference.MOD_ID, model.getName()).toString(), () -> new VanillaOreFeature(OreFeatureConfig.CODEC, model));
							}
						}
						break;
					}
				}
			}
		}
	}

	private ConfiguredFeature<?, ?> getOreFeature(int count, int size, int baseline, int spread, RuleTest filler, BlockState state) {
		return WorldGenHelper.registerFeature(model.getName(), new VanillaOreFeature(OreFeatureConfig.CODEC, model)
				.configured(new OreFeatureConfig(filler, state, size))
				.decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(baseline, spread)))
				.squared()
				.count(count));
	}

	@SubscribeEvent
	public void featureRegistry(final RegistryEvent.Register<Feature<?>> registryEvent) {
		registryEvent.getRegistry().register(new VanillaOreFeature(OreFeatureConfig.CODEC, model).setRegistryName(new ResourceLocation(Reference.MOD_ID, model.getName())));
	}
}