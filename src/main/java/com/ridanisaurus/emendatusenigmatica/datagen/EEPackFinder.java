/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.datagen;

import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePackInfo;

import java.nio.file.Path;
import java.util.function.Consumer;

public class EEPackFinder implements IPackFinder {


	private final PackType type;

	public EEPackFinder(PackType type) {

		this.type = type;
	}

	@Override
	public void findPacks(Consumer<ResourcePackInfo> infoConsumer, ResourcePackInfo.IFactory infoFactory) {
		Path rootPath = DataGeneratorFactory.ROOT_PATH;

		ResourcePackInfo pack = ResourcePackInfo.createResourcePack("emendatusenigmatica_" + type.getSuffix(), true,
				() -> new InMemoryPack(rootPath), infoFactory, ResourcePackInfo.Priority.BOTTOM, IPackNameDecorator.PLAIN);
		if (pack != null) {
			infoConsumer.accept(pack);
		}
	}
}
