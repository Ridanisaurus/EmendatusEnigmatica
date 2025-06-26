/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
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

import com.ridanisaurus.emendatusenigmatica.loader.EEVirtualPackHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.world.flag.FeatureFlagSet;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

public class EEPackFinder implements RepositorySource {

	private final PackType type;
	private EEVirtualPackHandler pack;

	public EEPackFinder(PackType type) {
		this.type = type;
	}

	//TODO: Test if this implementation doesn't cause memory / performance issues.
	@Override
	public void loadPacks(@NotNull Consumer<Pack> onLoad) {
		Path rootPath = DataGeneratorFactory.ROOT_PATH;
		if (this.pack == null) this.pack = new EEVirtualPackHandler(rootPath, type);

		onLoad.accept(new Pack(
			pack.location(),
			new Pack.ResourcesSupplier() {
				@Override
				public @NotNull PackResources openPrimary(@NotNull PackLocationInfo location) {
					if (location.id().equals(pack.location().id())) return pack;
					return null;
				}

				@Override
				public @NotNull PackResources openFull(@NotNull PackLocationInfo location, Pack.@NotNull Metadata metadata) {
					if (location.id().equals(pack.location().id())) return pack;
					return null;
				}
			},
			new Pack.Metadata(
				(type == PackType.CLIENT_RESOURCES)?
					Component.translatable("resourcepack.emendatusenigmatica.client.desc"):
					Component.translatable("resourcepack.emendatusenigmatica.server.desc"),
				PackCompatibility.COMPATIBLE,
				FeatureFlagSet.of(),
				List.of(),
				false
			),
			new PackSelectionConfig(true, Pack.Position.BOTTOM, true)
		));
	}
}
