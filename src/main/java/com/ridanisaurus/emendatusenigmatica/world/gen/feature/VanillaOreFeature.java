/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.VanillaDepositModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;
import java.util.Objects;
import java.util.function.Function;

public class VanillaOreFeature extends Feature<VanillaDepositModel> {
	private static final Codec<VanillaDepositModel> CODEC = VanillaDepositModel.getFeatureCodec(VanillaDepositModel.CODEC);

	public VanillaOreFeature() {
		super(CODEC);
	}

	public boolean place(@NotNull FeaturePlaceContext<VanillaDepositModel> context) {
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		WorldGenLevel level = context.level();
		var model = context.config();

		int spreadConst = 2;
		float veinDir = rand.nextFloat() * (float)Math.PI;
		float sizeBound = (float) model.size / 8.0F;
		int bBoxPad = Mth.ceil(((float) model.size / 16.0F * 2.0F + 1.0F) / 2.0F);
		double sX = (double)pos.getX() + Math.sin(veinDir) * (double)sizeBound;
		double fX = (double)pos.getX() - Math.sin(veinDir) * (double)sizeBound;
		double sZ = (double)pos.getZ() + Math.cos(veinDir) * (double)sizeBound;
		double fZ = (double)pos.getZ() - Math.cos(veinDir) * (double)sizeBound;
		double sY = pos.getY() + rand.nextInt(3) - spreadConst;
		double fY = pos.getY() + rand.nextInt(3) - spreadConst;
		int startX = pos.getX() - Mth.ceil(sizeBound) - bBoxPad;
		int startY = pos.getY() - spreadConst - bBoxPad;
		int startZ = pos.getZ() - Mth.ceil(sizeBound) - bBoxPad;
		int width = spreadConst * (Mth.ceil(sizeBound) + bBoxPad);
		int height = spreadConst * (spreadConst + bBoxPad);

		// Find spot in the bounding box below or at OCEAN_FLOOR height.
		for (int placeX = startX; placeX <= startX + width; ++placeX) {
			for (int placeZ = startZ; placeZ <= startZ + width; ++placeZ) {
				if (startY <= level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, placeX, placeZ)) {
					return this.doPlace(level, rand, model, sX, fX, sZ, fZ, sY, fY, startX, startY, startZ, width, height);
				}
			}
		}

