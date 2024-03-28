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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import org.apache.commons.lang3.function.TriFunction;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static com.ridanisaurus.emendatusenigmatica.loader.Validator.LOGGER;

public class MaterialToolsModel {
	public static final Codec<MaterialToolsModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.FLOAT.optionalFieldOf("attackDamage").forGetter(i -> Optional.of(i.attackDamage)),
			Codec.INT.optionalFieldOf("level").forGetter(i -> Optional.of(i.level)),
			Codec.INT.optionalFieldOf("enchantability").forGetter(i -> Optional.of(i.enchantability)),
			Codec.FLOAT.optionalFieldOf("efficiency").forGetter(i -> Optional.of(i.efficiency)),
			ToolModel.CODEC.optionalFieldOf("sword").forGetter(i -> Optional.of(i.sword)),
			ToolModel.CODEC.optionalFieldOf("pickaxe").forGetter(i -> Optional.of(i.pickaxe)),
			ToolModel.CODEC.optionalFieldOf("axe").forGetter(i -> Optional.of(i.axe)),
			ToolModel.CODEC.optionalFieldOf("shovel").forGetter(i -> Optional.of(i.shovel)),
			ToolModel.CODEC.optionalFieldOf("hoe").forGetter(i -> Optional.of(i.hoe)),
			ToolModel.CODEC.optionalFieldOf("paxel").forGetter(i -> Optional.of(i.paxel))
	).apply(x, (attackDamage, level, enchantability, efficiency, sword, pickaxe, axe, shovel, hoe, paxel) -> new MaterialToolsModel(
			attackDamage.orElse(0.0f),
			level.orElse(0),
			enchantability.orElse(0),
			efficiency.orElse(0.0f),
			sword.orElse(new ToolModel()),
			pickaxe.orElse(new ToolModel()),
			axe.orElse(new ToolModel()),
			shovel.orElse(new ToolModel()),
			hoe.orElse(new ToolModel()),
			paxel.orElse(new ToolModel())
	)));
    private final float attackDamage;
	private final int level;
	private final int enchantability;
	private final float efficiency;
	private final ToolModel sword;
	private final ToolModel pickaxe;
	private final ToolModel axe;
	private final ToolModel shovel;
	private final ToolModel hoe;
	private final ToolModel paxel;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("attackDamage", new Validator("attackDamage").getRequiredRange(0, Integer.MAX_VALUE, false));
		validators.put("level", new Validator("level").getRequiredRange(0, Integer.MAX_VALUE, false));
		validators.put("enchantability", new Validator("enchantability").getRequiredRange(0, Integer.MAX_VALUE, false));
		validators.put("efficiency", new Validator("efficiency").getRequiredRange(0, Integer.MAX_VALUE, false));

		TriFunction<Validator, JsonElement, Path, Boolean> toolValidator = (validator, element, path) -> {
			if (!validator.assertParentObject(element, path)) return false;
			String tool = validator.getName();
			JsonObject obj = element.getAsJsonObject();
			boolean required = false;

			if (!Validator.checkForTEMP(obj, path, false)) {
				LOGGER.error("Parent object is missing while validating \"%s\" in file \"%s\". Something is not right.".formatted(tool, Validator.obfuscatePath(path)));
			} else {
				required = obj.get("TEMP").getAsJsonObject().get(tool).getAsBoolean();
			}

			JsonElement valueJson = obj.get(tool);

			if (!required) {
				if (Objects.isNull(valueJson)) return true;
				LOGGER.warn("\"%s\" should not be present when it's missing from \"processedTypes\" in file \"%s\".".formatted(tool, Validator.obfuscatePath(path)));
				return validator.validateObject(valueJson, path, ToolModel.validators);
			}

			return validator.getRequiredObjectValidation(ToolModel.validators, false).apply(valueJson, path);
		};

		validators.put("sword_rg",   (element, path) -> toolValidator.apply(new Validator("sword"), element, path));
		validators.put("pickaxe_rg", (element, path) -> toolValidator.apply(new Validator("pickaxe"), element, path));
		validators.put("axe_rg",     (element, path) -> toolValidator.apply(new Validator("axe"), element, path));
		validators.put("shovel_rg",  (element, path) -> toolValidator.apply(new Validator("shovel"), element, path));
		validators.put("hoe_rg",     (element, path) -> toolValidator.apply(new Validator("hoe"), element, path));
		validators.put("paxel_rg",   (element, path) -> toolValidator.apply(new Validator("paxel"), element, path));
	}

	public MaterialToolsModel(float attackDamage, int level, int enchantability, float efficiency, ToolModel sword, ToolModel pickaxe, ToolModel axe, ToolModel shovel, ToolModel hoe, ToolModel paxel) {
		this.attackDamage = attackDamage;
		this.level = level;
		this.enchantability = enchantability;
		this.efficiency = efficiency;
		this.sword = sword;
		this.pickaxe = pickaxe;
		this.axe = axe;
		this.shovel = shovel;
		this.hoe = hoe;
		this.paxel = paxel;
	}

	public MaterialToolsModel() {
		this.attackDamage = 0.0f;
		this.level = 0;
		this.enchantability = 0;
		this.efficiency = 0.0f;
		this.sword = new ToolModel();
		this.pickaxe = new ToolModel();
		this.axe = new ToolModel();
		this.shovel = new ToolModel();
		this.hoe = new ToolModel();
		this.paxel = new ToolModel();
	}

	public float getAttackDamage() {
		return attackDamage;
	}

	public int getLevel() {
		return level;
	}

	public int getEnchantability() {
		return enchantability;
	}

	public float getEfficiency() {
		return efficiency;
	}

	public ToolModel getSword() {
		return sword;
	}

	public ToolModel getPickaxe() {
		return pickaxe;
	}

	public ToolModel getAxe() {
		return axe;
	}

	public ToolModel getShovel() {
		return shovel;
	}

	public ToolModel getHoe() {
		return hoe;
	}

	public ToolModel getPaxel() {
		return paxel;
	}
}