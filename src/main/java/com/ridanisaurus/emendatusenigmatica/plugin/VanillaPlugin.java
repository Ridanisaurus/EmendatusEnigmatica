package com.ridanisaurus.emendatusenigmatica.plugin;

import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.loader.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCDataBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.EEDataGenerator;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.LangGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.LootGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.RecipesGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.BlockModelsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.BlockStatesGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.tags.BlockHarvestLevelTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.tags.BlockHarvestToolTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.tags.BlockTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.fluid.FluidModelsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.fluid.FluidTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.item.ItemModelsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.item.ItemTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.world.BiomeTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.world.NeoFeatureGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.world.OreFeatureGen;
import com.ridanisaurus.emendatusenigmatica.loader.EEModelDefinition;
import com.ridanisaurus.emendatusenigmatica.loader.EEModelExtension;
import com.ridanisaurus.emendatusenigmatica.loader.SetupContext;
import com.ridanisaurus.emendatusenigmatica.plugin.compat.emi.EEEMIPlugin;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.*;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import dev.emi.emi.api.EmiRegistry;
import net.minecraft.core.HolderLookup;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@EmendatusPluginReference(modId = Reference.MOD_ID, name = "vanilla-plugin")
public class VanillaPlugin implements IEEPlugin<DataRegistry> {
    public static final EEModelDefinition<MaterialModel, DataRegistry> MATERIAL_DEFINITION = new EEModelDefinition<>(
        VanillaPlugin.class,
        "Material",
        "material",
        MaterialModel.CODEC,
        MaterialModel.VALIDATION_MANAGER,
        MaterialModel::register
    );

    public static final EEModelDefinition<StrataModel, DataRegistry> STRATA_DEFINITION = new EEModelDefinition<>(
        VanillaPlugin.class,
        "Strata",
        "strata",
        StrataModel.CODEC,
        StrataModel.VALIDATION_MANAGER,
        StrataModel::register
    );

    public static final EEModelDefinition<DepositModel, DataRegistry> DEPOSIT_DEFINITION = new EEModelDefinition<>(
        VanillaPlugin.class,
        "Deposit",
        "deposit",
        DepositModel.MODEL_CODEC,
        DepositModel.VALIDATION_MANAGER,
        DepositModel::register
    );


    /**
     * This method is used to make any necessary changes to other plugins,
     * register your models and anything else your plugin requires before EE itself starts.
     */
    @Override
    public void setup(SetupContext ctx) {
        EEEMIPlugin.provideDataRegistry(ctx.pluginLoader().getRegistry(getClass()));
        var loader = ctx.modelLoader();
        loader.registerDefinition(STRATA_DEFINITION);
        loader.registerDefinition(MATERIAL_DEFINITION);
        loader.registerDefinition(DEPOSIT_DEFINITION);

        loader.registerModelExtension(new EEModelExtension<>(
            this.getClass(),
            "vanilla",
            DEPOSIT_DEFINITION,
            VanillaDepositModel.CODEC,
            VanillaDepositModel.VALIDATION_MANAGER,
            (model, extModel, reg, __) -> extModel.register(reg)
        ));

        loader.registerModelExtension(new EEModelExtension<>(
            this.getClass(),
            "sphere",
            DEPOSIT_DEFINITION,
            SphereDepositModel.CODEC,
            SphereDepositModel.VALIDATION_MANAGER,
            (model, extModel, reg, __) -> extModel.register(reg)
        ));

        loader.registerModelExtension(new EEModelExtension<>(
            this.getClass(),
            "geode",
            DEPOSIT_DEFINITION,
            GeodeDepositModel.CODEC,
            GeodeDepositModel.VALIDATION_MANAGER,
            (model, extModel, reg, __) -> extModel.register(reg)
        ));

        loader.registerModelExtension(new EEModelExtension<>(
            this.getClass(),
            "dike",
            DEPOSIT_DEFINITION,
            DikeDepositModel.CODEC,
            DikeDepositModel.VALIDATION_MANAGER,
            (model, extModel, reg, __) -> extModel.register(reg)
        ));

        loader.registerModelExtension(new EEModelExtension<>(
            this.getClass(),
            "dense",
            DEPOSIT_DEFINITION,
            DenseDepositModel.CODEC,
            DenseDepositModel.VALIDATION_MANAGER,
            (model, extModel, reg, __) -> extModel.register(reg)
        ));
    }

