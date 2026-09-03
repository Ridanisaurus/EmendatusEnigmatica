package com.ridanisaurus.emendatusenigmatica.datagen.gen.world;

import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.TagBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EETagProvider;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class BiomeTagsGen extends EETagProvider {
    public BiomeTagsGen(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
        //TODO: Rework for new deposit system
//        for (IDepositProcessor processor : ModelLoader.ACTIVE_PROCESSORS) {
//            var biomes = processor.getCommonModel().getBiomes();
//
//            // Generate packed tags for deposits which contain more than a single tag in the biome array.
//            if (biomes.size() > 1 && biomes.stream().anyMatch(it -> it.startsWith("#")))
//                new TagBuilder(biomes).save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "biome/pack/" + processor.getCommonModel().getName()));
//        }
    }

    @Override
    public @NotNull String getName() {
        return "Emendatus Enigmatica: Biome Tags";
    }
}
