package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.EEDeposits;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.DepositValidators;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static com.ridanisaurus.emendatusenigmatica.loader.Validator.LOGGER;

public class CommonBlockDefinitionModel {
	public static final Codec<CommonBlockDefinitionModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block").forGetter(it -> Optional.ofNullable(it.block)),
			Codec.STRING.optionalFieldOf("tag").forGetter(it -> Optional.ofNullable(it.tag)),
			Codec.STRING.optionalFieldOf("material").forGetter(it -> Optional.ofNullable(it.material)),
			Codec.INT.fieldOf("weight").orElse(100).forGetter(it -> it.weight),
			Codec.INT.fieldOf("min").orElse(-500).forGetter(it -> it.min),
			Codec.INT.fieldOf("max").orElse(500).forGetter(it -> it.max)
	).apply(x, (s, s2, s3, i, i2, i3) -> new CommonBlockDefinitionModel(s.orElse(null), s2.orElse(null), s3.orElse(null), i, i2, i3)));
	protected final String block;
	protected final String tag;
	private final String material;
	protected final int weight;
	protected final int min;
	protected final int max;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 * @implNote Validators of min/max are using simplified temp check for checking if the type is dike.<br>
	 * If you need to pass parent to those validators for any other reason,
	 * modify this implementation to check for specific field for those validators!.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("block", 	new Validator("block").getResourceIDValidation(false));
		validators.put("tag", 		new Validator("tag").getResourceIDValidation(false));
		validators.put("weight", 	new Validator("weight").REQUIRES_INT);
		validators.put("material_rg", DepositValidators.getFullMaterialValidation(new Validator("material")));

		Validator minValidator = new Validator("min");
		Validator maxValidator = new Validator("max");
		validators.put("min_rg", (element, path) -> {
			if (!minValidator.assertParentObject(element, path)) return false;
			JsonObject parent = element.getAsJsonObject();
			JsonElement value = parent.get(minValidator.getName());

			if (Objects.isNull(value)) return true;
			if (!Validator.checkForTEMP(parent, path, false)) {
				LOGGER.warn(
					"\"%s\" should not be present in file \"%s\", as it doesn't take effect on types different than \"%s\"!"
					.formatted(minValidator.getName(), Validator.obfuscatePath(path), EEDeposits.DepositType.DIKE.getType())
				);
			}

			return minValidator.REQUIRES_INT.apply(value, path);
		});
		validators.put("max_rg", (element, path) -> {
			if (!maxValidator.assertParentObject(element, path)) return false;
			JsonObject parent = element.getAsJsonObject();
			JsonElement value = parent.get(minValidator.getName());

			if (Objects.isNull(value)) return true;
			if (!Validator.checkForTEMP(parent, path, false)) {
				LOGGER.warn(
					"\"%s\" should not be present in file \"%s\", as it doesn't take effect on types different than \"%s\"!"
					.formatted(maxValidator.getName(), Validator.obfuscatePath(path), EEDeposits.DepositType.DIKE.getType())
				);
			}

			return maxValidator.getMaxYLevelValidation("min").apply(parent, path);
		});
	}

	public CommonBlockDefinitionModel(@Nullable String block, @Nullable String tag, @Nullable String material, int weight, int min, int max) {
		this.block = block;
		this.tag = tag;
		this.material = material;
		this.weight = weight;
		this.min = min;
		this.max = max;
	}

	public @Nullable String getBlock() {
		return block;
	}

	public @Nullable String getTag() {
		return tag;
	}

	public @Nullable String getMaterial() {
		return material;
	}

	public int getWeight() {
		return weight;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
}