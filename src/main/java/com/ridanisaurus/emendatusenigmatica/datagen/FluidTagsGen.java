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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.registries.FluidHandler;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FluidTagsGen extends FluidTagsProvider {

	public FluidTagsGen(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Reference.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		// Fluids



//		Builder<Fluid> forgeFluids = tag(FluidTags.createOptional(new ResourceLocation(Reference.FORGE_TAG, "fluids")));
		FluidHandler.flowingFluidByMaterial.forEach((mat, fluid) -> {
//			forgeFluids.add(fluid.get());
//			Builder<Fluid> fluidTag = tag(FluidTags.bind(new ResourceLocation(Reference.FORGE_TAG, "fluids/" + mat).toString()));
			Builder<Fluid> moltenTag = tag(FluidTags.bind(new ResourceLocation(Reference.FORGE_TAG, "molten/" + mat).toString()));
			Builder<Fluid> forgeFluids = tag(FluidTags.bind(new ResourceLocation(Reference.FORGE_TAG, "molten_" + mat).toString()));
			moltenTag.add(fluid.get());
			forgeFluids.add(fluid.get());
		});
	}
}
