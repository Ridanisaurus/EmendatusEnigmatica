package com.ridanisaurus.eearsnouveauaddon;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EERecipeProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericRecipe;
import com.ridanisaurus.emendatusenigmatica.datagen.base.RecipeBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class EEArsNouveauDataGen {
	public static class Recipes extends EERecipeProvider {

		private final EmendatusDataRegistry registry;

		public Recipes(DataGenerator gen, EmendatusDataRegistry registry) {
			super(gen);
			this.registry = registry;
		}

		@Override
		protected void buildRecipes(Consumer<IFinishedGenericRecipe> consumer) {
			for (MaterialModel material : registry.getMaterials()) {
				if (material.getCompat().getANCompat()) {
					List<String> processedType = material.getProcessedTypes();
					for (StrataModel stratum : registry.getStrata()) {
						if (processedType.contains("dust") && processedType.contains("ore")) {
							// Dust from Ore - Crush Spell
							new RecipeBuilder("output")
									.forceOutputArray(true)
									.type("ars_nouveau:crush")
									.group("emendatusenigmatica:compat_recipe")
									.fieldJson("input", new RecipeBuilder.JsonItemBuilder(false)
											.stack(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()))
									.addOutput(builder -> builder
											.stackWithChance(EERegistrar.dustMap.get(material.getId()).get(), 2, 1)
											.stackWithChance((ForgeRegistries.ITEMS.getValue(stratum.getFillerType()) == Items.AIR ? Items.COBBLESTONE : ForgeRegistries.ITEMS.getValue(stratum.getFillerType())), 1, 1))
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore_crush_spell/" + material.getId() + "_" + stratum.getId()));
						}
					}
				}
			}
		}

		@Override
		public String getName() {
			return "EE Ars Nouveau Addon Recipes";
		}
	}
}
