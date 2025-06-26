package com.ridanisaurus.emendatusenigmatica.items.handlers;

import com.ridanisaurus.emendatusenigmatica.renderers.ShieldTextureRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

public class ShieldClientExtension implements IClientItemExtensions {
    public static final ShieldClientExtension INSTANCE = new ShieldClientExtension();

    @Override
    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return ShieldTextureRenderer.RENDERER;
    }
}
