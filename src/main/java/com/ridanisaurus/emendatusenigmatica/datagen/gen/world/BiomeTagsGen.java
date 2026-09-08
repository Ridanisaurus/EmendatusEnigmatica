package com.ridanisaurus.emendatusenigmatica.datagen.gen.world;

import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.TagBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EETagProvider;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.DepositModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class BiomeTagsGen extends EETagProvider {
    private final DataRegistry registry;

    public BiomeTagsGen(DataGenerator gen, DataRegistry registry) {
        super(gen);
        this.registry = registry;
    }

    @Override
    protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
        for (DepositModel model : registry.getRegisteredDeposits()) {
            // Generate packed tags for deposits which contain more than a single tag in the biome array.
            if (model.biomes.size() > 1 && model.biomes.stream().anyMatch(it -> it.startsWith("#")))
                new TagBuilder(model.biomes).save(consumer, Reference.getPath("biome/pack/" + model.id));
        }
    }

    @Override
    public @NotNull String getName() {
        return "Emendatus Enigmatica: Biome Tags";
    }
}
