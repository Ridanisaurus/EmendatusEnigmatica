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

package com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry;

import com.ridanisaurus.emendatusenigmatica.api.validation.RegistryValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import io.netty.util.internal.UnstableApi;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Texture Registry Validator is used to check if specified ResourceLocation points to a valid texture.
 * This validator will not cause a crash if the resource location is invalid.
 * @apiNote This is currently W.I.P and does nothing! There is no way to get textures the normal way on Common Setup.
 */
@UnstableApi
@ApiStatus.Experimental
public class TextureRegistryValidator extends AbstractRegistryValidator {
    private static final Map<ResourceLocation, TextureAtlasSprite> textures = null;
    public TextureRegistryValidator() {
        super("Provided ResourceLocation points to a non-existing texture!");
    }

    /**
     * This method is used to check if the resource location is present in the specified registry.
     *
     * @param data Record with ResourceLocation to validate and {@link ValidationContext} object from the original json file.
     * @return The result of the validation. See {@link Result} for more details.
     * @apiNote At this stage, all registries were constructed,
     */
    @Override
    public Result validate(@NotNull RegistryValidationData data) {
//        // Can't validate Textures on dedicated servers, texture manager doesn't exist there!
//        if (FMLEnvironment.dist == Dist.DEDICATED_SERVER) return Result.PASS;
//
//        if (Objects.isNull(textures)) textures = Minecraft.getInstance().getModelManager().getAtlas(TextureAtlas.LOCATION_BLOCKS).getTextures();
//
//        if (textures.containsKey(data.location())) return Result.PASS;
//        return Result.ERROR;
        return Result.PASS;
    }
}
