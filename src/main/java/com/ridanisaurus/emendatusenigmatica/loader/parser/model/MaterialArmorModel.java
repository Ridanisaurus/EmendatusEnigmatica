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
import java.util.*;
import java.util.function.BiFunction;

import static com.ridanisaurus.emendatusenigmatica.loader.Validator.LOGGER;

public class MaterialArmorModel {
	public static final Codec<MaterialArmorModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.BOOL.optionalFieldOf("setArmor").forGetter(i -> Optional.of(i.setArmor)),
			Codec.list(EffectModel.CODEC).optionalFieldOf("effects").forGetter(i -> Optional.of(i.effects)),
			Codec.STRING.optionalFieldOf("setName").forGetter(i -> Optional.of(i.setName)),
			Codec.STRING.optionalFieldOf("setDesc").forGetter(i -> Optional.of(i.setDesc)),
			Codec.FLOAT.optionalFieldOf("toughness").forGetter(i -> Optional.of(i.toughness)),
			Codec.FLOAT.optionalFieldOf("knockback").forGetter(i -> Optional.of(i.knockback)),
			Codec.INT.optionalFieldOf("enchantability").forGetter(i -> Optional.of(i.enchantability)),
			ArmorModel.CODEC.optionalFieldOf("helmet").forGetter(i -> Optional.of(i.helmet)),
			ArmorModel.CODEC.optionalFieldOf("chestplate").forGetter(i -> Optional.of(i.chestplate)),
			ArmorModel.CODEC.optionalFieldOf("leggings").forGetter(i -> Optional.of(i.leggings)),
			ArmorModel.CODEC.optionalFieldOf("boots").forGetter(i -> Optional.of(i.boots)),
			ArmorModel.CODEC.optionalFieldOf("shield").forGetter(i -> Optional.of(i.shield))
	).apply(x, (setArmor, effects, setName, setDesc, toughness, knockback, enchantability, helmet, chestplate, leggings, boots, shield) -> new MaterialArmorModel(
			setArmor.orElse(false),
			effects.orElse(List.of()),
			setName.orElse(""),
			setDesc.orElse(""),
			toughness.orElse(0.0f),
			knockback.orElse(0.0f),
			enchantability.orElse(0),
			helmet.orElse(new ArmorModel()),
			chestplate.orElse(new ArmorModel()),
			leggings.orElse(new ArmorModel()),
			boots.orElse(new ArmorModel()),
			shield.orElse(new ArmorModel())
	)));

	private final boolean setArmor;
	private final List<EffectModel> effects;
	private final String setName;
	private final String setDesc;
	private final float toughness;
	private final float knockback;
	private final int enchantability;
	private final ArmorModel helmet;
	private final ArmorModel chestplate;
	private final ArmorModel leggings;
	private final ArmorModel boots;
	private final ArmorModel shield;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("setArmor", new Validator("setArmor").REQUIRES_BOOLEAN);
		validators.put("setName", new Validator("setName").getNonEmptyValidation(false));
		validators.put("setDesc", new Validator("setDesc").getNonEmptyValidation(false));
		validators.put("toughness", new Validator("toughness").REQUIRES_FLOAT);
		validators.put("enchantability", new Validator("enchantability").REQUIRES_INT);
		validators.put("knockback", new Validator("knockback").REQUIRES_FLOAT);
		validators.put("effects", new Validator("effects").getObjectValidation(EffectModel.validators, true));

		TriFunction<Validator, JsonElement, Path, Boolean> armorValidator = (validator, element, path) -> {
			if (!validator.assertParentObject(element, path)) return false;
			String armorPiece = validator.getName();
			JsonObject obj = element.getAsJsonObject();
			boolean required = false;

			if (!Validator.checkForTEMP(obj, path, false)) {
				LOGGER.error("Parent object is missing while validating \"%s\" in file \"%s\". Something is not right.".formatted(armorPiece, Validator.obfuscatePath(path)));
			} else {
				required = obj.get("TEMP").getAsJsonObject().get(armorPiece).getAsBoolean();
			}

			JsonElement valueJson = obj.get(armorPiece);

			if (!required) {
				if (Objects.isNull(valueJson)) return true;
				LOGGER.warn("\"%s\" should not be present when it's missing from \"processedTypes\" in file \"%s\".".formatted(armorPiece, Validator.obfuscatePath(path)));
			}

			return validator.getRequiredObjectValidation(ArmorModel.validators, false).apply(valueJson, path);
		};

		validators.put("helmet_rg",   	(element, path) -> armorValidator.apply(new Validator("helmet"), element, path));
		validators.put("chestplate_rg", (element, path) -> armorValidator.apply(new Validator("chestplate"), element, path));
		validators.put("leggings_rg",   (element, path) -> armorValidator.apply(new Validator("leggings"), element, path));
		validators.put("boots_rg",  	(element, path) -> armorValidator.apply(new Validator("boots"), element, path));
		validators.put("shield_rg",     (element, path) -> armorValidator.apply(new Validator("shield"), element, path));
	}

	public MaterialArmorModel(boolean setArmor, List<EffectModel> effects, String setName, String setDesc,
	                          float toughness, float knockback, int enchantability,
	                          ArmorModel helmet, ArmorModel chestplate, ArmorModel leggings, ArmorModel boots, ArmorModel shield) {
		this.setArmor = setArmor;
		this.effects = effects;
		this.setName = setName;
		this.setDesc = setDesc;
		this.toughness = toughness;
		this.knockback = knockback;
		this.enchantability = enchantability;
		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;
		this.shield = shield;
	}

	public MaterialArmorModel() {
		this.setArmor = false;
		this.effects = Collections.emptyList();
		this.setName = "";
		this.setDesc = "";
		this.toughness = 0.0f;
		this.knockback = 0.0f;
		this.enchantability = 0;
		this.helmet = new ArmorModel();
		this.chestplate = new ArmorModel();
		this.leggings = new ArmorModel();
		this.boots = new ArmorModel();
		this.shield = new ArmorModel();
	}

	public boolean isSetArmor() {
		return setArmor;
	}
	public List<EffectModel> getEffects() {
		return effects;
	}

	public String getSetName() {
		return setName;
	}

	public String getSetDesc() {
		return setDesc;
	}

	public float getToughness() {
		return toughness;
	}

	public float getKnockback() {
		return knockback;
	}

	public int getEnchantability() {
		return enchantability;
	}

	public ArmorModel getHelmet() {
		return helmet;
	}

	public ArmorModel getChestplate() {
		return chestplate;
	}

	public ArmorModel getLeggings() {
		return leggings;
	}

	public ArmorModel getBoots() {
		return boots;
	}

	public ArmorModel getShield() {
		return shield;
	}
}