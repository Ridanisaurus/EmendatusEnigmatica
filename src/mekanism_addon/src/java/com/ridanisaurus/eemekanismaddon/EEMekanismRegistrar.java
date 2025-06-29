package com.ridanisaurus.eemekanismaddon;

import com.ridanisaurus.emendatusenigmatica.items.BasicBurnableItem;
import com.ridanisaurus.emendatusenigmatica.items.BasicItem;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasBuilder;
import mekanism.api.chemical.gas.attribute.GasAttributes;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.api.chemical.infuse.InfuseTypeBuilder;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.api.math.FloatingLong;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class EEMekanismRegistrar {
	public static final DeferredRegister<Slurry> SLURRIES = DeferredRegister.create(MekanismAPI.slurryRegistryName(), Reference.MOD_ID);
	public static final DeferredRegister<Gas> GASES = DeferredRegister.create(MekanismAPI.gasRegistryName(), Reference.MOD_ID);
	public static final DeferredRegister<InfuseType> INFUSE_TYPES = DeferredRegister.create(MekanismAPI.infuseTypeRegistryName(), Reference.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

	// Mekanism Compat
	public static Map<String, RegistryObject<Slurry>> dirtySlurryMap = new HashMap<>();
	public static Map<String, RegistryObject<Slurry>> cleanSlurryMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> crystalMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> shardMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> clumpMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> dirtyDustMap = new HashMap<>();
	public static Map<String, RegistryObject<Gas>> gasMap = new HashMap<>();
	public static Map<String, RegistryObject<InfuseType>> infuseMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> enrichedMap = new HashMap<>();

	public static void registerSlurries(MaterialModel material) {
		String itemNameDirty = "dirty_" + material.getId();
		String itemNameClean = "clean_" + material.getId();

		ResourceLocation ore = new ResourceLocation(Reference.FORGE, "ores/" + material.getId());

		dirtySlurryMap.put(material.getId(), SLURRIES.register(itemNameDirty, () -> new Slurry(SlurryBuilder.dirty().ore(ore).color(material.getColors().getGasColor()))));
		cleanSlurryMap.put(material.getId(), SLURRIES.register(itemNameClean, () -> new Slurry(SlurryBuilder.clean().ore(ore).color(material.getColors().getGasColor()))));
	}

	public static void registerCrystals(MaterialModel material) {
		String itemName = material.getId() + "_crystal";
		if (material.getProperties().isBurnable()) {
			crystalMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			crystalMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerShards(MaterialModel material) {
		String itemName = material.getId() + "_shard";
		if (material.getProperties().isBurnable()) {
			shardMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			shardMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerClumps(MaterialModel material) {
		String itemName = material.getId() + "_clump";
		if (material.getProperties().isBurnable()) {
			clumpMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			clumpMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerDirtyDusts(MaterialModel material) {
		String itemName = material.getId() + "_dirty_dust";
		if (material.getProperties().isBurnable()) {
			dirtyDustMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			dirtyDustMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerGases(MaterialModel material) {
		String itemName = "gaseous_" + material.getId();
		GasBuilder builder = GasBuilder.builder();
		builder.color(material.getColors().getGasColor());

		if (material.getGas().isBurnable()) {
			builder.with(new GasAttributes.Fuel(()-> material.getGas().getBurnTime(), ()-> FloatingLong.create(material.getGas().getEnergyDensity())));
		}
		if (material.getGas().isRadioactive()) {
			builder.with(new GasAttributes.Radiation(material.getGas().getRadioactivity()));
		}
		if (material.getGas().isCoolant()) {
			// TODO: Check if BOTH needs to be created
			if (material.getGas().getCoolantType().equals("cooled")) {
				builder.with(new GasAttributes.CooledCoolant(()-> gasMap.get(material.getId()).get(), material.getGas().getThermalEnthalpy(), material.getGas().getConductivity()));
			} else {
				builder.with(new GasAttributes.HeatedCoolant(()-> gasMap.get(material.getId()).get(), material.getGas().getThermalEnthalpy(), material.getGas().getConductivity()));
			}
		}

		gasMap.put(material.getId(), GASES.register(itemName, () -> new Gas(builder)));
	}

	public static void registerInfuseTypes(MaterialModel material) {
		String itemName = material.getId();
		infuseMap.put(material.getId(), INFUSE_TYPES.register(itemName, () -> new InfuseType(InfuseTypeBuilder.builder().color(material.getColors().getGasColor()))));
		if (material.getProperties().isBurnable()) {
			enrichedMap.put(material.getId(), ITEMS.register("enriched_" + itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			enrichedMap.put(material.getId(), ITEMS.register("enriched_" + itemName, () -> new BasicItem(material)));
		}
		// TODO: Add Enriched textures + data gen
	}

	public static void finalize(IEventBus eventBus) {
		SLURRIES.register(eventBus);
		GASES.register(eventBus);
		INFUSE_TYPES.register(eventBus);
		ITEMS.register(eventBus);
	}
}