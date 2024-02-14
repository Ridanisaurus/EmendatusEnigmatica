/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.loader.parser.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;

import static com.ridanisaurus.emendatusenigmatica.loader.Validator.LOGGER;

public class MaterialModel {
	public static final Codec<MaterialModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("id").forGetter(i -> i.id),
			Codec.STRING.fieldOf("source").forGetter(i -> i.source),
			Codec.STRING.fieldOf("localizedName").forGetter(i -> i.localizedName),
			Codec.BOOL.optionalFieldOf("disableDefaultOre").forGetter(i -> Optional.of(i.disableDefaultOre)),
			Codec.list(Codec.STRING).fieldOf("processedTypes").forGetter(i -> i.processedTypes),
			Codec.list(Codec.STRING).optionalFieldOf("strata").forGetter(i -> Optional.of(i.strata)),
			MaterialPropertiesModel.CODEC.optionalFieldOf("properties").forGetter(i -> Optional.of(i.properties)),
			MaterialGasPropertiesModel.CODEC.optionalFieldOf("gas").forGetter(i -> Optional.of(i.gas)),
			MaterialOreDropModel.CODEC.optionalFieldOf("oreDrop").forGetter(i -> Optional.of(i.oreDrop)),
			MaterialCompatModel.CODEC.optionalFieldOf("compat").forGetter(i -> Optional.of(i.compat)),
			MaterialColorsModel.CODEC.optionalFieldOf("colors").forGetter(i -> Optional.of(i.colors)),
			MaterialToolsModel.CODEC.optionalFieldOf("tools").forGetter(i -> Optional.of(i.tools)),
			MaterialArmorModel.CODEC.optionalFieldOf("armor").forGetter(i -> Optional.of(i.armor))
	).apply(x, (id, source, localizedName, disableDefaultOre, processedTypes, strata, properties, gas, oreDrop, compat, colors, tools, armor) -> new MaterialModel(
			id,
			source,
			localizedName,
			disableDefaultOre.orElse(false),
			processedTypes,
			strata.orElse(List.of()),
			properties.orElse(new MaterialPropertiesModel()),
			gas.orElse(new MaterialGasPropertiesModel()),
			oreDrop.orElse(new MaterialOreDropModel()),
			compat.orElse(new MaterialCompatModel()),
			colors.orElse(new MaterialColorsModel()),
			tools.orElse(new MaterialToolsModel()),
			armor.orElse(new MaterialArmorModel())
	)));

	private final String id;
	private final String source;
	private final String localizedName;
	private final boolean disableDefaultOre;
	private final List<String> processedTypes;
	private final List<String> strata;
	private final MaterialPropertiesModel properties;
	private final MaterialGasPropertiesModel gas;
	private final MaterialOreDropModel oreDrop;
	private final MaterialCompatModel compat;
	private final MaterialColorsModel colors;
	private final MaterialToolsModel tools;
	private final MaterialArmorModel armor;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static final Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new HashMap<>();
	static {
		validators.put("id", new Validator("id").getIDValidation(DefaultConfigPlugin.MATERIAL_IDS));
		validators.put("source", new Validator("source").getRequiredAcceptsOnlyValidation(List.of("vanilla", "modded"), false));
		validators.put("localizedName", new Validator("localizedName").NON_EMPTY_REQUIRED);
		validators.put("disableDefaultOre", new Validator("disableDefaultOre").REQUIRES_BOOLEAN);
		validators.put("properties_rg", new Validator("properties").getPassParentToValidators(MaterialPropertiesModel.validators, false));
		validators.put("colors_rg", new Validator("colors").getPassParentToValidators(MaterialColorsModel.validators, false));
		validators.put("compat", new Validator("compat").getObjectValidation(MaterialCompatModel.validators));
		validators.put("strata", new Validator("strata").getRegisteredIDValidation(DefaultConfigPlugin.STRATA_IDS, "Strata Registry", true));

		Validator typesValidator = new Validator("processedTypes");
		validators.put("processedTypes", (element, path) ->
			typesValidator.getRequiredAcceptsOnlyValidation(List.of(
				"storage_block",
				"ingot",
				"gem",
				"ore",
				"raw",
				"nugget",
				"dust",
				"plate",
				"gear",
				"rod",
				"fluid",
				"cluster",
				"sword",
				"pickaxe",
				"axe",
				"shovel",
				"hoe",
				"paxel",
				"helmet",
				"chestplate",
				"leggings",
				"boots",
				"shield",
				"infuse_type",
				"gas",
				"slurry",
				"crystal",
				"shard",
				"clump",
				"dirty_dust",
				"crushed_ore"),
			true).apply(element, path) &&
			typesValidator.getIllegalPairsValidation(List.of(
					new Pair<>("ingot", "gem")
			)).apply(element, path)
		);

		Validator oreDropValidator = new Validator("oreDrop");
		validators.put("oreDrop_rg", (element, path) -> {
			if (!oreDropValidator.assertParentObject(element, path)) return false;

			JsonObject obj = element.getAsJsonObject();
			JsonElement valueJson = obj.get(oreDropValidator.getName());
			JsonElement typesElement = obj.get("processedTypes");
			JsonObject tempObj = new JsonObject();
			boolean dropRequired = false;

			if (Objects.nonNull(typesElement) && typesElement.isJsonArray()) {
				JsonArray types = typesElement.getAsJsonArray();
				boolean gem = types.contains(new JsonPrimitive("gem"));
				boolean raw = types.contains(new JsonPrimitive("raw"));
				boolean ore = types.contains(new JsonPrimitive("ore"));
				if (!ore) {
					if (Objects.nonNull(valueJson)) {
						LOGGER.warn("\"%s\" should not be present when \"ore\" is missing from the \"processedTypes\" in file \"%s\"".formatted(oreDropValidator.getName(), Validator.obfuscatePath(path)));
					}
				} else if (!gem && !raw) {
					if (Objects.isNull(valueJson)) {
						LOGGER.error("\"%s\" is required when \"ore\", but no \"gem\" or \"raw\" is present in the \"processedTypes\" in file \"%s\".".formatted(oreDropValidator.getName(), Validator.obfuscatePath(path)));
						return false;
					}
					dropRequired = true;
				}
			} else if (Objects.nonNull(typesElement) && !typesElement.isJsonArray()) {
				LOGGER.warn("Expected \"processedTypes\" to be an array! Can't accurately verify values of \"%s\" in file \"%s\".".formatted(oreDropValidator.getName(), Validator.obfuscatePath(path)));
			}

			tempObj.add("DROP_REQUIRED", new JsonPrimitive(dropRequired));
			return oreDropValidator.passTempToValidators(tempObj, valueJson, path, MaterialOreDropModel.validators, false);
		});

		// Yes, Tools and armor are nearly identical. I don't have any idea how to "unify" those however...
		Validator toolsValidator = new Validator("tools");
		validators.put(toolsValidator.getName() + "_rg", (element, path) -> {
			if (!toolsValidator.assertParentObject(element, path)) return false;

			JsonObject obj = element.getAsJsonObject();
			JsonElement valueJson = obj.get(toolsValidator.getName());
			JsonElement typesElement = obj.get("processedTypes");
			JsonObject tempObj = new JsonObject();
			boolean sword = false;
			boolean pickaxe = false;
			boolean axe = false;
			boolean shovel = false;
			boolean hoe = false;
			boolean paxel = false;

			if (Objects.nonNull(typesElement) && typesElement.isJsonArray()) {
				JsonArray types = typesElement.getAsJsonArray();
				sword = types.contains(new JsonPrimitive("sword"));
				pickaxe = types.contains(new JsonPrimitive("pickaxe"));
				axe = types.contains(new JsonPrimitive("axe"));
				shovel = types.contains(new JsonPrimitive("shovel"));
				hoe = types.contains(new JsonPrimitive("hoe"));
				paxel = types.contains(new JsonPrimitive("paxel"));
				if (!(sword || pickaxe || axe || shovel || hoe || paxel)) {
					if (Objects.nonNull(valueJson)) {
						LOGGER.warn("\"%s\" should not be present when tools are missing from the \"processedTypes\" in file \"%s\"".formatted(toolsValidator.getName(), Validator.obfuscatePath(path)));
					}
				} else if (Objects.isNull(valueJson)) {
					LOGGER.error("\"%s\" is required when tools are present in the \"processedTypes\" in file \"%s\".".formatted(toolsValidator.getName(), Validator.obfuscatePath(path)));
					return false;
				}
			} else if (Objects.nonNull(typesElement) && !typesElement.isJsonArray()) {
				LOGGER.warn("Expected \"processedTypes\" to be an array! Can't accurately verify values of \"%s\" in file \"%s\".".formatted(toolsValidator.getName(), Validator.obfuscatePath(path)));
			}

			tempObj.add("sword",   new JsonPrimitive(sword));
			tempObj.add("pickaxe", new JsonPrimitive(pickaxe));
			tempObj.add("axe",     new JsonPrimitive(axe));
			tempObj.add("shovel",  new JsonPrimitive(shovel));
			tempObj.add("hoe",     new JsonPrimitive(hoe));
			tempObj.add("paxel",   new JsonPrimitive(paxel));

			return toolsValidator.passTempToValidators(tempObj, valueJson, path, MaterialToolsModel.validators, false);
		});

		Validator armorValidator = new Validator("armor");
		validators.put(armorValidator.getName() + "_rg", (element, path) -> {
			if (!armorValidator.assertParentObject(element, path)) return false;

			JsonObject obj = element.getAsJsonObject();
			JsonElement valueJson = obj.get(armorValidator.getName());
			JsonElement typesElement = obj.get("processedTypes");
			JsonObject tempObj = new JsonObject();
			boolean helmet = false;
			boolean chestplate = false;
			boolean leggings = false;
			boolean boots = false;
			boolean shield = false;

			if (Objects.nonNull(typesElement) && typesElement.isJsonArray()) {
				JsonArray types = typesElement.getAsJsonArray();
				helmet = types.contains(new JsonPrimitive("helmet"));
				chestplate = types.contains(new JsonPrimitive("chestplate"));
				leggings = types.contains(new JsonPrimitive("leggings"));
				boots = types.contains(new JsonPrimitive("boots"));
				shield = types.contains(new JsonPrimitive("shield"));
				if (!(helmet || chestplate || leggings || boots || shield)) {
					if (Objects.nonNull(valueJson)) {
						LOGGER.warn("\"%s\" should not be present when armor pieces are missing from the \"processedTypes\" in file \"%s\"".formatted(armorValidator.getName(), Validator.obfuscatePath(path)));
					}
				} else if (Objects.isNull(valueJson)) {
					LOGGER.error("\"%s\" is required when armor pieces are present in the \"processedTypes\" in file \"%s\".".formatted(armorValidator.getName(), Validator.obfuscatePath(path)));
					return false;
				}
			} else if (Objects.nonNull(typesElement) && !typesElement.isJsonArray()) {
				LOGGER.warn("Expected \"processedTypes\" to be an array! Can't accurately verify values of \"%s\" in file \"%s\".".formatted(armorValidator.getName(), Validator.obfuscatePath(path)));
			}

			tempObj.add("helmet",     new JsonPrimitive(helmet));
			tempObj.add("chestplate", new JsonPrimitive(chestplate));
			tempObj.add("leggings",   new JsonPrimitive(leggings));
			tempObj.add("boots",      new JsonPrimitive(boots));
			tempObj.add("shield",     new JsonPrimitive(shield));

			return armorValidator.passTempToValidators(tempObj, valueJson, path, MaterialArmorModel.validators, false);
		});

		Validator gasValidator = new Validator("gas");
		validators.put(gasValidator.getName() + "_rg", (element, path) -> {
			if (!gasValidator.assertParentObject(element, path)) return false;

			JsonObject obj = element.getAsJsonObject();
			JsonElement valueJson = obj.get(gasValidator.getName());
			JsonElement typesElement = obj.get("processedTypes");

			if (Objects.nonNull(typesElement) && typesElement.isJsonArray()) {
				JsonArray types = typesElement.getAsJsonArray();
				boolean gas = types.contains(new JsonPrimitive("gas"));
				if (!gas) {
					if (Objects.nonNull(valueJson)) {
						LOGGER.warn("\"%s\" should not be present when \"gas\" is missing from the \"processedTypes\" in file \"%s\"".formatted(gasValidator.getName(), Validator.obfuscatePath(path)));
					}
				} else if (Objects.isNull(valueJson)) {
					LOGGER.error("\"%s\" is required when \"gas\" is present in the \"processedTypes\" in file \"%s\".".formatted(gasValidator.getName(), Validator.obfuscatePath(path)));
					return false;
				}
			} else if (Objects.nonNull(typesElement) && !typesElement.isJsonArray()) {
				LOGGER.warn("Expected \"processedTypes\" to be an array! Can't accurately verify values of \"%s\" in file \"%s\".".formatted(gasValidator.getName(), Validator.obfuscatePath(path)));
			}

			return gasValidator.validateObject(valueJson, path, MaterialGasPropertiesModel.validators);
		});
	}

	public MaterialModel(String id, String source, String localizedName, boolean disableDefaultOre, List<String> processedTypes, List<String> strata,
	                     MaterialPropertiesModel properties, MaterialGasPropertiesModel gas, MaterialOreDropModel oreDrop, MaterialCompatModel compat, MaterialColorsModel colors, MaterialToolsModel tools, MaterialArmorModel armor) {
		this.id = id;
		this.source = source;
		this.localizedName = localizedName;
		this.disableDefaultOre = disableDefaultOre;
		this.processedTypes = processedTypes;
		this.strata = strata;
		this.properties = properties;
		this.gas = gas;
		this.oreDrop = oreDrop;
		this.compat = compat;
		this.colors = colors;
		this.tools = tools;
		this.armor = armor;
	}

	public String getId() {
		return id;
	}

	public String getSource() {
		return source;
	}

	public boolean isModded() {
		return !getSource().equals("vanilla");
	}

	public boolean isVanilla() {
		return getSource().equals("vanilla");
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public boolean getDisableDefaultOre() {
		return disableDefaultOre;
	}

	public List<String> getProcessedTypes() {
		return processedTypes;
	}

	public List<String> getStrata() {
		return strata;
	}

	public MaterialPropertiesModel getProperties() {
		return properties;
	}

	public MaterialGasPropertiesModel getGas() {
		return gas;
	}

	public MaterialOreDropModel getOreDrop() {
		return oreDrop;
	}

	public MaterialCompatModel getCompat() {
		return compat;
	}

	public MaterialColorsModel getColors() {
		return colors;
	}

	public MaterialToolsModel getTools() {
		return tools;
	}

	public MaterialArmorModel getArmor() {
		return armor;
	}

	public ItemLike getOreDefaultDrop() {
		if (processedTypes.contains("ore")) {
			if (properties.getMaterialType().equals("gem")) {
				return processedTypes.contains("gem") ? EERegistrar.gemMap.get(id).get() : oreDrop.getDefaultItemDropAsItem();
			} else {
				return processedTypes.contains("raw") ? EERegistrar.rawMap.get(id).get() : oreDrop.getDefaultItemDropAsItem();
			}
		} else {
			return ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:air"));
		}
	}
}