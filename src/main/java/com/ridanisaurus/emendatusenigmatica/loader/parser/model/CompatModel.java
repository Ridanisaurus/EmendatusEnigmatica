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
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;

import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;

import static com.ridanisaurus.emendatusenigmatica.loader.Validator.LOGGER;

public class CompatModel {
	public static final Codec<CompatModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("id").forGetter(i -> i.id),
			Codec.list(CompatRecipesModel.CODEC).fieldOf("recipes").forGetter(i -> i.recipes)
	).apply(x, CompatModel::new));
	private final String id;
	private final List<CompatRecipesModel> recipes;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 */
	public static final Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();
	static {
		validators.put("id", new Validator("id").getRegisteredIDValidation(DefaultConfigPlugin.MATERIAL_IDS, "Material Registry", false));
		validators.put("recipes", new Validator("recipes").getRequiredObjectValidation(CompatRecipesModel.validators));
	}

	public CompatModel(String id, List<CompatRecipesModel> recipes) {
		this.id = id;
		this.recipes = recipes;
	}

	public CompatModel() {
		this.id = "";
		this.recipes = List.of();
	}

	public final String getId() {
		return id;
	}

	public final List<CompatRecipesModel> getRecipes() {
		return recipes;
	}

	public static class CompatRecipesModel {
		public static final Codec<CompatRecipesModel> CODEC = RecordCodecBuilder.create(x -> x.group(
				Codec.STRING.fieldOf("mod").forGetter(i -> i.mod),
				Codec.STRING.fieldOf("machine").forGetter(i -> i.machine),
				Codec.list(CompatValuesModel.CODEC).fieldOf("values").forGetter(i -> i.values)
		).apply(x, CompatRecipesModel::new));

		private final String mod;
		private final String machine;
		private final List<CompatValuesModel> values;

		/**
		 * Holds verifying functions for each field.
		 * Function returns true if verification was successful, false otherwise to stop registration of the json.
		 * Adding suffix _rg will request the original object instead of just the value of the field.
		 */
		public static final Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();
		/**
		 * Holds all acceptable mods and validators for their machines for the CompatModel.
		 * @apiNote To add support for additional mod + machines, add an entry to this map with mod name and validator of the machine.
		 * @see CompatRecipesModel#acceptableMods
		 * @see CompatRecipesModel#acceptableModIds
		 * @see CompatValuesModel#acceptableMods
		 */
		public static final Map<String, BiFunction<JsonElement, Path, Boolean>> acceptableMods = new HashMap<>();

		/**
		 * Holds all acceptable mods for mod validator.
		 * @see CompatRecipesModel#acceptableMods
		 * @see CompatRecipesModel#acceptableModIds
		 * @see CompatValuesModel#acceptableMods
		 */
		public static final List<String> acceptableModIds = new ArrayList<>();
		static {

			Validator machineValidator = new Validator("machine");
			acceptableMods.put("thermal", machineValidator.getRequiredAcceptsOnlyValidation(List.of("pulverizer", "induction_smelter"), false));
			acceptableMods.put("create", machineValidator.getRequiredAcceptsOnlyValidation(List.of("crushing_wheels", "fan_washing"), false));
			acceptableModIds.addAll(acceptableMods.keySet());

			validators.put("mod", new Validator("mod").getRequiredAcceptsOnlyValidation(acceptableModIds, false));

			Validator valuesValidator = new Validator("values");
			validators.put("values_rg", (element_rg, path_rg) -> {
				if (!(
					valuesValidator.assertParentObject(element_rg, path_rg) &&
					valuesValidator.assertArray(element_rg.getAsJsonObject().get("values"), path_rg)
				)) return false;

				JsonObject element = element_rg.getAsJsonObject();
				JsonArray array = element.get("values").getAsJsonArray();

				JsonObject temp = new JsonObject();
				try {
					temp.add("mod", new JsonPrimitive(Objects.requireNonNull(element.get("mod")).getAsString()));
				} catch (ClassCastException | NullPointerException e) {
					temp.add("mod", new JsonPrimitive("NONE"));
				}

				try {
					temp.add("machine", new JsonPrimitive(Objects.requireNonNull(element.get("machine")).getAsString()));
				} catch (ClassCastException | NullPointerException e) {
					temp.add("machine", new JsonPrimitive("NONE"));
				}

				return valuesValidator.passTempToValidators(temp, array, path_rg, CompatValuesModel.validators, false);
			});

			validators.put("machine_rg", (element, path) -> {
				if (!machineValidator.assertParentObject(element, path)) return false;
				if (!machineValidator.REQUIRED.apply(element.getAsJsonObject().get("machine"), path)) return false;

				JsonElement modJson = element.getAsJsonObject().get("mod");
				if (Objects.isNull(modJson)) {
					LOGGER.error("Mod is null! Can't verify values of machine for \"%s\"".formatted(Validator.obfuscatePath(path)));
					return false;
				}

				try {
					String mod = modJson.getAsString();
					var validator = acceptableMods.get(mod);
					if (Objects.isNull(validator)) {
						LOGGER.error("Illegal value for mod present! Can't verify values of machine for \"%s\"".formatted(Validator.obfuscatePath(path)));
						return false;
					}
					return validator.apply(element.getAsJsonObject().get("machine"), path);
				} catch (ClassCastException e) {
					LOGGER.error("Mod is not a string! Can't verify values of machine for \"%s\".".formatted(Validator.obfuscatePath(path)));
				}
				return false;
			});
		}

		public CompatRecipesModel(String mod, String machine, List<CompatValuesModel> values) {
			this.mod = mod;
			this.machine = machine;
			this.values = values;
		}

		public CompatRecipesModel() {
			this.mod = "";
			this.machine = "";
			this.values = List.of();
		}

		public String getMod() {
			return mod;
		}

		public String getMachine() {
			return machine;
		}

		public List<CompatValuesModel> getValues() {
			return values;
		}
	}

	public static class CompatValuesModel {
		public static final Codec<CompatValuesModel> CODEC = RecordCodecBuilder.create(x -> x.group(
				Codec.STRING.fieldOf("type").forGetter(i -> i.type),
				Codec.list(CompatIOModel.CODEC).fieldOf("input").orElse(List.of()).forGetter(i -> i.input),
				Codec.list(CompatIOModel.CODEC).fieldOf("output").orElse(List.of()).forGetter(i -> i.output)
		).apply(x, CompatValuesModel::new));

		private final String type;
		private final List<CompatIOModel> input;
		private final List<CompatIOModel> output;

		/**
		 * Holds all acceptable mods and validators for their machines for the CompatModel.
		 * @apiNote To add support for additional mod + machines, add an entry to this map with mod name and validator of the machine.
		 * @see CompatRecipesModel#acceptableMods
		 * @see CompatRecipesModel#acceptableModIds
		 * @see CompatValuesModel#acceptableMods
		 */
		public static final Map<String, BiFunction<JsonElement, Path, Boolean>> acceptableMods = new HashMap<>();

		/**
		 * Holds verifying functions for each field.
		 * Function returns true if verification was successful, false otherwise to stop registration of the json.
		 * Adding suffix _rg will request the original object instead of just the value of the field.
		 */
		public static final Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();
		static {
			Validator typeValidator = new Validator("type");
			Validator inputValidator = new Validator("input");
			Validator outputValidator = new Validator("output");

			acceptableMods.put("thermal", typeValidator.getRequiredAcceptsOnlyValidation(List.of("ore", "raw", "alloy"), false));
			acceptableMods.put("create", typeValidator.getRequiredAcceptsOnlyValidation(List.of("ore", "crushed_ore"), false));

			validators.put("type_rg", (element, path) -> {
				if (!typeValidator.assertParentObject(element, path)) return false;

				String mod = element.getAsJsonObject().get("TEMP").getAsJsonObject().get("mod").getAsString();
				if (mod.equals("NONE")) {
					LOGGER.error("Mod is none! Can't verify values of type for \"%s\"".formatted(Validator.obfuscatePath(path)));
					return false;
				}

				var validator = acceptableMods.get(mod);
				if (Objects.isNull(validator)) {
					LOGGER.error("Illegal value for mod present! Can't verify values of type for \"%s\"".formatted(Validator.obfuscatePath(path)));
					return false;
				}
				return validator.apply(element.getAsJsonObject().get("type"), path);
			});

			validators.put("input_rg", (element_rg, path) -> {
				if (!inputValidator.assertParentObject(element_rg, path)) return false;

				JsonObject element = element_rg.getAsJsonObject();
				if (Objects.isNull(element.get("input"))) return true;
				if (LOGGER.shouldLog) {
					JsonObject temp = element.get("TEMP").getAsJsonObject();
					String mod = temp.get("mod").getAsString();
					String machine = temp.get("machine").getAsString();
					String type = "NONE";

					if (mod.equals("NONE")) LOGGER.warn("Mod is none! Can't accurately verify values of input for \"%s\"".formatted(Validator.obfuscatePath(path)));
					if (machine.equals("NONE")) LOGGER.warn("Machine is none! Can't accurately verify values of input for \"%s\"".formatted(Validator.obfuscatePath(path)));

					try {
						type = element.get("type").getAsString();
					} catch (ClassCastException e) {
						LOGGER.error("Type is not a string! Can't accurately verify values of input for \"%s\".".formatted(Validator.obfuscatePath(path)));
					} catch (NullPointerException e) {
						LOGGER.warn("Type is null! Can't accurately verify values of input for \"%s\"".formatted(Validator.obfuscatePath(path)));
					}


					if (!mod.equals("thermal")) LOGGER.warn("Input should not be present when selected mod is different than thermal (Currently: %s). Found in file \"%s\".".formatted(mod, Validator.obfuscatePath(path)));
					if (!machine.equals("induction_smelter")) LOGGER.warn("Input should not be present when selected machine is different than induction_smelter (Currently: %s). Found in file \"%s\".".formatted(machine, Validator.obfuscatePath(path)));
					if (!type.equals("alloy")) LOGGER.warn("Input should not be present when selected type is different than alloy (Currently: %s). Found in file \"%s\".".formatted(type, Validator.obfuscatePath(path)));

					element.get("input").getAsJsonArray().forEach(entry -> {
						if (Objects.nonNull(entry.getAsJsonObject().get("chance"))) LOGGER.warn("Chance should not be present in input object! Found in file \"%s\".".formatted(Validator.obfuscatePath(path)));
					});
				}

				return outputValidator.getObjectValidation(CompatIOModel.validators).apply(element.get("input"), path);
			});

			validators.put("output", outputValidator.getObjectValidation(CompatIOModel.validators));
		}

		CompatValuesModel(String type, List<CompatIOModel> input, List<CompatIOModel> output) {
			this.type = type;
			this.input = input;
			this.output = output;
		}

		public String getType() {
			return type;
		}

		public List<CompatIOModel> getInput() {
			return input;
		}

		public List<CompatIOModel> getOutput() {
			return output;
		}
	}

	public static class CompatIOModel {
		public static final Codec<CompatIOModel> CODEC = RecordCodecBuilder.create(x -> x.group(
				Codec.STRING.optionalFieldOf("item").forGetter(i -> Optional.ofNullable(i.item)),
				Codec.INT.optionalFieldOf("count").forGetter(i -> Optional.of(i.count)),
				Codec.FLOAT.optionalFieldOf("chance").forGetter(i -> Optional.of(i.chance))
		).apply(x, (item, count, chance) -> new CompatIOModel(
				item.orElse(""),
				count.orElse(0),
				chance.orElse(0.0f)
		)));

		/**
		 * Holds verifying functions for each field.
		 * Function returns true if verification was successful, false otherwise to stop registration of the json.
		 * Adding suffix _rg will request the original object instead of just the value of the field.
		 */
		public static final Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();
		static {
			validators.put("item", new Validator("item").RESOURCE_ID);
			validators.put("count", new Validator("count").getIntRange(0, 64, false));
			validators.put("chance", new Validator("chance").getRange(0, 1, false));
		}

		private final String item;
		private final int count;
		private final float chance;

		public CompatIOModel(String item, int count, float chance) {
			this.item = item;
			this.count = count;
			this.chance = chance;
		}

		public String getItem() {
			return item;
		}

		public int getCount() {
			return count;
		}

		public float getChance() {
			return chance;
		}
	}
}