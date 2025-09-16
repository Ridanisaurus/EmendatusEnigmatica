package com.ridanisaurus.eemekanismaddon.registry;

import com.ridanisaurus.emendatusenigmatica.items.templates.BasicBurnableItem;
import com.ridanisaurus.emendatusenigmatica.items.templates.BasicItem;
import com.ridanisaurus.eemekanismaddon.extensions.MekanismMaterialExtension;
import com.ridanisaurus.emendatusenigmatica.registries.data.EEItemMap;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
//import mekanism.api.chemical.gas.Gas;
//import mekanism.api.chemical.gas.GasBuilder;
//import mekanism.api.chemical.gas.attribute.GasAttributes;
//import mekanism.api.chemical.infuse.InfuseType;
//import mekanism.api.chemical.infuse.InfuseTypeBuilder;
//import mekanism.api.chemical.slurry.Slurry;
//import mekanism.api.chemical.slurry.SlurryBuilder;
//import mekanism.api.math.FloatingLong;
import mekanism.api.chemical.Chemical;
import mekanism.common.registration.impl.ChemicalDeferredRegister;
import mekanism.common.registration.impl.DeferredChemical;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EEMekanismRegistrar {
    public static final ChemicalDeferredRegister CHEMICALS = new ChemicalDeferredRegister(Reference.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);

	// Mekanism Compat
	public static Map<String, DeferredChemical<Chemical>> dirtySlurryMap = new HashMap<>();
	public static Map<String, DeferredChemical<Chemical>> cleanSlurryMap = new HashMap<>();
    public static Map<String, SlurryRegistryObject<Chemical, Chemical>> slurryMap = new HashMap<>();
    public static Map<String, DeferredChemical<Chemical>> infuseMap = new HashMap<>();
    public static Map<String, DeferredChemical<Chemical>> gasMap = new HashMap<>();
	public static EEItemMap<Item> crystalMap = new EEItemMap<>();
	public static EEItemMap<Item> shardMap = new EEItemMap<>();
	public static EEItemMap<Item> clumpMap = new EEItemMap<>();
	public static EEItemMap<Item> dirtyDustMap = new EEItemMap<>();
    public static EEItemMap<Item> enrichedMap = new EEItemMap<>();

    public static void registerCrystals(MekanismMaterialExtension material) {
        registerBurnableItem(crystalMap, material, material.getId() + "_crystal");
    }

    public static void registerShards(MekanismMaterialExtension material) {
        registerBurnableItem(shardMap, material, material.getId() + "_shard");
    }

    public static void registerClumps(MekanismMaterialExtension material) {
        registerBurnableItem(clumpMap, material, material.getId() + "_clump");
    }

    public static void registerDirtyDusts(MekanismMaterialExtension material) {
        registerBurnableItem(dirtyDustMap, material, material.getId() + "_dirty_dust");
    }

	public static void registerInfuseTypes(MekanismMaterialExtension material) {
        infuseMap.put(material.getId(), CHEMICALS.registerInfuse(material.getId(), material.getChemicalColor()));
        registerBurnableItem(enrichedMap, material, "enriched" + material.getId());
		// TODO: Add Enriched textures + data gen
	}

    public static void registerSlurries(MekanismMaterialExtension material) {
        String itemNameDirty = "dirty_" + material.getId();
        String itemNameClean = "clean_" + material.getId();

        ResourceLocation ore = ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "ores/" + material.getId());

        slurryMap.put(material.getId(), CHEMICALS.registerSlurry(material.getId(), it -> it.tint(material.getChemicalColor()).ore(ore)));

//        dirtySlurryMap.put(material.getId(), SLURRIES.register(itemNameDirty, () -> new Slurry(SlurryBuilder.dirty().ore(ore).color(material.getColors().getGasColor()))));
//        cleanSlurryMap.put(material.getId(), SLURRIES.register(itemNameClean, () -> new Slurry(SlurryBuilder.clean().ore(ore).color(material.getColors().getGasColor()))));
    }

    public static void registerGases(MekanismMaterialExtension material) {
//        String itemName = "gaseous_" + material.getId();
//        GasBuilder builder = GasBuilder.builder();
//        builder.color(material.getChemicalColor());
//
//        if (material.getGas().isBurnable()) {
//            builder.with(new GasAttributes.Fuel(()-> material.getGas().getBurnTime(), ()-> FloatingLong.create(material.getGas().getEnergyDensity())));
//        }
//        if (material.getGas().isRadioactive()) {
//            builder.with(new GasAttributes.Radiation(material.getGas().getRadioactivity()));
//        }
//        if (material.getGas().isCoolant()) {
//             TODO: Check if BOTH needs to be created
//            if (material.getGas().getCoolantType().equals("cooled")) {
//                builder.with(new GasAttributes.CooledCoolant(()-> gasMap.get(material.getId()).get(), material.getGas().getThermalEnthalpy(), material.getGas().getConductivity()));
//            } else {
//                builder.with(new GasAttributes.HeatedCoolant(()-> gasMap.get(material.getId()).get(), material.getGas().getThermalEnthalpy(), material.getGas().getConductivity()));
//            }
//        }
//
//        gasMap.put(material.getId(), GASES.register(itemName, () -> new Gas(builder)));
    }


    private static void registerBurnableItem(EEItemMap<Item> map, MekanismMaterialExtension material, String name) {
        registerBurnableItem(map, material, name, it -> it);
    }

    private static void registerBurnableItem(EEItemMap<Item> map, MekanismMaterialExtension material, String name, Function<Integer, Integer> modifier) {
        if (material.getOriginalModel().getProperties().isBurnable()) {
            map.put(material.getId(), ITEMS.register(name, () -> new BasicBurnableItem(material.getOriginalModel(), getBurnTime(material, modifier))));
        } else {
            map.put(material.getId(), ITEMS.register(name, () -> new BasicItem(material.getOriginalModel())));
        }
    }

    private static int getBurnTime(MekanismMaterialExtension model, Function<Integer, Integer> modifier) {
        var og = model.getOriginalModel();
        return og.getProperties().isBurnable()? modifier.apply(og.getProperties().getBurnTime()): 0;
    }

    private static int getBurnTime(MekanismMaterialExtension model) {
        return getBurnTime(model, it -> it);
    }

    public static void finalize(IEventBus eventBus) {
        CHEMICALS.register(eventBus);
		ITEMS.register(eventBus);
	}
}