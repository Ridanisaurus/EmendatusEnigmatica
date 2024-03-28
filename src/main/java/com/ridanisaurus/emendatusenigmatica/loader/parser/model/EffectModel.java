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
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public class EffectModel {
	public static final Codec<EffectModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("effect").forGetter(i -> Optional.of(i.effect)),
			Codec.INT.optionalFieldOf("level").forGetter(i -> Optional.of(i.level)),
			Codec.BOOL.optionalFieldOf("showIcon").forGetter(i -> Optional.of(i.showIcon)),
			Codec.BOOL.optionalFieldOf("showParticles").forGetter(i -> Optional.of(i.showParticles))
	).apply(x, (effect, level, showIcon, showParticles) -> new EffectModel(
			effect.orElse(""),
			level.orElse(1),
			showIcon.orElse(true),
			showParticles.orElse(true)
	)));

	private final String effect;
	private final int level;
	private final boolean showIcon;
	private final boolean showParticles;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("effect", new Validator("effect").getRequiredResourceIDValidation(false));
		validators.put("level", new Validator("level").REQUIRES_INT);
		validators.put("showIcon", new Validator("showIcon").REQUIRES_BOOLEAN);
		validators.put("showParticles", new Validator("showParticles").REQUIRES_BOOLEAN);
	}

	public EffectModel(String effect, int level, boolean showIcon, boolean showParticles) {
		this.effect = effect;
		this.level = level;
		this.showIcon = showIcon;
		this.showParticles = showParticles;
	}

	public EffectModel() {
		this.effect = "";
		this.level = 1;
		this.showIcon = true;
		this.showParticles = true;
	}

	public MobEffect getEffect() {
		return ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(effect));
	}

	public int getLevel() {
		return level - 1;
	}

	public boolean isShowIcon() {
		return showIcon;
	}

	public boolean isShowParticles() {
		return showParticles;
	}
}