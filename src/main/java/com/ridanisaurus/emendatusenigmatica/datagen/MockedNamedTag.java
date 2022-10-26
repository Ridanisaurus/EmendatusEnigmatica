package com.ridanisaurus.emendatusenigmatica.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.ArrayList;
import java.util.List;
// TODO: [Buuz] Previously, this class implemented the interface of ITag.INamedTag<T>
public class MockedNamedTag<T> implements TagKey<T> {
	private final ResourceLocation name;

	public MockedNamedTag(ResourceLocation name) {
		this.name = name;
	}

	@Override
	public ResourceLocation getName() {
		return name;
	}

	@Override
	public boolean contains(T element) {
		return false;
	}

	@Override
	public List<T> getValues() {
		return new ArrayList<>();
	}
}
