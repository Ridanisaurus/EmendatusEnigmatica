package com.ridanisaurus.emendatusenigmatica.core.mixin;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeDefaultFeatures.class)
public class DefaultBiomeFeaturesMixin {

	// TODO [RID] (maybe) add config option to overwrite vanilla ores
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
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_GRAVEL_NETHER);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_BLACKSTONE);
		ci.cancel();
	}

	// TODO [RID] Remove NetherQuartz and Gold from Basalt Deltas - BiomeMarker.java -> basaltDeltasBiome
}