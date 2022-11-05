package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositConfigModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.function.Function;

public class VanillaOreFeature extends Feature<NoneFeatureConfiguration> {
	private final VanillaDepositModel model;
	private final Lazy<List<OreConfiguration.TargetBlockState>> lazyList;

	// TODO: Fix VanillaOreGen in Dims
	public VanillaOreFeature(VanillaDepositModel model) {
		super(NoneFeatureConfiguration.CODEC);
		this.model = model;

		this.lazyList = Lazy.of(this::createTargetStateList);
	}

	private List<OreConfiguration.TargetBlockState> createTargetStateList() {
		List<OreConfiguration.TargetBlockState> states = new ArrayList<>();

		if (this.model.getConfig().getBlock() != null) {
			Block oreBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(model.getConfig().getBlock()));
			if (oreBlock == null) {
				EmendatusEnigmatica.LOGGER.warn("Unable to find {} in forge registries", model.getConfig().getBlock());
				return states; // Empty, something went wrong
			}

			for (StrataModel stratum : EELoader.STRATA) {
				if (model.getConfig().getFillerTypes().contains(stratum.getId())) {
					Block stratumBlock = ForgeRegistries.BLOCKS.getValue(stratum.getFillerType());
					if (stratumBlock == null) {
						EmendatusEnigmatica.LOGGER.warn("Unable to find {} in forge registries", stratum.getFillerType());
						continue;
					}

					states.add(OreConfiguration.target(new BlockMatchTest(stratumBlock), oreBlock.defaultBlockState()));
				}
			}

			return states;
		}

		for (MaterialModel material : EELoader.MATERIALS) {
			if (!material.getId().equals(model.getConfig().getMaterial())) continue;

			for (StrataModel stratum : EELoader.STRATA) {
				if (!model.getConfig().getFillerTypes().contains(stratum.getId())) continue;

				Block stratumBlock = ForgeRegistries.BLOCKS.getValue(stratum.getFillerType());
				if (stratumBlock == null) {
					EmendatusEnigmatica.LOGGER.warn("Unable to find {} in forge registries", stratum.getFillerType());
					continue;
				}

				RegistryObject<Block> blockRegistryObject = EERegistrar.oreBlockTable.get(stratum.getId(), material.getId());
				if (blockRegistryObject == null) {
					EmendatusEnigmatica.LOGGER.warn("Unable to find the combination of {} and {} in the ore block table", stratum.getId(), material.getId());
					continue;
				}

				BlockState oreBlockstate = blockRegistryObject.get().defaultBlockState();
				states.add(OreConfiguration.target(new BlockMatchTest(stratumBlock), oreBlockstate));
			}
		}

