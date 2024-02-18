package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.DepositValidators;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

public class SampleBlockDefinitionModel {
	public static final Codec<SampleBlockDefinitionModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block").forGetter(it -> Optional.ofNullable(it.block)),
			Codec.STRING.optionalFieldOf("tag").forGetter(it -> Optional.ofNullable(it.tag)),
			Codec.STRING.optionalFieldOf("material").forGetter(it -> Optional.ofNullable(it.material)),
			Codec.INT.fieldOf("weight").orElse(100).forGetter(it -> it.weight),
			Codec.STRING.optionalFieldOf("strata").forGetter(it -> Optional.ofNullable(it.strata))
	).apply(x, (s, s2, s3, i, s4) -> new SampleBlockDefinitionModel(s.orElse(null), s2.orElse(null), s3.orElse(null), i, s4.orElse(null))));
	protected final String block;
	protected final String tag;
	private final String material;
	protected final int weight;
	private final String strata;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("block", 	new Validator("block").getResourceIDValidation(false));
		validators.put("tag", 		new Validator("tag").getResourceIDValidation(false));
		validators.put("weight", 	new Validator("weight").REQUIRES_INT);
		validators.put("material_rg", DepositValidators.getFullMaterialValidation(new Validator("material")));

		Validator strataValidator = new Validator("strata");
		validators.put("strata_rg", (element, path) -> {
			if (!strataValidator.assertParentObject(element, path)) return false;
			JsonObject parent = element.getAsJsonObject();
			if (Objects.isNull(parent.get("material"))) {
				Validator.LOGGER.warn(
					"\"%s\" should not be present when specified sample is not material based in file \"%s\"."
					.formatted(strataValidator.getName(), Validator.obfuscatePath(path))
				);
			}
			return strataValidator.getRegisteredIDValidation(DefaultConfigPlugin.STRATA_IDS, "Strata Registry", false).apply(parent.get(strataValidator.getName()), path);
		});
	}

	public SampleBlockDefinitionModel(@Nullable String block, @Nullable String tag, @Nullable String material, int weight, @Nullable String strata) {
		this.block = block;
		this.tag = tag;
		this.weight = weight;
		this.material = material;
		this.strata = strata;
	}

	public int getWeight() {
		return weight;
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

	public @Nullable String getStrata() {
		return strata;
	}
}