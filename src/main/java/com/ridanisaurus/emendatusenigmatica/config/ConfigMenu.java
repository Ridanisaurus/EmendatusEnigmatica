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

package com.ridanisaurus.emendatusenigmatica.config;

import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.NotNull;

public class ConfigMenu extends Screen {
    private static final ResourceLocation LOGO = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ee_logo.png");
    // Buttons
    private static final Component ENABLED  = Component.translatable("screen.emendatusenigmatica.config.enabled").withStyle(ChatFormatting.GREEN);
    private static final Component DISABLED = Component.translatable("screen.emendatusenigmatica.config.disabled").withStyle(ChatFormatting.RED);
    // Titles
    private static final Component TITLE    = Component.translatable("screen.emendatusenigmatica.config.title");
    private static final Component C_TITLE  = Component.translatable("screen.emendatusenigmatica.config.client.title");
    private static final Component V_TITLE  = Component.translatable("screen.emendatusenigmatica.config.validation.title");
    // Options
    private static final Component PATREON      = Component.translatable("screen.emendatusenigmatica.config.client.patreon.text");
    private static final Component PATREON_TIP  = Component.translatable("screen.emendatusenigmatica.config.client.patreon.tip");
    private static final Component GLINT        = Component.translatable("screen.emendatusenigmatica.config.client.glint.text");
    private static final Component GLINT_TIP    = Component.translatable("screen.emendatusenigmatica.config.client.glint.tip");
    private static final Component SUMMARY      = Component.translatable("screen.emendatusenigmatica.config.validation.summary.text");
    private static final Component SUMMARY_TIP  = Component.translatable("screen.emendatusenigmatica.config.validation.summary.tip");
    private static final Component SKIP         = Component.translatable("screen.emendatusenigmatica.config.validation.empty_files.text");
    private static final Component SKIP_TIP     = Component.translatable("screen.emendatusenigmatica.config.validation.empty_files.tip");
    // Other
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, 61, 33);
    private final Screen last;
    public ConfigMenu(Screen last) {
        super(TITLE);
        this.last = last;
    }

    @Override
    protected void init() {
        super.init();
        // Old LOGO Rendering. It does scale dynamically.
//        this.addRenderableOnly((guiGraphics, mouseX, mouseY, partialTick) -> {
//            int rWidth = (int) Math.min(((float) height /8)*4.3f, width*0.75f);
//            int rHeight = (int) Math.min((width*0.75f)/4.3f, (float) height /8);
//            guiGraphics.blit(LOGO, width/2-rWidth/2,8,0,0,rWidth, rHeight, rWidth, rHeight);
//        });

        this.layout.addToHeader(ImageWidget.texture(5*64, 64, LOGO, 5*64, 64), LayoutSettings::alignHorizontallyCenter);

        GridLayout grid = new GridLayout();
        grid.defaultCellSetting().paddingHorizontal(8).paddingVertical(6).alignHorizontallyCenter().alignVerticallyTop();

        addTitle(grid, C_TITLE);
        addButton(grid, PATREON, PATREON_TIP, EEConfig.client.showPatreonReward);
        addButton(grid, GLINT, GLINT_TIP, EEConfig.client.oldSchoolGlint);
        addTitle(grid, V_TITLE);
        addButton(grid, SUMMARY, SUMMARY_TIP, EEConfig.startup.generateSummary);
        addButton(grid, SKIP, SKIP_TIP, EEConfig.startup.skipEmptyJsons);

        this.layout.addToContents(grid);

        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, p_345431_ -> this.onClose()).width(200).build());
        this.layout.visitWidgets(p_344729_ -> {
            AbstractWidget abstractwidget = this.addRenderableWidget(p_344729_);
        });
        this.repositionElements();
    }

    private int currentRow = 1;
    private void addTitle(@NotNull GridLayout layout, Component title) {
        layout.addChild(new StringWidget(title, this.font), currentRow++, 1, 1, 2, LayoutSettings::alignVerticallyMiddle);
    }

    private void addButton(@NotNull GridLayout layout, Component text, Component tooltip, ModConfigSpec.@NotNull BooleanValue config) {
        layout.addChild(new StringWidget(text, this.font), currentRow, 1, LayoutSettings::alignVerticallyMiddle);
        layout.addChild(Button
            .builder(config.get()? ENABLED: DISABLED, (event) -> {
                if (event.getMessage() == ENABLED) {
                    event.setMessage(DISABLED);
                    config.set(false);
                } else {
                    event.setMessage(ENABLED);
                    config.set(true);
                }
                config.save();
            })
            .tooltip(Tooltip.create(tooltip))
        .build(), currentRow++, 2, LayoutSettings::alignVerticallyMiddle);
    }

    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
    }

    @Override
    public void onClose() {
        super.onClose();
        minecraft.setScreen(last);
    }
}
