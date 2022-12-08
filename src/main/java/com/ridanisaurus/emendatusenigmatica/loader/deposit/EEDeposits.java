package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.GeodeDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.SphereDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.VanillaDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.util.FileHelper;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.GeodeOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.SphereOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.VanillaOreFeature;
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
	public static final List<IDepositProcessor> ACTIVE_PROCESSORS = new ArrayList<>();

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
	}
	public void load() {
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

		ArrayList<JsonObject> depositJsonDefinitions = FileHelper.loadFilesAsJsonObjects(depositDir);

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

	public void setup() {
		for (IDepositProcessor activeProcessor : ACTIVE_PROCESSORS) {
			if(activeProcessor.getType().equals(DepositType.VANILLA.getType())) {
				var model = ((VanillaDepositProcessor) activeProcessor).getVanillaModel();
				if (model.getConfig().getChance() < 1 || model.getConfig().getChance() > 100) throw new IllegalArgumentException("Chance for " + model.getName() + " is out of Range [1-100]");
				RegistryObject<VanillaOreFeature> vanillaOreFeature = FEATURES.register(model.getName(), () -> new VanillaOreFeature(model, this.loader.getRegistry()));
		        RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
				        () -> new ConfiguredFeature<>(vanillaOreFeature.get(), new NoneFeatureConfiguration())
		        );

				HeightRangePlacement placement = model.getConfig().getPlacement().equals("uniform") ?
						HeightRangePlacement.uniform(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel())) :
						HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));

		        PLACED_ORE_FEATURES.register(
		                model.getName(), () -> new PlacedFeature(oreFeature.getHolder().get(),
						        model.getConfig().getRarity().equals("common") ?
								         WorldGenHelper.commonOrePlacement(model.getConfig().getChance(), placement) :
								         WorldGenHelper.rareOrePlacement(model.getConfig().getChance(), placement)
				        )
		        );
			}
			if(activeProcessor.getType().equals(DepositType.SPHERE.getType())) {
				var model = ((SphereDepositProcessor) activeProcessor).getSphereModel();
				if (model.getConfig().getChance() < 1 || model.getConfig().getChance() > 100) throw new IllegalArgumentException("Chance for " + model.getName() + " is out of Range [1-100]");
				RegistryObject<SphereOreFeature> sphereOreFeature = FEATURES.register(model.getName(), () -> new SphereOreFeature(SphereOreFeatureConfig.CODEC, model, this.loader.getRegistry()));
				RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
						() -> new ConfiguredFeature<>(sphereOreFeature.get(), new SphereOreFeatureConfig(new MultiStrataRuleTest(model.getConfig().getFillerTypes())))
				);
				HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
				PLACED_ORE_FEATURES.register(model.getName(),
						() -> new PlacedFeature(oreFeature.getHolder().get(), WorldGenHelper.rareOrePlacement(model.getConfig().getChance(), placement))
				);
			}
			if(activeProcessor.getType().equals(DepositType.GEODE.getType())) {
				var model = ((GeodeDepositProcessor) activeProcessor).getGeodeModel();
				if (model.getConfig().getChance() < 1 || model.getConfig().getChance() > 100) throw new IllegalArgumentException("Chance for " + model.getName() + " is out of Range [1-100]");
				RegistryObject<GeodeOreFeature> geodeOreFeature = FEATURES.register(model.getName(), () -> new GeodeOreFeature(GeodeOreFeatureConfig.CODEC, model, this.loader.getRegistry()));
				RegistryObject<ConfiguredFeature<?, ?>> oreFeature = ORE_FEATURES.register(model.getName(),
						() -> new ConfiguredFeature<>(geodeOreFeature.get(), new GeodeOreFeatureConfig(new MultiStrataRuleTest(model.getConfig().getFillerTypes())))
				);
				HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
				PLACED_ORE_FEATURES.register(model.getName(),
						() -> new PlacedFeature(oreFeature.getHolder().get(), WorldGenHelper.rareOrePlacement(model.getConfig().getChance(), placement))
				);
			}
		}
	}

	private enum DepositType {
		VANILLA("emendatusenigmatica:vanilla_deposit"),
		SPHERE("emendatusenigmatica:sphere_deposit"),
		GEODE("emendatusenigmatica:geode_deposit");

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
