package com.ridanisaurus.emendatusenigmatica.core.mixin;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
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

    @Inject(method = "withDebrisOre", at = @At("HEAD"), cancellable = true)
    private static void withDebrisOre(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "withCommonNetherBlocks", at = @At("HEAD"), cancellable = true)
    private static void withCommonNetherBlocks(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_GRAVEL_NETHER);
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_BLACKSTONE);
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_DEBRIS_LARGE);
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_DEBRIS_SMALL);
        ci.cancel();
    }
}