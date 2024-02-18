package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.loader.ValidatorLogger;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.DepositValidators;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.*;
import com.ridanisaurus.emendatusenigmatica.util.FileHelper;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.*;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.DenseOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.DikeOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.GeodeOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.SphereOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
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
	public static final List<String> DEPOSIT_TYPES = new ArrayList<>();
	public static final List<IDepositProcessor> ACTIVE_PROCESSORS = new ArrayList<>();
	public static final List<String> DEPOSIT_IDS = new ArrayList<>();

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registry.FEATURE_REGISTRY, Reference.MOD_ID);
	public static final DeferredRegister<ConfiguredFeature<?,?>> ORE_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Reference.MOD_ID);
	public static final DeferredRegister<PlacedFeature> PLACED_ORE_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Reference.MOD_ID);
	private final EELoader loader;

	public EEDeposits(EELoader loader) {
		this.loader = loader;
	}

	public void initProcessors() {
		DEPOSIT_PROCESSORS.put(DepositType.VANILLA.getType(), VanillaDepositProcessor::new);
		DEPOSIT_PROCESSORS.put(DepositType.SPHERE.getType(), SphereDepositProcessor::new);
		DEPOSIT_PROCESSORS.put(DepositType.GEODE.getType(), GeodeDepositProcessor::new);
		DEPOSIT_PROCESSORS.put(DepositType.DIKE.getType(), DikeDepositProcessor::new);
		DEPOSIT_PROCESSORS.put(DepositType.DENSE.getType(), DenseDepositProcessor::new);
		DEPOSIT_PROCESSORS.put(DepositType.TEST.getType(), TestDepositProcessor::new);
	}
	public void load() {
		if (DEPOSIT_PROCESSORS.isEmpty()) initProcessors();

		if (DEPOSIT_TYPES.size() != DEPOSIT_PROCESSORS.size()) {
			DEPOSIT_TYPES.clear();
			DEPOSIT_TYPES.addAll(DEPOSIT_PROCESSORS.keySet());
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

		Map<Path, JsonObject> depositJsonDefinitionsMap = FileHelper.loadJsonsWithPaths(depositDir.toPath());
		Validator validator = new Validator("type");
		ValidatorLogger LOGGER = Validator.LOGGER;

		LOGGER.restartSpacer();
		LOGGER.info("Validating and registering data for: Deposits");
		depositJsonDefinitionsMap.forEach((path, element) -> {
			LOGGER.restartSpacer();
			if (!validator.validateObject(element, path, DepositValidators.get(element.get(validator.getName())))) {
				LOGGER.printSpacer(2);
				LOGGER.error("File \"%s\" is not going to be registered due to errors in it's validation.".formatted(path));
				return;
			}

			ACTIVE_PROCESSORS.add(DEPOSIT_PROCESSORS.get(element.get(validator.getName()).getAsString()).apply(element));
			DEPOSIT_IDS.add(element.get("registryName").getAsString());
		});

		LOGGER.restartSpacer();
		if (LOGGER.shouldLog)  {
			LOGGER.info("Finished validation and registration of data files.");
		} else {
			LOGGER.info("Finished registration of data files. Any validation errors that occurred, if any, have been hidden due to your current configuration file.");
		}
		LOGGER.printSpacer(0);

		ACTIVE_PROCESSORS.forEach(IDepositProcessor::load);
	}

	public void setup() {
		for (IDepositProcessor activeProcessor : ACTIVE_PROCESSORS) {
			if(activeProcessor.getType().equals(DepositType.VANILLA.getType())) {
				var model = ((VanillaDepositProcessor) activeProcessor).getVanillaModel();

				RegistryObject<VanillaOreFeature> vanillaOreFeature = FEATURES.register(model.getName(), () -> new VanillaOreFeature(model, this.loader.getDataRegistry()));
		        RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
				        () -> new ConfiguredFeature<>(vanillaOreFeature.get(), new NoneFeatureConfiguration())
		        );

				HeightRangePlacement placement = model.getPlacement().equals("uniform") ?
						HeightRangePlacement.uniform(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel())) :
						HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel()));

		        PLACED_ORE_FEATURES.register(
		                model.getName(), () -> new PlacedFeature(oreFeature.getHolder().get(),
						        model.getRarity().equals("common") ?
								         WorldGenHelper.commonOrePlacement(model.getPlacementChance(), placement) :
								         WorldGenHelper.rareOrePlacement(model.getPlacementChance(), placement)
				        )
		        );
			}
			if(activeProcessor.getType().equals(DepositType.SPHERE.getType())) {
				var model = ((SphereDepositProcessor) activeProcessor).getSphereModel();

				RegistryObject<SphereOreFeature> sphereOreFeature = FEATURES.register(model.getName(), () -> new SphereOreFeature(SphereOreFeatureConfig.CODEC, model, this.loader.getDataRegistry()));
				RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
						() -> new ConfiguredFeature<>(sphereOreFeature.get(), new SphereOreFeatureConfig(new MultiStrataRuleTest(model.getFillerTypes())))
				);

				HeightRangePlacement placement = model.getPlacement().equals("uniform") ?
						HeightRangePlacement.uniform(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel())) :
						HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel()));

				PLACED_ORE_FEATURES.register(model.getName(),
						() -> new PlacedFeature(oreFeature.getHolder().get(),
								model.getRarity().equals("common") ?
										WorldGenHelper.commonOrePlacement(model.getPlacementChance(), placement) :
										WorldGenHelper.rareOrePlacement(model.getPlacementChance(), placement)
						)
				);
			}
			if(activeProcessor.getType().equals(DepositType.GEODE.getType())) {
				var model = ((GeodeDepositProcessor) activeProcessor).getGeodeModel();

				RegistryObject<GeodeOreFeature> geodeOreFeature = FEATURES.register(model.getName(), () -> new GeodeOreFeature(GeodeOreFeatureConfig.CODEC, model, this.loader.getDataRegistry()));
				RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
						() -> new ConfiguredFeature<>(geodeOreFeature.get(), new GeodeOreFeatureConfig(new MultiStrataRuleTest(model.getFillerTypes())))
				);

				HeightRangePlacement placement = model.getPlacement().equals("uniform") ?
						HeightRangePlacement.uniform(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel())) :
						HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel()));

				PLACED_ORE_FEATURES.register(model.getName(),
						() -> new PlacedFeature(oreFeature.getHolder().get(),
								model.getRarity().equals("common") ?
										WorldGenHelper.commonOrePlacement(model.getPlacementChance(), placement) :
										WorldGenHelper.rareOrePlacement(model.getPlacementChance(), placement)
						)
				);
			}
			if(activeProcessor.getType().equals(DepositType.DIKE.getType())) {
				var model = ((DikeDepositProcessor) activeProcessor).getDikeModel();

				RegistryObject<DikeOreFeature> dikeOreFeature = FEATURES.register(model.getName(), () -> new DikeOreFeature(DikeOreFeatureConfig.CODEC, model, this.loader.getDataRegistry()));
				RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
						() -> new ConfiguredFeature<>(dikeOreFeature.get(), new DikeOreFeatureConfig(new MultiStrataRuleTest(model.getFillerTypes())))
				);

				HeightRangePlacement placement = model.getPlacement().equals("uniform") ?
						HeightRangePlacement.uniform(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel())) :
						HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel()));

				PLACED_ORE_FEATURES.register(model.getName(),
						() -> new PlacedFeature(oreFeature.getHolder().get(),
								model.getRarity().equals("common") ?
										WorldGenHelper.commonOrePlacement(model.getPlacementChance(), placement) :
										WorldGenHelper.rareOrePlacement(model.getPlacementChance(), placement)
						)
				);
			}
			if(activeProcessor.getType().equals(DepositType.DENSE.getType())) {
				var model = ((DenseDepositProcessor) activeProcessor).getDenseModel();

				RegistryObject<DenseOreFeature> denseOreFeature = FEATURES.register(model.getName(), () -> new DenseOreFeature(DenseOreFeatureConfig.CODEC, model, this.loader.getDataRegistry()));
				RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
						() -> new ConfiguredFeature<>(denseOreFeature.get(), new DenseOreFeatureConfig(new MultiStrataRuleTest(model.getFillerTypes())))
				);

				HeightRangePlacement placement = model.getPlacement().equals("uniform") ?
						HeightRangePlacement.uniform(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel())) :
						HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getMinYLevel()), VerticalAnchor.absolute(model.getMaxYLevel()));

				PLACED_ORE_FEATURES.register(model.getName(),
						() -> new PlacedFeature(oreFeature.getHolder().get(),
								model.getRarity().equals("common") ?
										WorldGenHelper.commonOrePlacement(model.getPlacementChance(), placement) :
										WorldGenHelper.rareOrePlacement(model.getPlacementChance(), placement)
						)
				);
			}
		}
	}

	public enum DepositType {
		VANILLA("emendatusenigmatica:vanilla_deposit"),
		SPHERE("emendatusenigmatica:sphere_deposit"),
		GEODE("emendatusenigmatica:geode_deposit"),
		DIKE("emendatusenigmatica:dike_deposit"),
		DENSE("emendatusenigmatica:dense_deposit"),
		TEST("emendatusenigmatica:test_deposit");

		private final String type;

		DepositType(String type) {
			this.type = type;
		}

		public String getType() {
			return this.type;
		}
	}

	public void finalize(IEventBus eventBus) {
		FEATURES.register(eventBus);
		ORE_FEATURES.register(eventBus);
		PLACED_ORE_FEATURES.register(eventBus);
	}
}
