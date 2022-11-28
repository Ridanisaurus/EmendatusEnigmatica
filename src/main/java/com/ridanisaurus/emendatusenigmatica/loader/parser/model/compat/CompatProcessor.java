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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompatProcessor {
	public static Map<String, List<CompatModel.CompatRecipesModel>> compatMap = new HashMap<>();
	public static Table<String, String, List<CompatModel.CompatValuesModel>> recipesTable = HashBasedTable.create();
	public static Table<String, List<CompatModel.CompatIOModel>, List<CompatModel.CompatIOModel>> valuesTable = HashBasedTable.create();
	public static Map<String, Table<String, Integer, Float>> inputMap = new HashMap<>();
	public static Map<String, Table<String, Integer, Float>> outputMap = new HashMap<>();

	public static void populate(CompatModel compat) {
		String id = compat.getId();
		List<CompatModel.CompatRecipesModel> recipes = compat.getRecipes();
		compatMap.put(id, recipes);

		for (CompatModel.CompatRecipesModel recipe : compat.getRecipes()) {
			String mod = recipe.getMod();
			String machine = recipe.getMachine();
			List<CompatModel.CompatValuesModel> values = recipe.getValues();
			recipesTable.put(mod, machine, values);

			for (CompatModel.CompatValuesModel value : values) {
				String type = value.getType();
				List<CompatModel.CompatIOModel> input = value.getInput();
				List<CompatModel.CompatIOModel> output = value.getOutput();
				valuesTable.put(type, input, output);

				ioTablePopulation(type, input, inputMap);
				ioTablePopulation(type, output, outputMap);
			}
		}
		System.out.println(compatMap);
		System.out.println(recipesTable);
		System.out.println(valuesTable);
		System.out.println(inputMap);
		System.out.println(outputMap);
	}

	private static void ioTablePopulation(String type, List<CompatModel.CompatIOModel> io, Map<String, Table<String, Integer, Float>> ioMap) {
		Table<String, Integer, Float> ioTable = HashBasedTable.create();
		for (CompatModel.CompatIOModel ioModel : io) {
			String item = ioModel.getItem();
			int count = ioModel.getCount();
			float chance = ioModel.getChance();

			ioTable.put(item, count, chance);
		}
		ioMap.put(type, ioTable);
	}
}
