package com.ridanisaurus.emendatusenigmatica.datagen;

import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class MockedNamedTag<T> implements ITag.INamedTag<T> {
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
