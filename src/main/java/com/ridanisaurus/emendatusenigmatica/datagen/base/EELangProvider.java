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

package com.ridanisaurus.emendatusenigmatica.datagen.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public abstract class EELangProvider implements DataProvider {
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().setLenient().create();
	private final Map<String, String> data = new TreeMap<>();
	private final DataGenerator gen;
	private final String modid;
	private final String locale;

	public EELangProvider(DataGenerator gen, String modid, String locale) {
		this.gen = gen;
		this.modid = modid;
		this.locale = locale;
	}

	protected abstract void addTranslations();

	@Override
	public void run(CachedOutput cachedOutput) throws IOException {
		addTranslations();
		if (!data.isEmpty())
			save(cachedOutput, data, this.gen.getOutputFolder().resolve("assets/" + modid + "/lang/" + locale + ".json"));
	}

	@Override
	public String getName() {
		return "Languages: " + locale;
	}

	private void save(CachedOutput cache, Object object, Path target) throws IOException {
		// TODO: DataProvider.saveStable handles the caching and hashing already, but creating the JSON Object this way seems unreliable. -C
		JsonObject json = new JsonObject();
		for (Map.Entry<String, String> pair : data.entrySet()) {
			json.addProperty(pair.getKey(), pair.getValue());
		}

		DataProvider.saveStable(cache, json, target);
	}

	public void addBlock(Supplier<? extends Block> key, String name) {
		add(key.get(), name);
	}

	public void add(Block key, String name) {
		add(key.getDescriptionId(), name);
	}

	public void addItem(Supplier<? extends Item> key, String name) {
		add(key.get(), name);
	}

	public void add(Item key, String name) {
		add(key.getDescriptionId(), name);
	}

	public void addItemStack(Supplier<ItemStack> key, String name) {
		add(key.get(), name);
	}

	public void add(ItemStack key, String name) {
		add(key.getDescriptionId(), name);
	}

	public void addEnchantment(Supplier<? extends Enchantment> key, String name) {
		add(key.get(), name);
	}

	public void add(Enchantment key, String name) {
		add(key.getDescriptionId(), name);
	}

	public void addEffect(Supplier<? extends MobEffect> key, String name) {
		add(key.get(), name);
	}

	public void add(MobEffect key, String name) {
		add(key.getDescriptionId(), name);
	}

	public void addEntityType(Supplier<? extends EntityType<?>> key, String name) {
		add(key.get(), name);
	}

	public void add(EntityType<?> key, String name) {
		add(key.getDescriptionId(), name);
	}

	public void add(String key, String value) {
		if (data.put(key, value) != null)
			throw new IllegalStateException("Duplicate translation key " + key);
	}
}
