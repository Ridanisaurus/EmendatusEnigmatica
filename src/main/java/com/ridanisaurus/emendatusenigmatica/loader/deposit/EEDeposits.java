package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.GeodeDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.SphereDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.VanillaDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.FileIOHelper;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.GeodeOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.SphereOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.VanillaOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.GeodeOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.SphereOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class EEDeposits {
	public static final Map<String, Function<JsonObject, IDepositProcessor>> DEPOSIT_PROCESSORS = new HashMap<>();
	public static final List<IDepositProcessor> ACTIVE_PROCESSORS = new ArrayList<>();

	public static final DeferredRegister<ConfiguredFeature<?,?>> ORE_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Reference.MOD_ID);
	public static final DeferredRegister<PlacedFeature> PLACED_ORE_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Reference.MOD_ID);

	private static final List<OreConfiguration.TargetBlockState> ORE_LIST = new ArrayList<>();

	public static void initProcessors() {
		DEPOSIT_PROCESSORS.put("emendatusenigmatica:vanilla_deposit", VanillaDepositProcessor::new);
		DEPOSIT_PROCESSORS.put("emendatusenigmatica:sphere_deposit", SphereDepositProcessor::new);
		DEPOSIT_PROCESSORS.put("emendatusenigmatica:geode_deposit", GeodeDepositProcessor::new);
	}

	public static void load() {
		if (DEPOSIT_PROCESSORS.isEmpty()) {
			initProcessors();
		}

		Path configDir = FMLPaths.CONFIGDIR.get().resolve("emendatusenigmatica/");

		// Check if the folder exists
		if (!configDir.toFile().exists() && configDir.toFile().mkdirs()) {
			EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/");
		}

		File depositDir = configDir.resolve("deposit/").toFile();
		if (!depositDir.exists() && depositDir.mkdirs()) {
			EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/deposit/");
		}

		ArrayList<JsonObject> depositJsonDefinitions = FileIOHelper.loadFilesAsJsonObjects(depositDir);

		for (JsonObject depositJsonDefinition : depositJsonDefinitions) {
			if (!depositJsonDefinition.has("type")) {
				continue;
			}
			String type = depositJsonDefinition.get("type").getAsString();
			Function<JsonObject, IDepositProcessor> processor = DEPOSIT_PROCESSORS.getOrDefault(type, null);
			if (processor == null) {
				continue;
			}

			ACTIVE_PROCESSORS.add(processor.apply(depositJsonDefinition));
		}

		for (IDepositProcessor activeProcessor : ACTIVE_PROCESSORS) {
			activeProcessor.load();
		}
	}

	public static void setup() {
		for (IDepositProcessor activeProcessor : ACTIVE_PROCESSORS) {
			if(activeProcessor.getSphereModel() != null) {
				var model = activeProcessor.getSphereModel();
				RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
						() -> new ConfiguredFeature<>(new SphereOreFeature(SphereOreFeatureConfig.CODEC, model), new SphereOreFeatureConfig(new MultiStrataRuleTest(model.getConfig().getFillerTypes())))
				);
				HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
				RegistryObject<PlacedFeature> orePlacedFeature = PLACED_ORE_FEATURES.register(model.getName(),
						() -> new PlacedFeature(oreFeature.getHolder().get(), WorldGenHelper.commonOrePlacement((int) model.getConfig().getChance(), placement))
				);
			}
			if(activeProcessor.getGeodeModel() != null) {
				var model = activeProcessor.getGeodeModel();
				RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
						() -> new ConfiguredFeature<>(new GeodeOreFeature(GeodeOreFeatureConfig.CODEC, model), new GeodeOreFeatureConfig(new MultiStrataRuleTest(model.getConfig().getFillerTypes())))
				);
				HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
				RegistryObject<PlacedFeature> orePlacedFeature = PLACED_ORE_FEATURES.register(model.getName(),
						() -> new PlacedFeature(oreFeature.getHolder().get(), WorldGenHelper.commonOrePlacement((int) model.getConfig().getChance(), placement))
				);
			}
			if(activeProcessor.getVanillaModel() != null) {
				var model = activeProcessor.getVanillaModel();
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

		        RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(
		                model.getName(), () -> new ConfiguredFeature<>(new VanillaOreFeature(OreConfiguration.CODEC, model), new OreConfiguration(ORE_LIST, model.getConfig().getSize()))
		        );
		        HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
		        RegistryObject<PlacedFeature> orePlacedFeature = PLACED_ORE_FEATURES.register(
		                model.getName(), () -> new PlacedFeature(oreFeature.getHolder().get(), WorldGenHelper.commonOrePlacement(model.getConfig().getChance(), placement))
		        );
			}
		}
	}

	public static void finalize(IEventBus eventBus) {
		ORE_FEATURES.register(eventBus);
		PLACED_ORE_FEATURES.register(eventBus);
	}
}