    /**
     * Method executed for each EE Configuration file,
     * allowing addons to extend the configuration files with their own options.
     *
     * @param ctx Config Creation Context
     * @apiNote Please make sure you are extending the correct type of the configuration file.
     * This method is executed for Client / Startup configs.
     */
    @Override
    public void extendConfig(ConfigCreationContext ctx) {
        if (!ctx.isStartup()) return;
        NeoFeatureGen.setupConfig(ctx);
    }

    /**
     * Method used to provide default configuration data for the mod it supports, if necessary.
     *
     * @param ctx DCCreationContext used to register configs and check for compatibility.
     */
    @Override
    public void provideDefaultConfiguration(DCCreationContext ctx) {
        //TODO: Add to the defaults replacements for the textures,
        // as we don't actually replace vanilla items by default (causes issues).

        // We don't need to check for compatibility while adding vanilla materials, no other addon should add those, and we will be executed first!
        // Materials
        getBuilder("material/coal", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/copper", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/iron", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/gold", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/diamond", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/emerald", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/lapis", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/redstone", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/quartz", "vanilla").markAsMaterial().finish(ctx);
//        getBuilder("material/netherite", "vanilla").markAsMaterial().finish(ctx);

        // Strata
        getBuilder("strata/stone", "vanilla").markAsStrata().finish(ctx);
        getBuilder("strata/deepslate", "vanilla").markAsStrata().finish(ctx);
        getBuilder("strata/netherrack", "vanilla").markAsStrata().finish(ctx);
        getBuilder("strata/diorite", "vanilla").markAsStrata().finish(ctx);
        getBuilder("strata/granite", "vanilla").markAsStrata().finish(ctx);
        getBuilder("strata/andesite", "vanilla").markAsStrata().finish(ctx);
        getBuilder("strata/tuff", "vanilla").markAsStrata().finish(ctx);

        // Deposits
        getBuilder("deposit/coal/coal_lower", "vanilla/coal").markAsDeposit().finish(ctx);
        getBuilder("deposit/coal/coal_upper", "vanilla/coal").markAsDeposit().finish(ctx);

        getBuilder("deposit/copper/copper_normal", "vanilla/copper").markAsDeposit().finish(ctx);
        getBuilder("deposit/copper/copper_large", "vanilla/copper").markAsDeposit().finish(ctx);

        getBuilder("deposit/iron/iron_small", "vanilla/iron").markAsDeposit().finish(ctx);
        getBuilder("deposit/iron/iron_middle", "vanilla/iron").markAsDeposit().finish(ctx);
        getBuilder("deposit/iron/iron_upper", "vanilla/iron").markAsDeposit().finish(ctx);

        getBuilder("deposit/gold/gold_extra", "vanilla/gold").markAsDeposit().finish(ctx);
        getBuilder("deposit/gold/gold_lower", "vanilla/gold").markAsDeposit().finish(ctx);
        getBuilder("deposit/gold/gold_normal", "vanilla/gold").markAsDeposit().finish(ctx);
        getBuilder("deposit/gold/gold_nether", "vanilla/gold").markAsDeposit().finish(ctx);
        getBuilder("deposit/gold/gold_nether_delta", "vanilla/gold").markAsDeposit().finish(ctx);

        getBuilder("deposit/diamond/diamond", "vanilla/diamond").markAsDeposit().finish(ctx);
        getBuilder("deposit/diamond/diamond_small", "vanilla/diamond").markAsDeposit().finish(ctx);
        getBuilder("deposit/diamond/diamond_medium", "vanilla/diamond").markAsDeposit().finish(ctx);
        getBuilder("deposit/diamond/diamond_large", "vanilla/diamond").markAsDeposit().finish(ctx);

        getBuilder("deposit/emerald/emerald", "vanilla/emerald").markAsDeposit().finish(ctx);

        getBuilder("deposit/lapis/lapis_lower", "vanilla/lapis").markAsDeposit().finish(ctx);
        getBuilder("deposit/lapis/lapis_normal", "vanilla/lapis").markAsDeposit().finish(ctx);

        getBuilder("deposit/redstone/redstone_lower", "vanilla/redstone").markAsDeposit().finish(ctx);
        getBuilder("deposit/redstone/redstone_normal", "vanilla/redstone").markAsDeposit().finish(ctx);

        getBuilder("deposit/quartz/quartz", "vanilla/quartz").markAsDeposit().finish(ctx);
        getBuilder("deposit/quartz/quartz_delta", "vanilla/quartz").markAsDeposit().finish(ctx);
    }

