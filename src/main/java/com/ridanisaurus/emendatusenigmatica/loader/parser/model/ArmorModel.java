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

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class ArmorModel {
	public static final Codec<ArmorModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.INT.optionalFieldOf("protection").forGetter(i -> Optional.of(i.protection)),
			Codec.INT.optionalFieldOf("durability").forGetter(i -> Optional.of(i.durability))
	).apply(x, (protection, durability) -> new ArmorModel(
			protection.orElse(0),
			durability.orElse(1)
	)));

	private final int protection;
	private final int durability;

	public ArmorModel(int protection, int durability) {
		this.protection = protection;
		this.durability = durability;
	}

	public ArmorModel() {
		this.protection = 0;
		this.durability = 1;
	}

	public int getProtection() {
		return protection;
	}

	public int getDurability() {
		return durability;
	}
}