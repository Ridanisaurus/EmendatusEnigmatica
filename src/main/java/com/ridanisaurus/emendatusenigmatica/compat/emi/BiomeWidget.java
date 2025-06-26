package com.ridanisaurus.emendatusenigmatica.compat.emi;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import dev.emi.emi.api.widget.TextureWidget;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class BiomeWidget extends TextureWidget {
    /**
     * Ordered list of supported biomes. Each entry has VOffset of index*12 and UOffset of 146 in the main widget texture.
     */
    public static final List<String> BIOME_OFFSETS = List.of(
        getBiomeTag(BiomeTags.IS_DEEP_OCEAN),
        getBiomeTag(BiomeTags.IS_OCEAN),
        getBiomeTag(BiomeTags.IS_BEACH),
        getBiomeTag(BiomeTags.IS_RIVER),
        getBiomeTag(BiomeTags.IS_MOUNTAIN),
        getBiomeTag(BiomeTags.IS_BADLANDS),
        getBiomeTag(BiomeTags.IS_HILL),
        getBiomeTag(BiomeTags.IS_TAIGA),
        getBiomeTag(BiomeTags.IS_JUNGLE),
        getBiomeTag(BiomeTags.IS_FOREST),
        getBiomeTag(BiomeTags.IS_SAVANNA)
    );

    private final List<String> tags = new ArrayList<>();
    private boolean includeDefault = false;
    private long time = 0;
    private int index = 0;

    public BiomeWidget(
        int x,
        int y,
        int width,
        int height,
        IDepositProcessor processor
    ) {
        super(
            Reference.getPath("textures/gui/world_gen.png"),
            x,
            y,
            width,
            height,
            146,
            0,
            width,
            height,
            256,
            256
        );

        var biomes = processor.getCommonModel().getBiomes();
        BIOME_OFFSETS.forEach(biome -> {
            if (biomes.contains(biome)) tags.add(biome); else includeDefault = true;
        });

        List<ClientTooltipComponent> tooltip = new ArrayList<>();
        tooltip.add(ClientTooltipComponent.create(Component.literal(ChatFormatting.GOLD + "Biomes:").getVisualOrderText()));

        if (biomes.isEmpty()) {
            tooltip.add(ClientTooltipComponent.create(Component.literal("- Any").getVisualOrderText()));
        } else {
            for (String biome : biomes)
                tooltip.add(ClientTooltipComponent.create(Component.literal("- ").append(Component.literal(biome)).getVisualOrderText()));
        }

        tooltip(tooltip);
    }

    @Override
    public void render(GuiGraphics draw, int mouseX, int mouseY, float delta) {
        draw.setColor(1f, 1f, 1f, 1f);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        int v = BIOME_OFFSETS.size()*12;
        if (!tags.isEmpty()) {
            if (System.currentTimeMillis() > time + 2000) {
                index = index+1 > tags.size()? 0: index+1;
                time = System.currentTimeMillis();
            }

            if (index == tags.size()) {
                if (!includeDefault) {
                    index = 0;
                    v = BIOME_OFFSETS.indexOf(tags.get(index))*12;
                }
            } else {
                v = BIOME_OFFSETS.indexOf(tags.get(index))*12;
            }
        }

        draw.blit(texture, x, y, width, height, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
    }

    private static String getBiomeTag(TagKey<Biome> biome) {
        return "#" + biome.location();
    }
}
