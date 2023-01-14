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

package com.ridanisaurus.emendatusenigmatica.integration;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.EEDeposits;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.*;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// TODO: Remove sample check from Vanilla
public class WorldGenRecipeCategory implements IRecipeCategory<WorldGenRecipeCategory.WorldGenWrapper> {
	private static final ResourceLocation ID = new ResourceLocation(Reference.MOD_ID, "world_gen");
	public static final RecipeType<WorldGenRecipeCategory.WorldGenWrapper> RECIPE = new RecipeType<>(ID, WorldGenRecipeCategory.WorldGenWrapper.class);
	private final IGuiHelper guiHelper;
	private final Component title;

	public WorldGenRecipeCategory(IGuiHelper guiHelper, String title) {
		this.guiHelper = guiHelper;
		this.title = Component.translatable(title);
	}

	public static List<WorldGenWrapper> getWorldGenRecipes(MaterialModel material, IDepositProcessor activeProcessor) {
		List<WorldGenRecipeCategory.WorldGenWrapper> recipes = new ArrayList<>();
		if (material.getProcessedTypes().contains("ore")) {
			if (activeProcessor instanceof VanillaDepositProcessor) {
				var model = ((VanillaDepositProcessor) activeProcessor).getVanillaModel();
				String depositMaterial = model.getConfig().getMaterial();
				if (depositMaterial != null && depositMaterial.equals(material.getId())) {
					recipes.add(new WorldGenWrapper(material, activeProcessor));
				}
			} else if (activeProcessor instanceof GeodeDepositProcessor) {
				// TODO: Add support for Non-Materials
				var model = ((GeodeDepositProcessor) activeProcessor).getGeodeModel();
				List<CommonBlockDefinitionModel> innerBlocks = model.getConfig().getInnerBlocks();
				for (CommonBlockDefinitionModel innerBlock : innerBlocks) {
					String depositMaterial = innerBlock.getMaterial();
					if (depositMaterial != null && depositMaterial.equals(material.getId())) {
						recipes.add(new WorldGenWrapper(material, activeProcessor));
					}
				}
			} else if (activeProcessor instanceof SphereDepositProcessor) {
				var model = ((SphereDepositProcessor) activeProcessor).getSphereModel();
				List<CommonBlockDefinitionModel> blocks = model.getConfig().getBlocks();
				for (CommonBlockDefinitionModel block : blocks) {
					String depositMaterial = block.getMaterial();
					if (depositMaterial != null && depositMaterial.equals(material.getId())) {
						recipes.add(new WorldGenWrapper(material, activeProcessor));
					}
				}
			} else if (activeProcessor instanceof DenseDepositProcessor) {
				var model = ((DenseDepositProcessor) activeProcessor).getDenseModel();
				List<CommonBlockDefinitionModel> blocks = model.getConfig().getBlocks();
				for (CommonBlockDefinitionModel block : blocks) {
					String depositMaterial = block.getMaterial();
					if (depositMaterial != null && depositMaterial.equals(material.getId())) {
						recipes.add(new WorldGenWrapper(material, activeProcessor));
					}
				}
			} else if (activeProcessor instanceof DikeDepositProcessor) {
				var model = ((DikeDepositProcessor) activeProcessor).getDikeModel();
				List<CommonBlockDefinitionModel> blocks = model.getConfig().getBlocks();
				for (CommonBlockDefinitionModel block : blocks) {
					String depositMaterial = block.getMaterial();
					if (depositMaterial != null && depositMaterial.equals(material.getId())) {
						recipes.add(new WorldGenWrapper(material, activeProcessor));
					}
				}
			}
		}
		return recipes;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, WorldGenWrapper recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 6, 6)
				.addIngredients(Ingredient.of(EETags.MATERIAL_ORE.apply(recipe.material.getId())));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 39, 6)
				.addIngredients(Ingredient.of(recipe.material.getOreDefaultDrop()));
	}

	@Override
	public RecipeType<WorldGenWrapper> getRecipeType() {
		return RECIPE;
	}

	@Override
	public void draw(WorldGenWrapper recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrix, double mouseX, double mouseY) {
		String featureType = ChatFormatting.DARK_AQUA + "Type: " + ChatFormatting.DARK_GRAY + recipe.getTypeName();
		String minY = ChatFormatting.DARK_AQUA + "Min Y: " + ChatFormatting.DARK_GRAY + recipe.getMinY();
		String maxY = ChatFormatting.DARK_AQUA + "Max Y: " + ChatFormatting.DARK_GRAY + recipe.getMaxY();
		String chance = ChatFormatting.DARK_AQUA + "Chance: " + ChatFormatting.DARK_GRAY + recipe.getChance() + "%";
		Minecraft.getInstance().font.draw(matrix, featureType, 5, 30, 0);
		Minecraft.getInstance().font.draw(matrix, minY, 5, 30 + 12, 0);
		Minecraft.getInstance().font.draw(matrix, maxY, 75, 30 + 12, 0);
		Minecraft.getInstance().font.draw(matrix, chance, 5, 30 + 24, 0);

		RenderSystem.setShaderTexture(0, new ResourceLocation(Reference.MOD_ID, "textures/gui/world_gen.png"));

		if (recipe.getDimension().equals("Overworld")) {
			Minecraft.getInstance().screen.blit(matrix, 74, 8, 134, 0, 12, 12);
		} else if (recipe.getDimension().equals("The Nether")) {
			Minecraft.getInstance().screen.blit(matrix, 74, 8, 134, 12, 12, 12);
		} else if (recipe.getDimension().equals("The End")) {
			Minecraft.getInstance().screen.blit(matrix, 74, 8, 134, 24, 12, 12);
		} else {
			Minecraft.getInstance().screen.blit(matrix, 74, 8, 134, 36, 12, 12);
		}

		if (recipe.getBiomes().contains("#" + BiomeTags.IS_DEEP_OCEAN.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 0, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_OCEAN.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 12, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_BEACH.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 24, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_RIVER.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 36, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_MOUNTAIN.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 48, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_BADLANDS.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 60, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_HILL.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 72, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_TAIGA.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 84, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_JUNGLE.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 96, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_FOREST.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 108, 12, 12);
		} else if (recipe.getBiomes().contains("#" + BiomeTags.IS_SAVANNA.location().toString())) {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 120, 12, 12);
		} else {
			Minecraft.getInstance().screen.blit(matrix, 91, 8, 146, 132, 12, 12);
		}
		if (!recipe.getType().equals(EEDeposits.DepositType.VANILLA.getType())) {
			if (recipe.hasSurfaceSample()) {
				Minecraft.getInstance().screen.blit(matrix, 108, 8, 158, 0, 12, 12);
			} else {
				Minecraft.getInstance().screen.blit(matrix, 108, 8, 158, 12, 12, 12);
			}
		}
	}

	@Override
	public List<Component> getTooltipStrings(WorldGenWrapper recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		if (mouseX > 74 && mouseX < 86 && mouseY > 8 && mouseY < 20) {
			List<Component> dimension = new ArrayList<>();
			dimension.add(Component.literal(ChatFormatting.GOLD + "Dimension:"));
			dimension.add(Component.literal(recipe.getDimension()));
			return dimension;
		}
		if (mouseX > 91 && mouseX < 103 && mouseY > 8 && mouseY < 20) {
			List<Component> biomes = new ArrayList<>();
			biomes.add(Component.literal(ChatFormatting.GOLD + "Biomes:"));
			if (recipe.getBiomes().size() == 0) biomes.add(Component.literal("- Any"));
			else {
				for (String biome : recipe.getBiomes()) {
					biomes.add(Component.literal("- ").append(Component.literal(biome)));
				}
			}
			return biomes;
		}
		if (mouseX > 108 && mouseX < 120 && mouseY > 8 && mouseY < 20 && !recipe.getType().equals(EEDeposits.DepositType.VANILLA.getType())) {
			List<Component> surfaceSamples = new ArrayList<>();
			surfaceSamples.add(Component.literal(ChatFormatting.GOLD + "Generate Surface Samples:"));
			if (recipe.hasSurfaceSample()) {
				surfaceSamples.add(Component.literal("True"));
			} else {
				surfaceSamples.add(Component.literal("False"));
			}
			return surfaceSamples;
		}
		if (mouseX > 26 && mouseX < 35 && mouseY > 6 && mouseY < 22) {
			List<Component> oreDropInfo = new ArrayList<>();
			oreDropInfo.add(Component.literal(ChatFormatting.GOLD + "Ore Drop Info:"));
			oreDropInfo.add(Component.literal(ChatFormatting.DARK_AQUA + "Min: " + ChatFormatting.WHITE + recipe.material.getOreDrop().getMin()));
			oreDropInfo.add(Component.literal(ChatFormatting.DARK_AQUA + "Max: " + ChatFormatting.WHITE + recipe.material.getOreDrop().getMax()));
			return oreDropInfo;
		}
		if (mouseX > 5 && mouseX < 129 && mouseY > 42 && mouseY < 49) {
			List<Component> optimalY = new ArrayList<>();
			optimalY.add(Component.literal(ChatFormatting.GOLD + "Optimal Y:"));
			optimalY.add(Component.literal(String.valueOf((recipe.getMinY() + recipe.getMaxY()) / 2)));
			return optimalY;
		}
		return Collections.emptyList();
	}

	@Override
	public Component getTitle() {
		return title;
	}

	@Override
	public IDrawable getBackground() {
		return guiHelper.drawableBuilder(new ResourceLocation(Reference.MOD_ID, "textures/gui/world_gen.png"), 0, 0, 134, 66).addPadding(0, 0 ,0 ,0).build();
	}

	@Nullable
	@Override
	public IDrawable getIcon() {
		return guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.IRON_PICKAXE));
	}

	public static class WorldGenWrapper {
		private MaterialModel material;
		private IDepositProcessor activeProcessor;

		public WorldGenWrapper(MaterialModel material,IDepositProcessor activeProcessor) {
			this.material = material;
			this.activeProcessor = activeProcessor;
		}

		public String getType() {
			return activeProcessor.getType();
		}

		public String getTypeName() {
			return stripString(getType());
		}

		public CommonDepositModelBase getCommonModel() {
			return activeProcessor.getCommonModel();
		}

		public String getDeposit() {
			return stripString(getCommonModel().getName());
		}

		public String getDimension() {
			return stripString(getCommonModel().getDimension());
		}

		public List<String> getBiomes() {
			return getCommonModel().getBiomes();
		}

		public boolean hasSurfaceSample() {
			if (activeProcessor instanceof VanillaDepositProcessor) {
				var model = ((VanillaDepositProcessor) activeProcessor).getVanillaModel();
				return model.getConfig().getGenerateSamples();
			} else if (activeProcessor instanceof GeodeDepositProcessor) {
				var model = ((GeodeDepositProcessor) activeProcessor).getGeodeModel();
				return model.getConfig().getGenerateSamples();
			} else if (activeProcessor instanceof SphereDepositProcessor) {
				var model = ((SphereDepositProcessor) activeProcessor).getSphereModel();
				return model.getConfig().getGenerateSamples();
			} else if (activeProcessor instanceof DenseDepositProcessor) {
				var model = ((DenseDepositProcessor) activeProcessor).getDenseModel();
				return model.getConfig().getGenerateSamples();
			} else if (activeProcessor instanceof DikeDepositProcessor) {
				var model = ((DikeDepositProcessor) activeProcessor).getDikeModel();
				return model.getConfig().getGenerateSamples();
			} else {
				return false;
			}
		}

		public int getMinY() {
			if (activeProcessor instanceof VanillaDepositProcessor) {
				var model = ((VanillaDepositProcessor) activeProcessor).getVanillaModel();
				return model.getConfig().getMinYLevel();
			} else if (activeProcessor instanceof GeodeDepositProcessor) {
				var model = ((GeodeDepositProcessor) activeProcessor).getGeodeModel();
				return model.getConfig().getMinYLevel();
			} else if (activeProcessor instanceof SphereDepositProcessor) {
				var model = ((SphereDepositProcessor) activeProcessor).getSphereModel();
				return model.getConfig().getMinYLevel();
			} else if (activeProcessor instanceof DenseDepositProcessor) {
				var model = ((DenseDepositProcessor) activeProcessor).getDenseModel();
				return model.getConfig().getMinYLevel();
			} else if (activeProcessor instanceof DikeDepositProcessor) {
				var model = ((DikeDepositProcessor) activeProcessor).getDikeModel();
				return model.getConfig().getMinYLevel();
			} else {
				return -1;
			}
		}

		public int getMaxY() {
			if (activeProcessor instanceof VanillaDepositProcessor) {
				var model = ((VanillaDepositProcessor) activeProcessor).getVanillaModel();
				return model.getConfig().getMaxYLevel();
			} else if (activeProcessor instanceof GeodeDepositProcessor) {
				var model = ((GeodeDepositProcessor) activeProcessor).getGeodeModel();
				return model.getConfig().getMaxYLevel();
			} else if (activeProcessor instanceof SphereDepositProcessor) {
				var model = ((SphereDepositProcessor) activeProcessor).getSphereModel();
				return model.getConfig().getMaxYLevel();
			} else if (activeProcessor instanceof DenseDepositProcessor) {
				var model = ((DenseDepositProcessor) activeProcessor).getDenseModel();
				return model.getConfig().getMaxYLevel();
			} else if (activeProcessor instanceof DikeDepositProcessor) {
				var model = ((DikeDepositProcessor) activeProcessor).getDikeModel();
				return model.getConfig().getMaxYLevel();
			} else {
				return -1;
			}
		}

		public int getChance() {
			if (activeProcessor instanceof VanillaDepositProcessor) {
				var model = ((VanillaDepositProcessor) activeProcessor).getVanillaModel();
				return model.getConfig().getChance();
			} else if (activeProcessor instanceof GeodeDepositProcessor) {
				var model = ((GeodeDepositProcessor) activeProcessor).getGeodeModel();
				return model.getConfig().getChance();
			} else if (activeProcessor instanceof SphereDepositProcessor) {
				var model = ((SphereDepositProcessor) activeProcessor).getSphereModel();
				return model.getConfig().getChance();
			} else if (activeProcessor instanceof DenseDepositProcessor) {
				var model = ((DenseDepositProcessor) activeProcessor).getDenseModel();
				return model.getConfig().getChance();
			} else if (activeProcessor instanceof DikeDepositProcessor) {
				var model = ((DikeDepositProcessor) activeProcessor).getDikeModel();
				return model.getConfig().getChance();
			} else {
				return -1;
			}
		}

		private String stripString(String input) {
			int index = input.indexOf(":");
			if (index != -1) {
				input = input.substring(index + 1);
			}
			input = input.replaceAll("_", " ");
//			input = input.substring(0, 1).toUpperCase() + input.substring(1);
			String[] words = input.split(" ");
			StringBuilder sb = new StringBuilder();
			for (String word : words) {
				sb.append(Character.toUpperCase(word.charAt(0)));
				sb.append(word.substring(1));
				sb.append(" ");
			}
			return input = sb.toString().trim();
		}
	}
}
