package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.custom.CustomDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.CustomOreFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.noise.NoiseQuality;
import org.spongepowered.noise.module.source.Perlin;

import java.util.Random;

public class CustomOreFeature extends Feature<CustomOreFeatureConfig> {
    private final Perlin noise;
    private boolean hasSeeded = false;
    private CustomDepositModel model;

    public CustomOreFeature(Codec<CustomOreFeatureConfig> codec, CustomDepositModel model) {
        super(codec);
        this.model = model;
        noise = new Perlin();
        noise.setNoiseQuality(NoiseQuality.FAST);
        noise.setFrequency(0.1);
        noise.setOctaveCount(3);
        noise.setLacunarity(0.3);
        noise.setPersistence(0.8);

    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, CustomOreFeatureConfig config) {
        if (!hasSeeded) {
            noise.setSeed(rand.nextInt(Integer.MAX_VALUE));
            hasSeeded = true;
        }
        int yTop = model.getConfig().getMaxYLevel();
        int yBottom = model.getConfig().getMinYLevel();

        if (!model.getDimensions().contains(WorldGenHelper.getDimensionAsString(reader.getWorld()))) {
            return false;
        }

        if (rand.nextInt(100) > model.getConfig().getChance()){
            return false;
        }

        for (int x = pos.getX(); x < pos.getX() + 16; x++) {
            for (int z = pos.getZ(); z < pos.getZ() + 16; z++) {
                for (int y = yBottom; y < yTop; y++) {
                    if (noise.getValue(x, y, z) >= 0.5) {
                        placeBlock(reader, generator, rand, new BlockPos(x, y, z), config);
                    }
                }
            }
        }
        return true;
    }

    private void placeBlock(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos
            pos, CustomOreFeatureConfig config) {
        if (!config.target.test(reader.getBlockState(pos), rand)) {
            return;
        }
        if (rand.nextInt(100) > model.getConfig().getDensity()){
            return;
        }
        int index = rand.nextInt(model.getConfig().getBlocks().size());
        CommonBlockDefinitionModel commonBlockDefinitionModel = model.getConfig().getBlocks().get(index);
        if (commonBlockDefinitionModel.getBlock() != null) {
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(commonBlockDefinitionModel.getBlock()));
            reader.setBlockState(pos, block.getDefaultState(), 2);
        } else if (commonBlockDefinitionModel.getTag() != null) {
            ITag<Block> blockITag = BlockTags.getCollection().get(new ResourceLocation(commonBlockDefinitionModel.getTag()));
            Block block = blockITag.getRandomElement(rand);
            reader.setBlockState(pos, block.getDefaultState(), 2);
        }
        // TODO add EE material supprt
    }
}
