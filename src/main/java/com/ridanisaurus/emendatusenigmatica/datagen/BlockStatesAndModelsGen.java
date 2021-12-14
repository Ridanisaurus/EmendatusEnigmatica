/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.MultiLayerModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStatesAndModelsGen extends BlockStateProvider {

    public BlockStatesAndModelsGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Reference.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Storage Blocks
        for (MaterialModel material : EELoader.MATERIALS) {
            for (String processedType : material.getProcessedType()) {
                if (processedType.equals("storage_block")) {
                    Block block = EERegistrar.storageBlockMap.get(material.getId()).get();
                    ResourceLocation loc = block.getRegistryName();
                    if (material.getHighlightColor() == -1) {
                        models().getBuilder(loc.toString()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                                .texture("base",  new ResourceLocation(Reference.MOD_ID, "blocks/" + material.getId() + "_block"))
                                .texture("particle",  new ResourceLocation(Reference.MOD_ID, "blocks/" + material.getId() + "_block"))
                                .element()
                                .cube("#base")
                                .allFaces((d, u) -> u.tintindex(-1))
                                .end();
                    } else {
                        models().getBuilder(loc.toString()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                                .texture("highlight", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block_0"))
                                .texture("base", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block_1"))
                                .texture("shade", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block_2"))
                                .texture("particle", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block"))
                                .element()
                                .cube("#highlight")
                                .allFaces((d, u) -> u.tintindex(0))
                                .end()
                                .element()
                                .cube("#base")
                                .allFaces((d, u) -> u.tintindex(1))
                                .end()
                                .element()
                                .cube("#shade")
                                .allFaces((d, u) -> u.tintindex(2))
                                .end();
                    }
                    simpleBlock(block, new ModelFile.UncheckedModelFile(modLoc("block/" + loc.getPath())));

                }

                // Fluid Block
                if (processedType.equals("fluid")) {
                    FlowingFluidBlock fluidBlock = EERegistrar.fluidBlockMap.get(material.getId()).get();
                    ResourceLocation loc = fluidBlock.getRegistryName();
                    simpleBlock(fluidBlock, models().getBuilder(loc.getPath()).texture("particle", new ResourceLocation(Reference.MOD_ID, "fluids/fluid_still")));
                }
            }
        }

        // Ores
        for (MaterialModel material : EELoader.MATERIALS) {
            for (StrataModel stratum : EELoader.STRATA) {
                if (material.getProcessedType().contains("ore")) {
                    Block ore = EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get();
                    ResourceLocation loc = ore.getRegistryName();
                    if (material.getHighlightColor() == -1) {
                        dynamicBlock(loc, stratum.getBaseTexture().toString(), "blocks/overlays/" + material.getId());
                    } else {
                        if (material.getOreBlockType().equals("gem")) {
                            dynamicTintBlock(loc, stratum.getBaseTexture().toString(), "blocks/overlays/templates/ore_gem");
                        } else {
                            dynamicTintBlock(loc, stratum.getBaseTexture().toString(), "blocks/overlays/templates/ore_metal");
                        }
                    }
                    simpleBlock(ore, new ModelFile.UncheckedModelFile(modLoc("block/" + loc.getPath())));
                }
            }
        }
    }

    public void dynamicBlock(ResourceLocation loc, String baseTexture, String overlayTexture) {
        models().getBuilder(loc.getPath()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                .texture("particle", modLoc(overlayTexture))
                .transforms()
                .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
                .rotation(75F, 45F, 0F)
                .translation(0F, 2.5F, 0)
                .scale(0.375F, 0.375F, 0.375F)
                .end()
                .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
                .rotation(75F, 45F, 0F)
                .translation(0F, 2.5F, 0)
                .scale(0.375F, 0.375F, 0.375F)
                .end()
                .end()
                .customLoader(MultiLayerModelBuilder::begin)
                .submodel(RenderType.solid(), this.models().nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                        .texture("base", baseTexture)
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .cube("#base")
                        .allFaces((dir, uv) -> uv.tintindex(-1))
                        .end()
                )
                .submodel(RenderType.translucent(), this.models().nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                        .texture("overlay", new ResourceLocation(Reference.MOD_ID, overlayTexture))
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .cube("#overlay")
                        .allFaces((dir, uv) -> uv.tintindex(0))
                        .end()
                )
                .end();
    }

    public void dynamicTintBlock(ResourceLocation loc, String baseTexture, String overlayTexture) {
        models().getBuilder(loc.getPath()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                .texture("particle", modLoc(overlayTexture))
                .transforms()
                .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
                .rotation(75F, 45F, 0F)
                .translation(0F, 2.5F, 0)
                .scale(0.375F, 0.375F, 0.375F)
                .end()
                .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
                .rotation(75F, 45F, 0F)
                .translation(0F, 2.5F, 0)
                .scale(0.375F, 0.375F, 0.375F)
                .end()
                .end()
                .customLoader(MultiLayerModelBuilder::begin)
                .submodel(RenderType.solid(), this.models().nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                        .texture("base", baseTexture)
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .cube("#base")
                        .allFaces((dir, uv) -> uv.tintindex(-1))
                        .end()
                )
                .submodel(RenderType.translucent(), this.models().nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                        .texture("overlay_0", new ResourceLocation(Reference.MOD_ID, overlayTexture))
                        .texture("overlay_1", new ResourceLocation(Reference.MOD_ID, overlayTexture))
                        .texture("overlay_2", new ResourceLocation(Reference.MOD_ID, overlayTexture))
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .cube("#overlay_0")
                        .allFaces((dir, uv) -> uv.tintindex(0))
                        .end()
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .cube("#overlay_1")
                        .allFaces((dir, uv) -> uv.tintindex(1))
                        .end()
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .cube("#overlay_2")
                        .allFaces((dir, uv) -> uv.tintindex(2))
                        .end()
                )
                .end();
    }

    @Override
    public String getName() {
        return "Emendatus Enigmatica BlockModels and BlockStates";
    }
}