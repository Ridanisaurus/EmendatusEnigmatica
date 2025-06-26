package com.ridanisaurus.eemekanismaddon;

import com.google.common.collect.Lists;
//import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
//import com.ridanisaurus.emendatusenigmatica.datagen.base.*;
//import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
//import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
//import com.ridanisaurus.emendatusenigmatica.registries.EETags;
//import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.infuse.InfusionStack;
import mekanism.api.datagen.recipe.builder.*;
import mekanism.api.recipes.ItemStackToInfuseTypeRecipe;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.DataGenerator;
//import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Consumer;

public class EEMekanismDataGen {
//	private static final Gas SULFURIC_ACID = Gas.getFromRegistry(new ResourceLocation(EEMekanismAddon.MEKANISM, "sulfuric_acid"));
//	private static final Gas HYDROGEN_CHLORIDE = Gas.getFromRegistry(new ResourceLocation(EEMekanismAddon.MEKANISM, "hydrogen_chloride"));
//	private static final Gas OXYGEN = Gas.getFromRegistry(new ResourceLocation(EEMekanismAddon.MEKANISM, "oxygen"));
//
//	public static class Recipes extends RecipeProvider {
//
//		private final EmendatusDataRegistry registry;
//
//		public Recipes(DataGenerator gen, EmendatusDataRegistry registry) {
//			super(gen);
//			this.registry = registry;
//		}
//
//		@Override
//		protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
//			for (MaterialModel material : registry.getMaterials()) {
//				if (material.getCompat().getMeknaismCompat()) {
//					List<String> processedType = material.getProcessedTypes();
//					if (processedType.contains("slurry")) {
//						if (processedType.contains("ore")) {
//							// Dirty Slurry from Ore in the Dissolution Chamber
//							ChemicalDissolutionRecipeBuilder.dissolution(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_ORE.apply(material.getId())),
//									IngredientCreatorAccess.gas().from(SULFURIC_ACID, 1),
//									EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getStack(1_000)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "slurry/dirty/from_ore/" + material.getId()));
//						}
//						if (processedType.contains("raw")) {
//							// Dirty Slurry from Raw Material in the Dissolution Chamber
//							ChemicalDissolutionRecipeBuilder.dissolution(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW.apply(material.getId()), 3),
//									IngredientCreatorAccess.gas().from(SULFURIC_ACID, 1),
//									EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getStack(2_000)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "slurry/dirty/from_raw/" + material.getId()));
//							// Dirty Slurry from Raw Storage Block in the Dissolution Chamber
//							ChemicalDissolutionRecipeBuilder.dissolution(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(material.getId())),
//									IngredientCreatorAccess.gas().from(SULFURIC_ACID, 2),
//									EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getStack(6_000)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "slurry/dirty/from_raw_block/" + material.getId()));
//						}
//						// Clean Slurry from Dirty Slurry in the Chemical Washer
//						FluidSlurryToSlurryRecipeBuilder.washing(
//								IngredientCreatorAccess.fluid().from(FluidTags.WATER, 5),
//								IngredientCreatorAccess.slurry().from(EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get(), 1),
//								EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get().getStack(1)
//						).build(consumer, new ResourceLocation(Reference.MOD_ID, "slurry/clean/" + material.getId()));
//					}
//					if (processedType.contains("crystal") && processedType.contains("slurry")) {
//						// Crystal from Clean Slurry
//						ChemicalCrystallizerRecipeBuilder.crystallizing(
//								IngredientCreatorAccess.slurry().from(EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get(), 200),
//								getItemStack(EEMekanismRegistrar.crystalMap.get(material.getId()).get())
//						).build(consumer, new ResourceLocation(Reference.MOD_ID, "crystal/from_slurry/" + material.getId()));
//
//					}
//					if (processedType.contains("shard")) {
//						if (processedType.contains("crystal")) {
//							// Shard from Crystal
//							ItemStackChemicalToItemStackRecipeBuilder.injecting(
//									IngredientCreatorAccess.item().from(EEMekanismTags.MATERIAL_CRYSTAL.apply(material.getId())),
//									IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 1),
//									getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get())
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_crystal/" + material.getId()));
//						}
//						if (processedType.contains("ore")) {
//							// Shard from Ore
//							ItemStackChemicalToItemStackRecipeBuilder.injecting(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_ORE.apply(material.getId())),
//									IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 1),
//									getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get(), 4)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_ore/" + material.getId()));
//						}
//						if (processedType.contains("raw")) {
//							// Shard from Raw Material
//							ItemStackChemicalToItemStackRecipeBuilder.injecting(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW.apply(material.getId()), 3),
//									IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 1),
//									getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get(), 8)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_raw/" + material.getId()));
//							// Shard from Raw Block
//							ItemStackChemicalToItemStackRecipeBuilder.injecting(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(material.getId())),
//									IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 2),
//									getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get(), 24)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_raw_block/" + material.getId()));
//						}
//					}
//					if (processedType.contains("clump")) {
//						if (processedType.contains("shard")) {
//							// Clump from Shard
//							ItemStackChemicalToItemStackRecipeBuilder.purifying(
//									IngredientCreatorAccess.item().from(EEMekanismTags.MATERIAL_SHARD.apply(material.getId())),
//									IngredientCreatorAccess.gas().from(OXYGEN, 1),
//									getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get())
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_shard/" + material.getId()));
//						}
//						if (processedType.contains("ore")) {
//							// Clump from Ore
//							ItemStackChemicalToItemStackRecipeBuilder.purifying(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_ORE.apply(material.getId())),
//									IngredientCreatorAccess.gas().from(OXYGEN, 1),
//									getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), 3)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_ore/" + material.getId()));
//						}
//						if (processedType.contains("raw")) {
//							// Clump from Raw Material
//							ItemStackChemicalToItemStackRecipeBuilder.purifying(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW.apply(material.getId())),
//									IngredientCreatorAccess.gas().from(OXYGEN, 1),
//									getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), 2)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_raw/" + material.getId()));
//							// Clump from Raw Block
//							ItemStackChemicalToItemStackRecipeBuilder.purifying(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(material.getId())),
//									IngredientCreatorAccess.gas().from(OXYGEN, 2),
//									getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), 18)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_raw_block/" + material.getId()));
//						}
//					}
//					if (processedType.contains("dirty_dust") && processedType.contains("clump")) {
//						// Dirty Dust from Clump
//						ItemStackToItemStackRecipeBuilder.crushing(
//								IngredientCreatorAccess.item().from(EEMekanismTags.MATERIAL_CLUMP.apply(material.getId())),
//								getItemStack(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get())
//						).build(consumer, new ResourceLocation(Reference.MOD_ID, "dirty_dust/from_clump/" + material.getId()));
//					}
//					if (processedType.contains("dust") && processedType.contains("dirty_dust")) {
//						// Dust from Dirty Dust
//						ItemStackToItemStackRecipeBuilder.enriching(
//								IngredientCreatorAccess.item().from(EEMekanismTags.MATERIAL_DIRTY_DUST.apply(material.getId())),
//								getItemStack(EERegistrar.dustMap.get(material.getId()).get())
//						).build(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_dirty_dust/" + material.getId()));
//					}
//					if (processedType.contains("infuse_type")) {
//						// Enriching
//						if (material.isModded() && material.getProperties().getMaterialType().equals("metal")) {
//							ItemStackToItemStackRecipeBuilder.enriching(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_INGOT.apply(material.getId())),
//									getItemStack(EEMekanismRegistrar.enrichedMap.get(material.getId()).get())
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "enriched/from_ingot/" + material.getId()));
//						}
//						if (material.isModded() && material.getProperties().getMaterialType().equals("gem")) {
//							ItemStackToItemStackRecipeBuilder.enriching(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_GEM.apply(material.getId())),
//									getItemStack(EEMekanismRegistrar.enrichedMap.get(material.getId()).get())
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "enriched/from_gem/" + material.getId()));
//						}
//						// Infusion
//						if (processedType.contains("dust")) {
//							ItemStackToChemicalRecipeBuilder.infusionConversion(
//									IngredientCreatorAccess.item().from(EETags.MATERIAL_DUST.apply(material.getId())),
//									new InfusionStack(EEMekanismRegistrar.infuseMap.get(material.getId()).get(), 10)
//							).build(consumer, new ResourceLocation(Reference.MOD_ID, "infuse_type/from_dust/" + material.getId()));
//						}
//						ItemStackToChemicalRecipeBuilder.infusionConversion(
//								IngredientCreatorAccess.item().from(EEMekanismTags.MATERIAL_ENRICHED.apply(material.getId())),
//								new InfusionStack(EEMekanismRegistrar.infuseMap.get(material.getId()).get(), 80)
//						).build(consumer, new ResourceLocation(Reference.MOD_ID, "infuse_type/from_enriched/" + material.getId()));
//					}
//				}
//			}
//		}
//
//		private ItemStack getItemStack(Item item, int size) {
//			return new ItemStack(item, size);
//		}
//
//		private ItemStack getItemStack(Item item) {
//			return getItemStack(item, 1);
//		}
//
//		@Override
//		public String getName() {
//			return "EE Mekanism Addon Recipes";
//		}
//	}
//
//	public static class ItemModels extends EEItemModelProvider {
//
//		private final EmendatusDataRegistry registry;
//
//		public ItemModels(DataGenerator generator, EmendatusDataRegistry registry) {
//			super(generator);
//			this.registry = registry;
//		}
//
//		@Override
//		protected void buildItemModels(Consumer<IFinishedGenericJSON> consumer) {
//			for (MaterialModel material : registry.getMaterials()) {
//				List<String> processedType = material.getProcessedTypes();
//				// Crystals
//				if (processedType.contains("crystal")) {
//					ItemModelBuilder crystalBuilder = new ItemModelBuilder("minecraft:item/generated");
//					if (material.getColors().getMaterialColor() == -1) {
//						crystalBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_crystal").toString());
//					} else {
//						crystalBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/00").toString())
//								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/01").toString())
//								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/02").toString())
//								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/03").toString())
//								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/04").toString());
//					}
//					crystalBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_crystal"));
//				}
//				// Shards
//				if (processedType.contains("shard")) {
//					ItemModelBuilder shardBuilder = new ItemModelBuilder("minecraft:item/generated");
//					if (material.getColors().getMaterialColor() == -1) {
//						shardBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_shard").toString());
//					} else {
//						shardBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/00").toString())
//								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/01").toString())
//								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/02").toString())
//								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/03").toString())
//								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/04").toString());
//					}
//					shardBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_shard"));
//				}
//				// Clumps
//				if (processedType.contains("clump")) {
//					ItemModelBuilder clumpBuilder = new ItemModelBuilder("minecraft:item/generated");
//					if (material.getColors().getMaterialColor() == -1) {
//						clumpBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_clump").toString());
//					} else {
//						clumpBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/00").toString())
//								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/01").toString())
//								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/02").toString())
//								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/03").toString())
//								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/04").toString());
//					}
//					clumpBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_clump"));
//				}
//				// Dirty Dusts
//				if (processedType.contains("dirty_dust")) {
//					ItemModelBuilder dirtyDustBuilder = new ItemModelBuilder("minecraft:item/generated");
//					if (material.getColors().getMaterialColor() == -1) {
//						dirtyDustBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_dirty_dust").toString());
//					} else {
//						dirtyDustBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/00").toString())
//								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/01").toString())
//								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/02").toString())
//								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/03").toString())
//								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/04").toString());
//					}
//					dirtyDustBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_dirty_dust"));
//				}
//				// Enriched
//				if (processedType.contains("infuse_type")) {
//					ItemModelBuilder enrichedBuilder = new ItemModelBuilder("minecraft:item/generated");
//					if (material.getColors().getMaterialColor() == -1) {
//						enrichedBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/enriched_" + material.getId()).toString());
//					} else {
//						enrichedBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/enriched/00").toString())
//								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/enriched/01").toString())
//								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/enriched/02").toString())
//								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/enriched/03").toString())
//								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/enriched/04").toString());
//					}
//					enrichedBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, "enriched_" + material.getId()));
//				}
//			}
//
//		}
//
//		@Override
//		public String getName() {
//			return "EE Mekanism Addon Item Models";
//		}
//
//	}
//
//	public static class ItemTags extends EETagProvider {
//
//		private final EmendatusDataRegistry registry;
//
//		public ItemTags(DataGenerator gen, EmendatusDataRegistry registry) {
//			super(gen);
//			this.registry = registry;
//		}
//
//		private final List<String> mekanismCrystals = Lists.newArrayList();
//		private final List<String> mekanismShards = Lists.newArrayList();
//		private final List<String> mekanismClumps = Lists.newArrayList();
//		private final List<String> mekanismDirtyDusts = Lists.newArrayList();
//		private final List<String> mekanismEnriched = Lists.newArrayList();
//
//		@Override
//		protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
//			for (MaterialModel material : registry.getMaterials()) {
//				List<String> processedType = material.getProcessedTypes();
//				// Crystals
//				if (processedType.contains("crystal")) {
//					ResourceLocation crystal = EEMekanismRegistrar.crystalMap.get(material.getId()).getId();
//					if (!mekanismCrystals.contains("#mekanism:crystals/" + material.getId())) mekanismCrystals.add("#mekanism:crystals/" + material.getId());
//					new TagBuilder().tag(crystal.toString()).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/crystals/" + material.getId()));
//				}
//				// Shards
//				if (processedType.contains("shard")) {
//					ResourceLocation shard = EEMekanismRegistrar.shardMap.get(material.getId()).getId();
//					if (!mekanismShards.contains("#mekanism:shards/" + material.getId())) mekanismShards.add("#mekanism:shards/" + material.getId());
//					new TagBuilder().tag(shard.toString()).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/shards/" + material.getId()));
//				}
//				// Clumps
//				if (processedType.contains("clump")) {
//					ResourceLocation clump = EEMekanismRegistrar.clumpMap.get(material.getId()).getId();
//					if (!mekanismClumps.contains("#mekanism:clumps/" + material.getId())) mekanismClumps.add("#mekanism:clumps/" + material.getId());
//					new TagBuilder().tag(clump.toString()).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/clumps/" + material.getId()));
//				}
//				// Dirty Dusts
//				if (processedType.contains("dirty_dust")) {
//					ResourceLocation dirtyDust = EEMekanismRegistrar.dirtyDustMap.get(material.getId()).getId();
//					if (!mekanismDirtyDusts.contains("#mekanism:dirty_dusts/" + material.getId())) mekanismDirtyDusts.add("#mekanism:dirty_dusts/" + material.getId());
//					new TagBuilder().tag(dirtyDust.toString()).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/dirty_dusts/" + material.getId()));
//				}
//				// Enriched
//				if (processedType.contains("infuse_type")) {
//					ResourceLocation enriched = EEMekanismRegistrar.enrichedMap.get(material.getId()).getId();
//					if (!mekanismEnriched.contains("#mekanism:enriched/" + material.getId())) mekanismEnriched.add("#mekanism:enriched/" + material.getId());
//					new TagBuilder().tag(enriched.toString()).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/enriched/" + material.getId()));
//				}
//			}
//			if (!mekanismCrystals.isEmpty()) new TagBuilder().tags(mekanismCrystals).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/crystals"));
//			if (!mekanismShards.isEmpty()) new TagBuilder().tags(mekanismShards).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/shards"));
//			if (!mekanismClumps.isEmpty()) new TagBuilder().tags(mekanismClumps).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/clumps"));
//			if (!mekanismDirtyDusts.isEmpty()) new TagBuilder().tags(mekanismDirtyDusts).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/dirty_dusts"));
//			if (!mekanismEnriched.isEmpty()) new TagBuilder().tags(mekanismEnriched).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/items/enriched"));
//		}
//		@Override
//		public String getName() {
//			return "EE Mekanism Addon Item Tags";
//		}
//	}
//
//	public static class SlurryTags extends EETagProvider {
//
//		private final EmendatusDataRegistry registry;
//
//		public SlurryTags(DataGenerator gen, EmendatusDataRegistry registry) {
//			super(gen);
//			this.registry = registry;
//		}
//
//		private final List<String> mekanismCleanSlurry = Lists.newArrayList();
//		private final List<String> mekanismDirtySlurry = Lists.newArrayList();
//
//		@Override
//		protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
//			for (MaterialModel material : registry.getMaterials()) {
//				List<String> processedType = material.getProcessedTypes();
//				// Slurries
//				if (processedType.contains("slurry")) {
//					ResourceLocation cleanSlurry = EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).getId();
//					mekanismCleanSlurry.add(cleanSlurry.toString());
//					ResourceLocation dirtySlurry = EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).getId();
//					mekanismDirtySlurry.add(dirtySlurry.toString());
//				}
//			}
//			if (!mekanismCleanSlurry.isEmpty()) new TagBuilder().tags(mekanismCleanSlurry).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/mekanism/clean"));
//			if (!mekanismDirtySlurry.isEmpty()) new TagBuilder().tags(mekanismDirtySlurry).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/mekanism/dirty"));
//		}
//
//		@Override
//		public String getName() {
//			return "EE Mekanism Addon Slurry Tags";
//		}
//	}
//
//	public static class InfuseTypeTags extends EETagProvider {
//
//		private final EmendatusDataRegistry registry;
//
//		public InfuseTypeTags(DataGenerator gen, EmendatusDataRegistry registry) {
//			super(gen);
//			this.registry = registry;
//		}
//
//		private final List<String> mekanismInfuseType = Lists.newArrayList();
//
//		@Override
//		protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
//			for (MaterialModel material : registry.getMaterials()) {
//				List<String> processedType = material.getProcessedTypes();
//				// Infuse Type
//				if (processedType.contains("infuse_type")) {
//					ResourceLocation infuseType = EEMekanismRegistrar.infuseMap.get(material.getId()).getId();
//					mekanismInfuseType.add(infuseType.toString());
//				}
//			}
//			if (!mekanismInfuseType.isEmpty()) new TagBuilder().tags(mekanismInfuseType).save(consumer, new ResourceLocation(EEMekanismAddon.MEKANISM, "/mekanism/infuse_type"));
//		}
//
//		@Override
//		public String getName() {
//			return "EE Mekanism Addon Infuse Type Tags";
//		}
//	}
//
//	public static class Lang extends EELangProvider {
//
//		private final EmendatusDataRegistry registry;
//
//		public Lang(DataGenerator gen, EmendatusDataRegistry registry) {
//			super(gen, EEMekanismAddon.MOD_ID, "en_us");
//			this.registry = registry;
//		}
//
//		@Override
//		protected void addTranslations() {
//			for (MaterialModel material : registry.getMaterials()) {
//				List<String> processedType = material.getProcessedTypes();
//				// Slurries
//				if (processedType.contains("slurry")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append("Dirty ");
//					sb.append(material.getLocalizedName());
//					sb.append(" Slurry");
//					add(EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getTranslationKey(), sb.toString());
//
//					StringBuilder sb2 = new StringBuilder();
//					sb2.append("Clean ");
//					sb2.append(material.getLocalizedName());
//					sb2.append(" Slurry");
//					add(EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get().getTranslationKey(), sb2.toString());
//				}
//				// Crystals
//				if (processedType.contains("crystal")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append(material.getLocalizedName());
//					sb.append(" Crystal");
//					add(EEMekanismRegistrar.crystalMap.get(material.getId()).get(), sb.toString());
//				}
//				// Shards
//				if (processedType.contains("shard")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append(material.getLocalizedName());
//					sb.append(" Shard");
//					add(EEMekanismRegistrar.shardMap.get(material.getId()).get(), sb.toString());
//				}
//				// Clumps
//				if (processedType.contains("clump")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append(material.getLocalizedName());
//					sb.append(" Clump");
//					add(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), sb.toString());
//				}
//				// Dirty Dusts
//				if (processedType.contains("dirty_dust")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append("Dirty ");
//					sb.append(material.getLocalizedName());
//					sb.append(" Dust");
//					add(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get(), sb.toString());
//				}
//				// Gasses
//				if (processedType.contains("gas")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append("Gaseous ");
//					sb.append(material.getLocalizedName());
//					add(EEMekanismRegistrar.gasMap.get(material.getId()).get().getTranslationKey(), sb.toString());
//				}
//				// Infuse Types
//				if (processedType.contains("infuse_type")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append(material.getLocalizedName());
//					add(EEMekanismRegistrar.infuseMap.get(material.getId()).get().getTranslationKey(), sb.toString());
//					StringBuilder sb2 = new StringBuilder();
//					sb2.append("Enriched ");
//					sb2.append(material.getLocalizedName());
//					add(EEMekanismRegistrar.enrichedMap.get(material.getId()).get(), sb2.toString());
//				}
//			}
//		}
//
//		@Override
//		public String getName() {
//			return "EE Mekanism Addon Languages: en_us";
//		}
//	}
}