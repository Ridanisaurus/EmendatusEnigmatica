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

package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.plugin.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.processors.*;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public class WorldGenHelper {
	@Contract("_, _ -> new")
	public static @Unmodifiable List<PlacementModifier> orePlacement(PlacementModifier modifier_1, PlacementModifier modifier_2) {
		return List.of(modifier_1, InSquarePlacement.spread(), modifier_2, BiomeFilter.biome());
	}

	@Contract("_, _ -> new")
	public static @Unmodifiable List<PlacementModifier> commonOrePlacement(int chancePerChunk, PlacementModifier modifier) {
		return orePlacement(CountPlacement.of(chancePerChunk), modifier);
	}

	@Contract("_, _ -> new")
	public static @Unmodifiable List<PlacementModifier> rareOrePlacement(int chancePerChunk, PlacementModifier modifier) {
		return orePlacement(RarityFilter.onAverageOnceEvery(chancePerChunk), modifier);
	}

	public static List<PlacementModifier> getOrePlacement(@NotNull String rarity, int placementChance, PlacementModifier modifier) {
		return rarity.equals("common") ?
			commonOrePlacement(placementChance, modifier):
			rareOrePlacement(placementChance, modifier);
	}

	public static HeightRangePlacement getPlacementModifier(@NotNull String placement, int minY, int maxY) {
		return placement.equals("uniform") ?
			HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)) :
			HeightRangePlacement.triangle(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY));
	}

	public static List<PlacementModifier> getFullOrePlacement(@NotNull IDepositProcessor processor) {
		return switch (processor) {
			case VanillaDepositProcessor p -> 	getOrePlacement(p.getRarity(), p.getChance(), getPlacementModifier(p.getPlacement(), p.getMinY(), p.getMaxY()));
			case DikeDepositProcessor p -> 		getOrePlacement(p.getRarity(), p.getChance(), getPlacementModifier(p.getPlacement(), p.getMinY(), p.getMaxY()));
			case DenseDepositProcessor p -> 	getOrePlacement(p.getRarity(), p.getChance(), getPlacementModifier(p.getPlacement(), p.getMinY(), p.getMaxY()));
			case GeodeDepositProcessor p -> 	getOrePlacement(p.getRarity(), p.getChance(), getPlacementModifier(p.getPlacement(), p.getMinY(), p.getMaxY()));
			case SphereDepositProcessor p -> 	getOrePlacement(p.getRarity(), p.getChance(), getPlacementModifier(p.getPlacement(), p.getMinY(), p.getMaxY()));
			default -> null;
		};
	}
}