		return states;
	}

	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> config) {
		RandomSource rand = config.random();
		BlockPos pos = config.origin();
		WorldGenLevel reader = config.level();

		if (!model.getDimensions().contains(WorldGenHelper.getDimensionAsString(reader.getLevel()))) {
			return false;
		}

		var oreConfig = this.model.getConfig();

		float f = rand.nextFloat() * (float)Math.PI;
		float f1 = (float)oreConfig.getSize() / 8.0F;
		int i = Mth.ceil(((float)oreConfig.getSize() / 16.0F * 2.0F + 1.0F) / 2.0F);
		double d0 = (double)pos.getX() + Math.sin((double)f) * (double)f1;
		double d1 = (double)pos.getX() - Math.sin((double)f) * (double)f1;
		double d2 = (double)pos.getZ() + Math.cos((double)f) * (double)f1;
		double d3 = (double)pos.getZ() - Math.cos((double)f) * (double)f1;
		int j = 2;
		double d4 = (double)(pos.getY() + rand.nextInt(3) - 2);
		double d5 = (double)(pos.getY() + rand.nextInt(3) - 2);
		int k = pos.getX() - Mth.ceil(f1) - i;
		int l = pos.getY() - 2 - i;
		int i1 = pos.getZ() - Mth.ceil(f1) - i;
		int j1 = 2 * (Mth.ceil(f1) + i);
		int k1 = 2 * (2 + i);

		for(int l1 = k; l1 <= k + j1; ++l1) {
			for(int i2 = i1; i2 <= i1 + j1; ++i2) {
				if (l <= reader.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, l1, i2)) {
					return this.doPlace(reader, rand, d0, d1, d2, d3, d4, d5, k, l, i1, j1, k1);
				}
			}
		}

		return false;
	}

	protected boolean doPlace(WorldGenLevel reader, RandomSource rand, double p_225175_, double p_225176_, double p_225177_, double p_225178_, double p_225179_, double p_225180_, int p_225181_, int p_225182_, int p_225183_, int p_225184_, int p_225185_) {
		int i = 0;
		BitSet bitset = new BitSet(p_225184_ * p_225185_ * p_225184_);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int j = this.model.getConfig().getSize();
		double[] adouble = new double[j * 4];

		for(int k = 0; k < j; ++k) {
			float f = (float)k / (float)j;
			double d0 = Mth.lerp((double)f, p_225175_, p_225176_);
			double d1 = Mth.lerp((double)f, p_225179_, p_225180_);
			double d2 = Mth.lerp((double)f, p_225177_, p_225178_);
			double d3 = rand.nextDouble() * (double)j / 16.0D;
			double d4 = ((double)(Mth.sin((float)Math.PI * f) + 1.0F) * d3 + 1.0D) / 2.0D;
			adouble[k * 4 + 0] = d0;
			adouble[k * 4 + 1] = d1;
			adouble[k * 4 + 2] = d2;
			adouble[k * 4 + 3] = d4;
		}

		for(int l3 = 0; l3 < j - 1; ++l3) {
			if (!(adouble[l3 * 4 + 3] <= 0.0D)) {
				for(int i4 = l3 + 1; i4 < j; ++i4) {
					if (!(adouble[i4 * 4 + 3] <= 0.0D)) {
						double d8 = adouble[l3 * 4 + 0] - adouble[i4 * 4 + 0];
						double d10 = adouble[l3 * 4 + 1] - adouble[i4 * 4 + 1];
						double d12 = adouble[l3 * 4 + 2] - adouble[i4 * 4 + 2];
						double d14 = adouble[l3 * 4 + 3] - adouble[i4 * 4 + 3];
						if (d14 * d14 > d8 * d8 + d10 * d10 + d12 * d12) {
							if (d14 > 0.0D) {
								adouble[i4 * 4 + 3] = -1.0D;
							} else {
								adouble[l3 * 4 + 3] = -1.0D;
							}
						}
					}
				}
			}
		}

		BulkSectionAccess bulksectionaccess = new BulkSectionAccess(reader);

		List<OreConfiguration.TargetBlockState> targetBlockStates = lazyList.get();
		try {
			for(int j4 = 0; j4 < j; ++j4) {
				double d9 = adouble[j4 * 4 + 3];
				if (!(d9 < 0.0D)) {
					double d11 = adouble[j4 * 4 + 0];
					double d13 = adouble[j4 * 4 + 1];
					double d15 = adouble[j4 * 4 + 2];
					int k4 = Math.max(Mth.floor(d11 - d9), p_225181_);
					int l = Math.max(Mth.floor(d13 - d9), p_225182_);
					int i1 = Math.max(Mth.floor(d15 - d9), p_225183_);
					int j1 = Math.max(Mth.floor(d11 + d9), k4);
					int k1 = Math.max(Mth.floor(d13 + d9), l);
					int l1 = Math.max(Mth.floor(d15 + d9), i1);

					for(int i2 = k4; i2 <= j1; ++i2) {
						double d5 = ((double)i2 + 0.5D - d11) / d9;
						if (d5 * d5 < 1.0D) {
							for(int j2 = l; j2 <= k1; ++j2) {
								double d6 = ((double)j2 + 0.5D - d13) / d9;
								if (d5 * d5 + d6 * d6 < 1.0D) {
									for(int k2 = i1; k2 <= l1; ++k2) {
										double d7 = ((double)k2 + 0.5D - d15) / d9;
										if (d5 * d5 + d6 * d6 + d7 * d7 < 1.0D && !reader.isOutsideBuildHeight(j2)) {
											int l2 = i2 - p_225181_ + (j2 - p_225182_) * p_225184_ + (k2 - p_225183_) * p_225184_ * p_225185_;
											if (!bitset.get(l2)) {
												bitset.set(l2);
												blockpos$mutableblockpos.set(i2, j2, k2);
												if (reader.ensureCanWrite(blockpos$mutableblockpos)) {
													LevelChunkSection levelchunksection = bulksectionaccess.getSection(blockpos$mutableblockpos);
													if (levelchunksection != null) {
														int i3 = SectionPos.sectionRelative(i2);
														int j3 = SectionPos.sectionRelative(j2);
														int k3 = SectionPos.sectionRelative(k2);
														BlockState blockstate = levelchunksection.getBlockState(i3, j3, k3);

														for(OreConfiguration.TargetBlockState oreconfiguration$targetblockstate : targetBlockStates) {
															if (canPlaceOre(blockstate, bulksectionaccess::getBlockState, rand, oreconfiguration$targetblockstate, blockpos$mutableblockpos)) {
																levelchunksection.setBlockState(i3, j3, k3, oreconfiguration$targetblockstate.state, false);
																++i;
																break;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Throwable throwable1) {
			try {
				bulksectionaccess.close();
			} catch (Throwable throwable) {
				throwable1.addSuppressed(throwable);
			}

			throw throwable1;
		}

		bulksectionaccess.close();
		return i > 0;
	}

	public static boolean canPlaceOre(BlockState stateAtLocation, Function<BlockPos, BlockState> getState, RandomSource random, OreConfiguration.TargetBlockState stateToPlace, BlockPos.MutableBlockPos pos) {
		if (!stateToPlace.target.test(stateAtLocation, random)) {
			return false;
		} else {
			return !isAdjacentToAir(getState, pos);
		}
	}
}
