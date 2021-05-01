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

package com.ridanisaurus.emendatusenigmatica.registries;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.List;

public class SlurryHandler {
	public static final DeferredRegister<Slurry> SLURRIES = DeferredRegister.create(Slurry.class, Reference.MOD_ID);

	public static Table<ProcessedMaterials, Materials, RegistryObject<Slurry>> backingSlurryTable;

	public static void slurryInit() {
		ImmutableTable.Builder<ProcessedMaterials, Materials, RegistryObject<Slurry>> builder = new ImmutableTable.Builder<>();
		for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
			for (Materials material : Materials.values()) {
				List<String> toCreate = Arrays.asList(material.type);
				if (processedMaterial == ProcessedMaterials.CLEAN_SLURRY && toCreate.contains("CleanSlurry")) {
					builder.put(processedMaterial, material, SLURRIES.register("clean_" + material.id, () -> {
						ResourceLocation ore = new ResourceLocation("forge", "ores/" + material.id);
						return new Slurry(SlurryBuilder.clean().ore(ore).color(material.tintColour));
					}));
				}
				if (processedMaterial == ProcessedMaterials.DIRTY_SLURRY && toCreate.contains("DirtySlurry")) {
					builder.put(processedMaterial, material, SLURRIES.register("dirty_" + material.id, () -> {
						ResourceLocation ore = new ResourceLocation("forge", "ores/" + material.id);
						return new Slurry(SlurryBuilder.dirty().ore(ore).color(material.tintColour));
					}));
				}
			}
		}
		backingSlurryTable = builder.build();
	}
}
