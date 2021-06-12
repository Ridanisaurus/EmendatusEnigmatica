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

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class FluidHandler {

	private String name;
	private RegistryObject<ForgeFlowingFluid> source;
	private RegistryObject<ForgeFlowingFluid> flowing;
	private RegistryObject<FlowingFluidBlock> fluidblock;
	private RegistryObject<Item> bucket;

	public static final ResourceLocation FLUID_STILL_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_still");
	public static final ResourceLocation FLUID_FLOWING_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_flow");
	public static final ResourceLocation FLUID_OVERLAY_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_overlay");

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MOD_ID);

	public static Map<String, RegistryObject<ForgeFlowingFluid>> flowingFluidByMaterial = new HashMap<>();
	public static Map<String, RegistryObject<FlowingFluidBlock>> fluidBlockByMaterial = new HashMap<>();
	public static Map<String, RegistryObject<Item>> fluidBucketByMaterial = new HashMap<>();

	public static ForgeFlowingFluid.Properties makeProperties(FluidAttributes.Builder attributeBuilder, Supplier<ForgeFlowingFluid> still, Supplier<ForgeFlowingFluid> flowing, Supplier<Item> bucket, Supplier<FlowingFluidBlock> block)
	{
		return new ForgeFlowingFluid.Properties(still, flowing,attributeBuilder)
				.bucket(bucket).block(block);
	}

	public static FluidAttributes.Builder createAttributes(int color) {
		return FluidAttributes.builder(FLUID_STILL_RL, FLUID_FLOWING_RL)
				.color(color)
				.overlay(FLUID_OVERLAY_RL)
				.rarity(Rarity.COMMON)
				.density(3000)
				.viscosity(6000)
				.temperature(1300)
				.sound(SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundEvents.ITEM_BUCKET_EMPTY_LAVA)
				.luminosity(15);
	}

	public FluidHandler(String name, int color) {
		this.name = name;
		source = FLUIDS.register("molten_" + name, () -> new ForgeFlowingFluid.Source(
				makeProperties(createAttributes(color), source, flowing, bucket, fluidblock))
		);
		flowing = FLUIDS.register("molten_" + name + "_flowing", () -> new ForgeFlowingFluid.Flowing(
				makeProperties(createAttributes(color), source, flowing, bucket, fluidblock))
		);
		fluidblock = BlockHandler.BLOCKS.register("molten_" + name, () ->
				new FlowingFluidBlock(source,
					Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops())
		);
		bucket = ItemHandler.ITEMS.register("molten_" + name + "_bucket", () ->
				new BucketItem(source,
						new Item.Properties().group(EmendatusEnigmatica.TAB).maxStackSize(1))
		);
		flowingFluidByMaterial.put(name, source);
		fluidBlockByMaterial.put(name, fluidblock);
		fluidBucketByMaterial.put(name, bucket);
	}

	public static void fluifInit() {
		for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
			for (Materials material : Materials.values()) {
				List<String> toCreate = Arrays.asList(material.type);

				if (processedMaterial == ProcessedMaterials.FLUID && toCreate.contains("Fluid")) {
					new FluidHandler(material.id, material.tintColour);
				}

			}
		}
	}
}
