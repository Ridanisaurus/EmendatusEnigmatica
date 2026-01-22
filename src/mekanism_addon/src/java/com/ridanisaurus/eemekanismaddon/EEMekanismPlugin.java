package com.ridanisaurus.eemekanismaddon;

import com.ridanisaurus.eemekanismaddon.datagen.LangGen;
import com.ridanisaurus.eemekanismaddon.extensions.GasExtension;
import com.ridanisaurus.eemekanismaddon.extensions.MekanismMaterialExtension;
import com.ridanisaurus.eemekanismaddon.registry.EEMekanismRegistrar;
import com.ridanisaurus.eemekanismaddon.validators.CoolantColorValidator;
import com.ridanisaurus.emendatusenigmatica.api.BasicEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.config.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.ModelLoader;
import com.ridanisaurus.emendatusenigmatica.plugin.extensions.ModelExtension;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialColorsModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.ProcessedTypesContainValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.ProcessedTypesValidator;
import com.ridanisaurus.eemekanismaddon.validators.ChemicalColorValidator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@EmendatusPluginReference(modId = EEMekanismAddon.MOD_ID, name = "mekanism-plugin")
public class EEMekanismPlugin extends BasicEmendatusPlugin {
    public static ModConfigSpec.BooleanValue disableOsmium = null;

    @Override
    public void setup() {
        ProcessedTypesValidator.TYPES.addAll(List.of(
            "infuse_type",
            "gas",
            "slurry",
            "crystal",
            "shard",
            "clump",
            "dirty_dust"
        ));

        MaterialColorsModel.VALIDATION_MANAGER
            .addValidator("chemicalColor",      new ChemicalColorValidator())
            .addValidator("hotCoolantColor",    new CoolantColorValidator())
            .addValidator("gasColor",           new DeprecatedFieldValidator("chemicalColor"));

        MaterialModel.VALIDATION_MANAGER
            .addValidator("gas", new ProcessedTypesContainValidator("gas", GasExtension.VALIDATION_MANAGER.getAsValidator(false)));

        ModelLoader.registerExtension(new ModelExtension<>(MekanismMaterialExtension.CODEC, MaterialModel.class, EEMekanismPlugin.class));
    }

    /**
     * @param ctx Config Creation Context
     */
    @Override
    public void extendConfig(ConfigCreationContext ctx) {
        if (!ctx.isStartup()) return;
        disableOsmium = ctx.getBuilder()
            .comment("Determines if Mekanism Osmium ore generation should be disabled.")
            .translation("ee_mekanism.config.disable_osmium_ore")
            .define("disableOsmiumOre",true);
    }

	@Override
	public void registerMinecraft(EmendatusDataRegistry registry) {
        for (MekanismMaterialExtension material : registry.<MekanismMaterialExtension>getMaterialExtensions(EEMekanismPlugin.class)) {
            var types = material.getOriginalModel().getProcessedTypes();
            if (types.contains("slurry"))
                EEMekanismRegistrar.registerSlurries(material);

            if (types.contains("crystal"))
                EEMekanismRegistrar.registerCrystals(material);

            if (types.contains("shard"))
                EEMekanismRegistrar.registerShards(material);

            if (types.contains("clump"))
                EEMekanismRegistrar.registerClumps(material);

            if (types.contains("dirty_dust"))
                EEMekanismRegistrar.registerDirtyDusts(material);

            if (types.contains("gas"))
                EEMekanismRegistrar.registerGases(material);

            if (types.contains("infuse_type"))
                EEMekanismRegistrar.registerInfuseTypes(material);
        }
	}

    /**
     * @param generator      DataGenerator to register data providers to.
     * @param providers      Vanilla Registry Lookup for use with vanilla generators that require it.
     * @param registry       Emendatus Enigmatica registry with all data parsed from the configuration files.
     * @param customRegistry CustomRegistry object specified in the annotation, or null if {@link Void}
     */
    @Override
    public void registerDynamicDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, EmendatusDataRegistry registry, Void customRegistry) {
        generator.addProvider(true, new LangGen(generator, registry));
//        generator.addProvider(true, new EEMekanismDataGen.ItemModels(generator, registry));
//        generator.addProvider(true, new EEMekanismDataGen.Lang(generator, registry));
//        generator.addProvider(true, new EEMekanismDataGen.ItemTags(generator, registry));
//        generator.addProvider(true, new EEMekanismDataGen.SlurryTags(generator, registry));
//        generator.addProvider(true, new EEMekanismDataGen.Recipes(generator, registry));;
    }

    /**
     * @param registry DCCreationContext used to register configs and check for compatibility.
     */
    @Override
    public void provideDefaultConfiguration(DCCreationContext registry) {
        super.provideDefaultConfiguration(registry);
    }
}