		return false;
	}

	protected boolean doPlace(
		WorldGenLevel level,
		RandomSource rand,
		@NotNull VanillaDepositModel model,
		double sX,
		double fX,
		double sZ,
		double fZ,
		double sY,
		double fY,
		int startMinX,
		int startMinY,
		int startMinZ,
		int width,
		int height
	) {
		BitSet placeFlag = new BitSet(width * height * width);
		BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
		int size = model.size;
		double[] data = new double[size * 4];
		boolean placed = false;

		// Generate spheres based on the initial vein direction.
		for (int index = 0; index < size; ++index) {
			float delta = (float) index / (float) size;
			double xLerp = Mth.lerp(delta, sX, fX);
			double yLerp = Mth.lerp(delta, sY, fY);
			double zLerp = Mth.lerp(delta, sZ, fZ);
			double rFactor = rand.nextDouble() * (double) size / 16.0D;
			double radius = ((double) (Mth.sin((float) Math.PI * delta) + 1.0F) * rFactor + 1.0D) / 2.0D;
			data[index * 4 + 0] = xLerp;
			data[index * 4 + 1] = yLerp;
			data[index * 4 + 2] = zLerp;
			data[index * 4 + 3] = radius;
		}

		// Remove spheres that overlap
		for (int index = 0; index < size - 1; ++index) {
            if (data[index * 4 + 3] <= 0.0D) continue;

            for (int next = index + 1; next < size; ++next) {
                if (data[next * 4 + 3] <= 0.0D) continue;
                double xDelta = data[index * 4 + 0] - data[next * 4 + 0];
                double yDelta = data[index * 4 + 1] - data[next * 4 + 1];
                double zDelta = data[index * 4 + 2] - data[next * 4 + 2];
                double rDelta = data[index * 4 + 3] - data[next * 4 + 3];
                if (rDelta * rDelta > xDelta * xDelta + yDelta * yDelta + zDelta * zDelta) {
                    if (rDelta > 0.0D) {
                        data[next * 4 + 3] = -1.0D;
                    } else {
                        data[index * 4 + 3] = -1.0D;
                    }
                }
            }
        }

		try (BulkSectionAccess bulkAccess = new BulkSectionAccess(level)) {
			for (int index = 0; index < size; ++index) {
				double radius = data[index * 4 + 3];
                if (radius < 0.0D) continue;
				double blobX = data[index * 4 + 0];
				double blobY = data[index * 4 + 1];
				double blobZ = data[index * 4 + 2];
				int startX = Math.max(Mth.floor(blobX - radius), startMinX);
				int startY = Math.max(Mth.floor(blobY - radius), startMinY);
				int startZ = Math.max(Mth.floor(blobZ - radius), startMinZ);
				int endX = Math.max(Mth.floor(blobX + radius), startX);
				int endY = Math.max(Mth.floor(blobY + radius), startY);
				int endZ = Math.max(Mth.floor(blobZ + radius), startZ);

				for (int x = startX; x <= endX; ++x) {
					double normX = ((double) x + 0.5D - blobX) / radius;
                    if (normX * normX >= 1.0D) continue;
					for (int y = startY; y <= endY; ++y) {
						double normY = ((double) y + 0.5D - blobY) / radius;
                        if (normX * normX + normY * normY >= 1.0D) continue;
						for (int z = startZ; z <= endZ; ++z) {
							double normZ = ((double) z + 0.5D - blobZ) / radius;
                            if (normX * normX + normY * normY + normZ * normZ >= 1.0D || level.isOutsideBuildHeight(y)) continue;
							int blockID = x - startMinX + (y - startMinY) * width + (z - startMinZ) * width * height;
							// Duplicate check - makes sure we don't place the same block again.
                            if (placeFlag.get(blockID)) continue;
							placeFlag.set(blockID);
							blockPos.set(x, y, z);
                            if (!level.ensureCanWrite(blockPos)) continue;
							LevelChunkSection chunkSection = bulkAccess.getSection(blockPos);
                            if (chunkSection == null) continue;
							int posX = SectionPos.sectionRelative(x);
							int posY = SectionPos.sectionRelative(y);
							int posZ = SectionPos.sectionRelative(z);
							BlockState blockState = chunkSection.getBlockState(posX, posY, posZ);

							if (canPlaceOre(blockState, bulkAccess::getBlockState, rand, model.target, blockPos)) {
								chunkSection.setBlockState(posX, posY, posZ, getToPlaceBlockState(model, blockState, rand), false);
								placed = true;
							}
                        }
                    }
                }
            }
		}

		return placed;
	}

	public static @NotNull BlockState getToPlaceBlockState(@NotNull VanillaDepositModel model, @NotNull BlockState strataState, @NotNull RandomSource rand) {
		if (model.block != null)
			return BuiltInRegistries.BLOCK.get(ResourceLocation.parse(model.block)).defaultBlockState();

		if (model.material != null) {
			var strata = model.target.getStrataFromFiller(strataState, rand);
			if (strata != null)
				return Objects.requireNonNull(EERegistrar.oreBlockTable.get(strata, model.material)).get().defaultBlockState();
		}

		throw new IllegalStateException("No valid block found for placing feature: " + model.id);
	}

	public static boolean canPlaceOre(BlockState stateAtLocation, Function<BlockPos, BlockState> getState, RandomSource random, @NotNull RuleTest target, BlockPos.MutableBlockPos pos) {
		if (!target.test(stateAtLocation, random)) return false;
		if (shouldSkipAirCheck(random, 0.5F)) return true;
		return !isAdjacentToAir(getState, pos);
	}

	protected static boolean shouldSkipAirCheck(RandomSource random, float chance) {
		if (chance <= 0.0F) return true;
		if (chance >= 1.0F) return false;
		return random.nextFloat() >= chance;
	}
}
