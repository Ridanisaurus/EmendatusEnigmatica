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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static com.ridanisaurus.emendatusenigmatica.loader.Validator.LOGGER;

public class MaterialOreDropModel {
	public static final Codec<MaterialOreDropModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("drop").forGetter(i -> Optional.ofNullable(i.drop)),
			Codec.INT.optionalFieldOf("min").forGetter(i -> Optional.of(i.min)),
			Codec.INT.optionalFieldOf("max").forGetter(i -> Optional.of(i.max)),
			Codec.BOOL.optionalFieldOf("uniformCount").forGetter(i -> Optional.of(i.uniformCount))
	).apply(x, (drop, min, max, uniformCount) -> new MaterialOreDropModel(
			drop.orElse(""),
			min.orElse(1),
			max.orElse(1),
			uniformCount.orElse(false)
	)));

	private final String drop;
	private final int min;
	private final int max;
	private final boolean uniformCount;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static final Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		Validator dropValidator = new Validator("drop");
		validators.put("drop_rg", (element, path) -> {
			if (!dropValidator.assertParentObject(element, path)) return false;

			JsonObject obj = element.getAsJsonObject();
			boolean required = false;

			if (!Validator.checkForTEMP(obj, path, false)) {
				LOGGER.warn("Parent data is missing while verifying \"%s\" in file \"%s\", something is not right.".formatted(dropValidator.getName(), Validator.obfuscatePath(path)));
			} else {
				JsonElement requiredJson = obj.get("TEMP").getAsJsonObject().get("DROP_REQUIRED");
				if (Objects.isNull(requiredJson)) {
					LOGGER.warn("Parent data doesn't contain required information for proper validation of \"%s\" in file \"%s\", something is not right.".formatted(dropValidator.getName(), Validator.obfuscatePath(path)));
				} else {
					required = requiredJson.getAsBoolean();
				}
			}

			JsonElement value = obj.get(dropValidator.getName());
			return required? dropValidator.RESOURCE_ID_REQUIRED.apply(value, path): dropValidator.RESOURCE_ID.apply(value, path);
		});

		validators.put("min", new Validator("min").getIntRange(0, Integer.MAX_VALUE, false));

		Validator maxValidator = new Validator("max");
		validators.put("max_rg", (jsonElement, path) -> {
			if (!maxValidator.assertParentObject(jsonElement, path)) return false;
			JsonObject obj = jsonElement.getAsJsonObject();
			int min = 0;
			if (Objects.nonNull(obj.get("min"))) {
				try {
					min = obj.get("min").getAsInt();
				} catch (ClassCastException e) {
					LOGGER.error("\"min\" requested while validating \"max\" in file \"%s\" is not a numeric value!");
				}
			}
			return maxValidator.getIntRange(min, Integer.MAX_VALUE, false).apply(obj.get("max"), path);
		});

		validators.put("uniformCount", new Validator("uniformCount").REQUIRES_BOOLEAN);
	}

	public MaterialOreDropModel(String drop, int min, int max, boolean uniformCount) {
		this.drop = drop;
		this.min = min;
		this.max = max;
		this.uniformCount = uniformCount;
	}

	public MaterialOreDropModel() {
		this.drop = "";
		this.min = 1;
		this.max = 1;
		this.uniformCount = false;
	}

	public String getDrop() {
		return drop;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public boolean isUniformCount() {
		return uniformCount;
	}

	public ItemLike getDefaultItemDropAsItem() {
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(drop));
	}
}