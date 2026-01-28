/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.datagen.gen.block.tags;

import com.google.common.collect.Lists;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.TagBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EETagProvider;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static com.ridanisaurus.emendatusenigmatica.util.Reference.COMMON;
import static com.ridanisaurus.emendatusenigmatica.util.Reference.MINECRAFT;

public class BlockHarvestToolTagsGen extends EETagProvider {
    private final DataRegistry registry;

    public BlockHarvestToolTagsGen(DataGenerator gen, DataRegistry registry) {
        super(gen);
        this.registry = registry;
    }

    private final List<String> shovel = Lists.newArrayList();
    private final List<String> hoe = Lists.newArrayList();
    private final List<String> axe = Lists.newArrayList();
    private final List<String> pickaxe = Lists.newArrayList();

    @Override
    protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
        for (MaterialModel material : registry.getRegisteredMaterials()) {
            List<String> processedType = material.getProcessedTypes();

            if (processedType.contains("storage_block")) {
                pickaxe.add(EERegistrar.storageBlockMap.getIdAsString(material));
                if (material.getProperties().hasOxidization()) {
                    pickaxe.add(EERegistrar.exposedBlockMap.getIdAsString(material));
                    pickaxe.add(EERegistrar.weatheredBlockMap.getIdAsString(material));
                    pickaxe.add(EERegistrar.oxidizedBlockMap.getIdAsString(material));
                    pickaxe.add(EERegistrar.waxedStorageBlockMap.getIdAsString(material));
                    pickaxe.add(EERegistrar.waxedExposedBlockMap.getIdAsString(material));
                    pickaxe.add(EERegistrar.waxedWeatheredBlockMap.getIdAsString(material));
                    pickaxe.add(EERegistrar.waxedOxidizedBlockMap.getIdAsString(material));
                }
                if (processedType.contains("raw"))
                    pickaxe.add(EERegistrar.rawBlockMap.getIdAsString(material));
            }

            if (processedType.contains("cluster")) {
                pickaxe.add(EERegistrar.buddingBlockMap.getIdAsString(material));
                pickaxe.add(EERegistrar.smallBudBlockMap.getIdAsString(material));
                pickaxe.add(EERegistrar.mediumBudBlockMap.getIdAsString(material));
                pickaxe.add(EERegistrar.largeBudBlockMap.getIdAsString(material));
                pickaxe.add(EERegistrar.clusterBlockMap.getIdAsString(material));
                pickaxe.add(EERegistrar.clusterShardBlockMap.getIdAsString(material));
            }

            if (processedType.contains("ore")) {
                for (StrataModel strata : registry.getRegisteredStrata()) {
                    if (!material.getStrata().isEmpty() &&! material.getStrata().contains(strata.getId())) continue;
                    ResourceLocation ore = EERegistrar.oreBlockTable.get(strata.getId(), material.getId()).getId();
                    switch (strata.getHarvestTool()) {
                        case "shovel" -> shovel.add(ore.toString());
                        case "hoe" -> hoe.add(ore.toString());
                        case "axe" -> axe.add(ore.toString());
                        case "pickaxe" -> pickaxe.add(ore.toString());
                        default ->
                            throw new IllegalStateException("Harvest tool " + strata.getHarvestTool() + " for " + strata.getId() + " is out of Vanilla tool system bounds, and the tag should be added manually");
                    }

                    //TODO: Rework Sample System.
//                    if (processedType.contains("sample")) {
////							ResourceLocation sample = ResourceLocation.parse("minecraft:stone");
//                        if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
//                            ResourceLocation sample = EERegistrar.oreSampleBlockTable.get(strata.getId(), material.getId()).getId();
//                            switch (strata.getHarvestTool()) {
//                                case "shovel" -> shovel.add(sample.toString());
//                                case "hoe" -> hoe.add(sample.toString());
//                                case "axe" -> axe.add(sample.toString());
//                                case "pickaxe" -> pickaxe.add(sample.toString());
//                                default ->
//                                    throw new IllegalStateException("Harvest tool " + strata.getHarvestTool() + " for " + strata.getId() + " is out of Vanilla tool system bounds, and the tag should be added manually");
//                            }
//                        }
//                    }
                }
            }
        }

        if (!shovel.isEmpty())  new TagBuilder(shovel)  .save(consumer, MINECRAFT, "/block/mineable/shovel");
        if (!hoe.isEmpty())     new TagBuilder(hoe)     .save(consumer, MINECRAFT, "/block/mineable/hoe");
        if (!axe.isEmpty())     new TagBuilder(axe)     .save(consumer, MINECRAFT, "/block/mineable/axe");
        if (!pickaxe.isEmpty()) new TagBuilder(pickaxe) .save(consumer, MINECRAFT, "/block/mineable/pickaxe");

        // Paxel is axe / pickaxe / shovel at once, so it's pretty much just three different tags.
        new TagBuilder(List.of("#minecraft:mineable/axe", "#minecraft:mineable/pickaxe", "#minecraft:mineable/shovel")).save(consumer, COMMON, "/block/mineable/paxel");
    }

    @Override
    public @NotNull String getName() {
        return "Emendatus Enigmatica: Block Harvest Tool Tags";
    }
}
