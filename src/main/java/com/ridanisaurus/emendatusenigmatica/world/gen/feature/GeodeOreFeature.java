package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.MathHelper;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.GeodeOreFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// TODO: [BUUZ] Not sure what changed here, but I already have implemented one of the place() methods, but it is still asking for the FeaturePlaceContext one
public class GeodeOreFeature extends Feature<GeodeOreFeatureConfig> {
	private final List<CommonBlockDefinitionModel> outerShellBlocks;
	private final List<CommonBlockDefinitionModel> innerShellBlocks;
	private final List<CommonBlockDefinitionModel> innerBlocks;
	private final List<CommonBlockDefinitionModel> fillBlocks;
	private final GeodeDepositModel model;

	public GeodeOreFeature(Codec<GeodeOreFeatureConfig> codec, GeodeDepositModel model) {
		super(codec);
		this.model = model;
		outerShellBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel block : model.getConfig().getOuterShellBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(block.getWeight(), block);
			outerShellBlocks.addAll(filled);
		}
		innerShellBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel block : model.getConfig().getInnerShellBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(block.getWeight(), block);
			innerShellBlocks.addAll(filled);
		}
		innerBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel block : model.getConfig().getInnerBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(block.getWeight(), block);
			innerBlocks.addAll(filled);
		}
		fillBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel block : model.getConfig().getFillBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(block.getWeight(), block);
			fillBlocks.addAll(filled);
		}
	}

	@Override
	public boolean place(GeodeOreFeatureConfig config, WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos) {

		if (!model.getDimensions().contains(WorldGenHelper.getDimensionAsString(reader.getLevel()))) {
			return false;
		}

		int intRand = rand.nextInt(100);
		double doubleRand = rand.nextDouble();
		if (intRand >= 1) {
			doubleRand += intRand - 1;
		}
		if (doubleRand > model.getConfig().getChance() / model.getConfig().getChanceChunkSkip()) {
			return false;
		}
		int yTop = model.getConfig().getMaxYLevel();
		int yBottom = model.getConfig().getMinYLevel();
		int yPos = rand.nextInt(yTop);
		yPos = Math.max(yPos, yBottom);

		generateHollowSphere(reader, generator, rand, pos, config, outerShellBlocks, model.getConfig().getRadius() + 1, yPos);
		generateHollowSphere(reader, generator, rand, pos, config, innerShellBlocks, model.getConfig().getRadius(), yPos);
		generateHollowSphere(reader, generator, rand, pos, config, innerBlocks, model.getConfig().getRadius() - 1, yPos);
		for (int i = model.getConfig().getRadius() -2; i >= 0; i--) {
			generateHollowSphere(reader, generator, rand, pos, config, fillBlocks, i, yPos);
		}
		return true;
	}

	private void generateHollowSphere(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos, GeodeOreFeatureConfig config, List<CommonBlockDefinitionModel> blocks, int radius, int yPos) {
		int yTop = model.getConfig().getMaxYLevel();
		int yBottom = model.getConfig().getMinYLevel();

		radius += 0.5;
		radius += 0.5;
		radius += 0.5;

		final double invRadiusX = 1d / radius;
		final double invRadiusY = 1d / radius;
		final double invRadiusZ = 1d / radius;

		final int ceilRadiusX = (int) Math.ceil(radius);
		final int ceilRadiusY = (int) Math.ceil(radius);
		final int ceilRadiusZ = (int) Math.ceil(radius);

		double nextXn = 0;
		forX:
		for (int x = 0; x <= ceilRadiusX; ++x) {
			final double xn = nextXn;
			nextXn = (x + 1) * invRadiusX;
			double nextYn = 0;
			forY:
			for (int y = 0; y <= ceilRadiusY; ++y) {
				final double yn = nextYn;
				nextYn = (y + 1) * invRadiusY;
				double nextZn = 0;
				forZ:
				for (int z = 0; z <= ceilRadiusZ; ++z) {
					final double zn = nextZn;
					nextZn = (z + 1) * invRadiusZ;

					double distanceSq = MathHelper.lengthSq(xn, yn, zn);
					if (distanceSq > 1) {
						if (z == 0) {
							if (y == 0) {
								break forX;
							}
							break forY;
						}
						break forZ;
					}
					if (y + yPos > yTop || y + yPos < yBottom) {
						continue;
					}

					if (MathHelper.lengthSq(nextXn, yn, zn) <= 1 && MathHelper.lengthSq(xn, nextYn, zn) <= 1 && MathHelper.lengthSq(xn, yn, nextZn) <= 1) {
						continue;
					}

					placeBlock(reader, generator, rand, new BlockPos(pos.getX() + x, yPos + y, pos.getZ() + z), config.target, blocks);
					placeBlock(reader, generator, rand, new BlockPos(pos.getX() + -x, yPos + y, pos.getZ() + z), config.target, blocks);
					placeBlock(reader, generator, rand, new BlockPos(pos.getX() + x, yPos + -y, pos.getZ() + z), config.target, blocks);
					placeBlock(reader, generator, rand, new BlockPos(pos.getX() + x, yPos + y, pos.getZ() + -z), config.target, blocks);
					placeBlock(reader, generator, rand, new BlockPos(pos.getX() + -x, yPos + -y, pos.getZ() + z), config.target, blocks);
					placeBlock(reader, generator, rand, new BlockPos(pos.getX() + x, yPos + -y, pos.getZ() + -z), config.target, blocks);
					placeBlock(reader, generator, rand, new BlockPos(pos.getX() + -x, yPos + y, pos.getZ() + -z), config.target, blocks);
					placeBlock(reader, generator, rand, new BlockPos(pos.getX() + -x, yPos + -y, pos.getZ() + -z), config.target, blocks);
				}
			}
		}
	}

	private void placeBlock(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos
			pos, RuleTest filler, List<CommonBlockDefinitionModel> blocks) {
		if (!filler.test(reader.getBlockState(pos), rand)) {
			return;
		}
		// TODO: [BUUZ] The .getAllTags() seems to have been completely removed since they moved away from the Tag Collection
		int index = rand.nextInt(blocks.size());
		CommonBlockDefinitionModel commonBlockDefinitionModel = blocks.get(index);
		if (commonBlockDefinitionModel.getBlock() != null) {
			Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(commonBlockDefinitionModel.getBlock()));
			reader.setBlock(pos, block.defaultBlockState(), 2);
		} else if (commonBlockDefinitionModel.getTag() != null) {
			ITag<Block> blockITag = ForgeRegistries.BLOCKS.tags().getTag(EETags.getBlockTag(new ResourceLocation(commonBlockDefinitionModel.getTag())));
			blockITag.getRandomElement(rand).ifPresent(block -> {
				reader.setBlock(pos, block.defaultBlockState(), 2);
			});
		} else if (commonBlockDefinitionModel.getMaterial() != null) {
			BlockState currentFiller = reader.getBlockState(pos);
			String fillerId = currentFiller.getBlock().getRegistryName().toString();
			Integer strataIndex = EELoader.STRATA_INDEX_BY_FILLER.getOrDefault(fillerId, null);
			if (strataIndex != null) {
				StrataModel stratum = EELoader.STRATA.get(strataIndex);
				Block block = EERegistrar.oreBlockTable.get(stratum.getId(), commonBlockDefinitionModel.getMaterial()).get();
				reader.setBlock(pos, block.defaultBlockState(), 2);
			}
		}
	}
}