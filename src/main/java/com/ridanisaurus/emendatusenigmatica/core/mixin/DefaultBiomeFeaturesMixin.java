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
	@Inject(method = "addDefaultOres", at = @At("HEAD"), cancellable = true)
	private static void addDefaultOres(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
		//if (Config.disableVanillaOres) {
		ci.cancel();
		//}
	}

	@Inject(method = "addExtraGold", at = @At("HEAD"), cancellable = true)
	private static void addExtraGold(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
		ci.cancel();

	}

	@Inject(method = "addExtraEmeralds", at = @At("HEAD"), cancellable = true)
	private static void addExtraEmeralds(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
		ci.cancel();
	}

//	@Inject(method = "addAncientDebris", at = @At("HEAD"), cancellable = true)
//	private static void addAncientDebris(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
//		ci.cancel();
//	}

	@Inject(method = "addNetherDefaultOres", at = @At("HEAD"), cancellable = true)
	private static void addNetherDefaultOres(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_GRAVEL_NETHER);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_BLACKSTONE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_DEBRIS_LARGE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_DEBRIS_SMALL);
		ci.cancel();
	}
}