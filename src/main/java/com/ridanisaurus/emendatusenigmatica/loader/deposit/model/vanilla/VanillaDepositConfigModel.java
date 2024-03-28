package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.DepositValidators;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public class VanillaDepositConfigModel {
	public static final Codec<VanillaDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block").orElse(null).forGetter(it -> Optional.ofNullable(it.block)),
			Codec.STRING.optionalFieldOf("material").orElse(null).forGetter(it -> Optional.ofNullable(it.material)),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
			Codec.INT.fieldOf("size").orElse(0).forGetter(it -> it.size),
			Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
			Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
			Codec.STRING.fieldOf("rarity").orElse("common").forGetter(it -> it.rarity)
	).apply(x, (s, s2, l, i, i2, i3, i4, s3, s4) -> new VanillaDepositConfigModel(s.orElse(null), s2.orElse(null), l, i, i2, i3, i4, s3, s4)));

	public final String block;
	public final String material;
	public final List<String> fillerTypes;
	public final int chance;
	public final int size;
	public final int minYLevel;
	public final int maxYLevel;
	public final String placement;
	public final String rarity;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("material_rg",	DepositValidators.getBlockMaterialValidation(new Validator("material")));
		validators.put("block", 		new Validator("block").getResourceIDValidation(false));
		validators.put("fillerTypes", 	new Validator("fillerTypes").getRequiredRegisteredIDValidation(DefaultConfigPlugin.STRATA_IDS, "Strata Registry", true));
		validators.put("chance", 		new Validator("chance").getRequiredIntRange(1, 100, false));
		validators.put("size", 			new Validator("size").getRequiredIntRange(1, 16, false));
		validators.put("minYLevel", 	new Validator("minYLevel").getRequiredIntRange(-64, 320, false));
		validators.put("maxYLevel_rg", 	new Validator("maxYLevel").getMaxYLevelValidation("minYLevel"));
		validators.put("placement", 	new Validator("placement").getAcceptsOnlyValidation(List.of("uniform", "triangle"), false));
		validators.put("rarity", 		new Validator("rarity").getAcceptsOnlyValidation(List.of("common", "rare"), false));
	}

	public VanillaDepositConfigModel(@Nullable String block, @Nullable String material, List<String> fillerTypes, int chance, int size, int minYLevel, int maxYLevel, String placement, String rarity) {
		this.block = block;
		this.material = material;
		this.fillerTypes = fillerTypes;
		this.chance = chance;
		this.size = size;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
		this.placement = placement;
		this.rarity = rarity;
	}
}
