package com.ridanisaurus.eemekanismaddon;

import com.ridanisaurus.eemekanismaddon.datagen.LangGen;
import com.ridanisaurus.eemekanismaddon.extensions.MekanismMaterialExtension;
import com.ridanisaurus.eemekanismaddon.registry.EEMekanismDataRegistry;
import com.ridanisaurus.eemekanismaddon.registry.EEMekanismRegistrar;
import com.ridanisaurus.eemekanismaddon.validators.CoolantColorValidator;
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.loader.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.datagen.EEDataGenerator;
import com.ridanisaurus.emendatusenigmatica.loader.EEModelExtension;
import com.ridanisaurus.emendatusenigmatica.loader.SetupContext;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialColorsModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.ProcessedTypesValidator;
import com.ridanisaurus.eemekanismaddon.validators.ChemicalColorValidator;
import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@EmendatusPluginReference(modId = EEMekanismAddon.MOD_ID, name = "mekanism-plugin")
public class EEMekanismPlugin implements IEEPlugin<EEMekanismDataRegistry> {
    public static ModConfigSpec.BooleanValue disableOsmium = null;
    private DataRegistry vanillaRegistry = null;

    @Override
    public void setup(SetupContext ctx) {
        vanillaRegistry = ctx.pluginLoader().getRegistry(VanillaPlugin.class);

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

        ctx.modelLoader().registerModelExtension(new EEModelExtension<>(
            this.getClass(),
            "chemicals",
            VanillaPlugin.MATERIAL_DEFINITION,
            MekanismMaterialExtension.CODEC,
            MekanismMaterialExtension.VALIDATION_MANAGER,
            (model, extended, oRegistry, registry) -> {
                registry.registerExtendedMaterial(model.getId(), extended);
            }
        ));
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
	public void register(EEMekanismDataRegistry registry) {
        registry.getExtensions().forEach((id, extension) -> {
            var material = vanillaRegistry.getMaterialModel(id);
            var types = material.getProcessedTypes();

            if (types.contains("crystal"))
                EEMekanismRegistrar.registerCrystals(material);

            if (types.contains("shard"))
                EEMekanismRegistrar.registerShards(material);

            if (types.contains("clump"))
                EEMekanismRegistrar.registerClumps(material);

            if (types.contains("dirty_dust"))
                EEMekanismRegistrar.registerDirtyDusts(material);

            if (types.contains("slurry"))
                EEMekanismRegistrar.registerSlurries(material, extension);

            if (types.contains("gas"))
                EEMekanismRegistrar.registerGases(material, extension);

            if (types.contains("infuse_type"))
                EEMekanismRegistrar.registerInfuseTypes(material, extension);
        });
	}

    @Override
    public void registerDynamicDataGen(EEDataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, EEMekanismDataRegistry registry) {
        generator.addProvider(true, new LangGen(generator, registry, vanillaRegistry));
        //TODO: Reimplement generators
//        generator.addProvider(true, new EEMekanismDataGen.ItemModels(generator, registry));
//        generator.addProvider(true, new EEMekanismDataGen.Lang(generator, registry));
//        generator.addProvider(true, new EEMekanismDataGen.ItemTags(generator, registry));
//        generator.addProvider(true, new EEMekanismDataGen.SlurryTags(generator, registry));
//        generator.addProvider(true, new EEMekanismDataGen.Recipes(generator, registry));;
    }

    @Override
    public void provideDefaultConfiguration(DCCreationContext registry) {
        //TODO: Implement DefaultConfigs
    }
}