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

package com.ridanisaurus.emendatusenigmatica.loader.parser.model.compat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class MaterialCreateModel {
	public static final Codec<MaterialCreateModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			CreateCrushingCompat.CODEC.optionalFieldOf("crushing").forGetter(i -> Optional.of(i.crushing))
	).apply(x, (crushing) -> new MaterialCreateModel(
			crushing.orElse(new CreateCrushingCompat())
	)));

	private final CreateCrushingCompat crushing;

	public MaterialCreateModel(CreateCrushingCompat crushing) {
		this.crushing = crushing;
	}

	public MaterialCreateModel() {
		this.crushing = new CreateCrushingCompat();
	}

	public CreateCrushingCompat getCrushingCompat() {
		return crushing;
	}
}