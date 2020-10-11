package com.ridanisaurus.emendatusenigmatica.core.mixin;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    // TODO: (maybe) add config option to overwrite vanilla ores

    @Inject(method = "withOverworldOres", at = @At("HEAD"), cancellable = true)
    private static void withOverworldOres(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "withExtraGoldOre", at = @At("HEAD"), cancellable = true)
    private static void withExtraGoldOre(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "withEmeraldOre", at = @At("HEAD"), cancellable = true)
    private static void withEmeraldOre(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
        ci.cancel();
    }
}
