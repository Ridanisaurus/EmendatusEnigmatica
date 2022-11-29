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

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompatProcessor {
	public final String id;
	public String mod;
	public String machine;
	public String type;
	public List<CompatModel.CompatIOModel> inputs = new ArrayList<>();
	public Table<String, Integer, Float> inputTable = HashBasedTable.create();
	public List<CompatModel.CompatIOModel> outputs = new ArrayList<>();
	public Table<String, Integer, Float> outputTable = HashBasedTable.create();

	public CompatProcessor(CompatModel compat) {
		this.id = compat.getId();
		List<CompatModel.CompatRecipesModel> recipes = compat.getRecipes();
		recipes.forEach(recipe -> {
			this.mod = recipe.getMod();
			this.machine = recipe.getMachine();
			List<CompatModel.CompatValuesModel> values = recipe.getValues();

			values.forEach(value -> {
				this.type = value.getType();
				this.inputs = value.getInput();
				this.outputs = value.getOutput();

				this.inputs.forEach(input -> {
					String item = input.getItem();
					int count = input.getCount();
					float chance = input.getChance();
					this.inputTable.put(item, count, chance);
				});

				this.outputs.forEach(output -> {
					String item = output.getItem();
					int count = output.getCount();
					float chance = output.getChance();
					this.outputTable.put(item, count, chance);
				});
			});
		});
		System.out.println("Recipe ID: " + this.id);
		System.out.println("Mod: " + this.mod + " - " + this.machine);
		System.out.println("Type: " + this.type);
		System.out.println("Inputs: " + this.inputs);
		System.out.println("Input Table: " + this.inputTable);
		System.out.println("Outputs: " + this.outputs);
		System.out.println("Output Table: " + this.outputTable);
		System.out.println();
	}

	public String getId() {
		return id;
	}

	public String getMod() {
		return mod;
	}

	public String getMachine() {
		return machine;
	}

	public String getType() {
		return type;
	}

	public List<CompatModel.CompatIOModel> getInput() {
		return inputs;
	}

	public Table<String, Integer, Float> getInputTable() {
		return inputTable;
	}

	public List<CompatModel.CompatIOModel> getOutput() {
		return outputs;
	}

	public Table<String, Integer, Float> getOutputTable() {
		return outputTable;
	}
}