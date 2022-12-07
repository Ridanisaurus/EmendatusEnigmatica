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

import java.util.List;
import java.util.Optional;

public class CompatModel {
	public static final Codec<CompatModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("id").forGetter(i -> i.id),
			Codec.list(CompatRecipesModel.CODEC).fieldOf("recipes").forGetter(i -> i.recipes)
	).apply(x, CompatModel::new));

	private final String id;
	private final List<CompatRecipesModel> recipes;

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