    /**
     * Method called after EEDataGenerator is created and ready for registration of providers.
     *
     * @param generator EEDataGenerator instance.
     * @param providers Vanilla Registry Lookup for use with vanilla generators that require it.
     * @param registry  The registry class specified in the R parameter of your plugin.
     */
    @Override
    public void registerDynamicDataGen(EEDataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, DataRegistry registry) {
        //TODO: Rework data generators to use the new registry (ouch)
        generator.addProvider(true, new BlockStatesGen(generator, registry));
        generator.addProvider(true, new BlockModelsGen(generator, registry));
        generator.addProvider(true, new BlockTagsGen(generator, registry));
        generator.addProvider(true, new BlockHarvestLevelTagsGen(generator, registry));
        generator.addProvider(true, new BlockHarvestToolTagsGen(generator, registry));
        generator.addProvider(true, new ItemModelsGen(generator, registry));
        generator.addProvider(true, new ItemTagsGen(generator, registry));
        generator.addProvider(true, new FluidModelsGen(generator, registry));
        generator.addProvider(true, new FluidTagsGen(generator, registry));
        generator.addProvider(true, new LangGen(generator, registry));
        generator.addProvider(true, new RecipesGen(generator, registry, providers));
        generator.addProvider(true, new LootGen(generator, registry, providers));
        generator.addProvider(true, new OreFeatureGen(generator, registry, providers));
        generator.addProvider(true, new NeoFeatureGen(generator, registry, providers));
        generator.addProvider(true, new BiomeTagsGen(generator, registry));
    }

    /**
     * Method called after validation and serialization of the models defined by the plugins.
     *
     * @param registry The registry class specified in the R parameter of your plugin.
     * @apiNote This method is called when it's safe to register objects to Minecraft/Mods registries.
     */
    @Override
    public void register(DataRegistry registry) {
        for (MaterialModel material : registry.getRegisteredMaterials()) {
            List<String> types = material.getProcessedTypes();
            if (types.contains("storage_block")) EERegistrar.registerStorageBlocks(material);
            if (types.contains("ingot")) EERegistrar.registerIngots(material);
            if (types.contains("nugget")) EERegistrar.registerNuggets(material);
            if (types.contains("gem")) EERegistrar.registerGems(material);
            if (types.contains("dust")) EERegistrar.registerDusts(material);
            if (types.contains("plate")) EERegistrar.registerPlates(material);
            if (types.contains("gear")) EERegistrar.registerGears(material);
            if (types.contains("rod")) EERegistrar.registerRods(material);
            if (types.contains("sword")) EERegistrar.registerSwords(material);
            if (types.contains("pickaxe")) EERegistrar.registerPickaxes(material);
            if (types.contains("axe")) EERegistrar.registerAxes(material);
            if (types.contains("shovel")) EERegistrar.registerShovels(material);
            if (types.contains("hoe")) EERegistrar.registerHoes(material);
            if (types.contains("paxel")) EERegistrar.registerPaxels(material);
            if (types.contains("armor")) EERegistrar.registerArmor(material);
            if (types.contains("shield")) EERegistrar.registerShields(material);
            if (types.contains("fluid")) EERegistrar.registerFluids(material);

            if (types.contains("raw")) {
                EERegistrar.registerRaw(material);
                if (types.contains("storage_block")) EERegistrar.registerRawBlocks(material);
            }

            if (types.contains("cluster")) {
                EERegistrar.registerSmallBudBlocks(material);
                EERegistrar.registerMediumBudBlocks(material);
                EERegistrar.registerLargeBudBlocks(material);
                EERegistrar.registerClusterBlocks(material);
                EERegistrar.registerBuddingBlocks(material);
                EERegistrar.registerClusterShardBlocks(material);
                EERegistrar.registerClusterShards(material);
            }

            if (types.contains("ore")) {
                List<StrataModel> stratas = material.getStrata().isEmpty()?
                    registry.getRegisteredStrata():
                    material.getStrata().stream()
                        .map(registry::getStrataModel)
                        .toList();

                for (StrataModel strata : stratas) {
                    EERegistrar.registerOre(strata, material);

                    //TODO: Rework Sample System.
//                    if (types.contains("sample")) {
//                        if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
//                            EERegistrar.registerSample(strata, material);
//                        }
//                    }
                }
            }
        }
    }

    private DCDataBuilder getBuilder(String internal, String external) {
        return DCDataBuilder.fromInternalFile("assets/%s/configs/%s".formatted(Reference.MOD_ID, internal), external);
    }
}
