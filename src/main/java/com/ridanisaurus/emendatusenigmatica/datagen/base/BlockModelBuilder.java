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

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.math.Vector3f;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BlockModelBuilder{

	protected final String parent;
	protected final List<ElementBuilder> elements = new ArrayList<>();
	protected final Map<String, String> textures = new LinkedHashMap<>();
	private final Map<String, BlockModelBuilder> childModels = new LinkedHashMap<>();
	private final List<String> itemRenderOrder = new ArrayList<>();
	protected String customLoader = null;
	protected String renderType = null;

	protected ResourceLocation location;

	public BlockModelBuilder(String parent) {
		this.parent = parent;
	}

	public BlockModelBuilder setLoader(String loader) {
		this.customLoader = loader;
		return this;
	}

	public BlockModelBuilder texture(String key, String texture) {
		if (texture.charAt(0) == '#') {
			this.textures.put(key, texture);
			return self();
		} else {
			ResourceLocation asLoc;
			if (texture.contains(":")) {
				asLoc = new ResourceLocation(texture);
			} else {
				asLoc = new ResourceLocation(getLocation().getNamespace(), texture);
			}
			return texture(key, asLoc);
		}
	}

	public BlockModelBuilder texture(String key, ResourceLocation texture) {
		this.textures.put(key, texture.toString());
		return self();
	}

	public BlockModelBuilder renderType(String renderType) {
		return renderType(new ResourceLocation(renderType));
	}

	public BlockModelBuilder renderType(ResourceLocation renderType) {
		this.renderType = renderType.toString();
		return self();
	}

	public ElementBuilder element() {
		ElementBuilder ret = new ElementBuilder();
		elements.add(ret);
		return ret;
	}

	private String serializeLocOrKey(String tex) {
		if (tex.charAt(0) == '#') {
			return tex;
		}
		return new ResourceLocation(tex).toString();
	}

	private JsonArray serializeVector3f(Vector3f vec) {
		JsonArray ret = new JsonArray();
		ret.add(serializeFloat(vec.x()));
		ret.add(serializeFloat(vec.y()));
		ret.add(serializeFloat(vec.z()));
		return ret;
	}

	private Number serializeFloat(float f) {
		if ((int) f == f) {
			return (int) f;
		}
		return f;
	}

	public void save(Consumer<IFinishedGenericJSON> consumer, ResourceLocation jsonResourceLocation) {
		consumer.accept(new Result(jsonResourceLocation, this.parent, this.customLoader, this.elements, this.textures, this.renderType, this.childModels, this.itemRenderOrder));
	}

	public ResourceLocation getLocation() {
		return location;
	}

	private BlockModelBuilder self() { return (BlockModelBuilder) this; }

	public class ElementBuilder {
		private Vector3f from = new Vector3f();
		private Vector3f to = new Vector3f(16, 16, 16);
		private final Map<Direction, FaceBuilder> faces = new LinkedHashMap<>();

		public ElementBuilder from(float x, float y, float z) {
			this.from = new Vector3f(x, y, z);
			return this;
		}

		public ElementBuilder to(float x, float y, float z) {
			this.to = new Vector3f(x, y, z);
			return this;
		}

		public FaceBuilder face(Direction dir) {
			Preconditions.checkNotNull(dir, "Direction must not be null");
			return faces.computeIfAbsent(dir, FaceBuilder::new);
		}

		public ElementBuilder allFaces(BiConsumer<Direction, FaceBuilder> action) {
			Arrays.stream(Direction.values())
					.forEach(d -> action.accept(d, face(d)));
			return this;
		}

		public ElementBuilder faces(BiConsumer<Direction, FaceBuilder> action) {
			faces.entrySet().stream()
					.forEach(e -> action.accept(e.getKey(), e.getValue()));
			return this;
		}

		public ElementBuilder texture(String texture) {
			return faces(addTexture(texture));
		}

		public ElementBuilder cube(String texture) {
			return allFaces(addTexture(texture).andThen((dir, f) -> f.cullface(dir)));
		}

		private BiConsumer<Direction, FaceBuilder> addTexture(String texture) {
			return ($, f) -> f.texture(texture);
		}

		BlockElement build() {
			Map<Direction, BlockElementFace> faces = this.faces.entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().build(), (k1, k2) -> { throw new IllegalArgumentException(); }, LinkedHashMap::new));
			return new BlockElement(from, to, faces, null, true);
		}

		public BlockModelBuilder end() { return self(); }

		public class FaceBuilder {
			private Direction cullface;
			private int tintindex = -1;
			private String texture;
			private float[] uvs;
			private FaceRotation rotation = FaceRotation.ZERO;
			private int emissivity = 0;
			private boolean hasAmbientOcclusion = true;

			FaceBuilder(Direction dir) {
				// param unused for functional match
			}

			public FaceBuilder cullface(@Nullable Direction dir) {
				this.cullface = dir;
				return this;
			}

			public FaceBuilder tintindex(int index) {
				this.tintindex = index;
				return this;
			}

			public FaceBuilder texture(String texture) {
				Preconditions.checkNotNull(texture, "Texture must not be null");
				this.texture = texture;
				return this;
			}

			public FaceBuilder uvs(float u1, float v1, float u2, float v2) {
				this.uvs = new float[] { u1, v1, u2, v2 };
				return this;
			}

			public FaceBuilder rotation(FaceRotation rot) {
				Preconditions.checkNotNull(rot, "Rotation must not be null");
				this.rotation = rot;
				return this;
			}

			public FaceBuilder emissivity(int emissivity) {
				this.emissivity = emissivity;
				return this;
			}

			public FaceBuilder emissive() {
				return emissivity(15);
			}

			public FaceBuilder ao(boolean ao) {
				this.hasAmbientOcclusion = ao;
				return this;
			}

			BlockElementFace build() {
				if (this.texture == null) {
					throw new IllegalStateException("A model face must have a texture");
				}
				return new BlockElementFace(cullface, tintindex, texture, new BlockFaceUV(uvs, rotation.rotation), emissivity, hasAmbientOcclusion);
			}

			public ElementBuilder end() { return ElementBuilder.this; }
		}

		public class BlockElementFace {
			public static final int NO_TINT = -1;
			public final Direction cullForDirection;
			public final int tintIndex;
			public final String texture;
			public final BlockFaceUV uv;
			public final int emissivity;
			public final boolean hasAmbientOcclusion;

			public BlockElementFace(@Nullable Direction direction, int tint, String texture, BlockFaceUV uv, int emissivity, boolean hasAmbientOcclusion) {
				this.cullForDirection = direction;
				this.tintIndex = tint;
				this.texture = texture;
				this.uv = uv;
				this.emissivity = emissivity;
				this.hasAmbientOcclusion = hasAmbientOcclusion;
			}
		}

		public class BlockElement {
			private static final boolean DEFAULT_RESCALE = false;
			private static final float MIN_EXTENT = -16.0F;
			private static final float MAX_EXTENT = 32.0F;
			public final Vector3f from;
			public final Vector3f to;
			public final Map<Direction, BlockElementFace> faces;
			public final BlockElementRotation rotation;
			public final boolean shade;

			public BlockElement(Vector3f from, Vector3f to, Map<Direction, BlockElementFace> faces, @Nullable BlockElementRotation rotation, boolean shade) {
				this.from = from;
				this.to = to;
				this.faces = faces;
				this.rotation = rotation;
				this.shade = shade;
				this.fillUvs();
			}

			private void fillUvs() {
				for(Map.Entry<Direction, BlockElementFace> entry : this.faces.entrySet()) {
					float[] afloat = this.uvsByFace(entry.getKey());
					(entry.getValue()).uv.setMissingUv(afloat);
				}

			}

			public float[] uvsByFace(Direction direction) {
				switch (direction) {
					case DOWN:
						return new float[]{this.from.x(), 16.0F - this.to.z(), this.to.x(), 16.0F - this.from.z()};
					case UP:
						return new float[]{this.from.x(), this.from.z(), this.to.x(), this.to.z()};
					case NORTH:
					default:
						return new float[]{16.0F - this.to.x(), 16.0F - this.to.y(), 16.0F - this.from.x(), 16.0F - this.from.y()};
					case SOUTH:
						return new float[]{this.from.x(), 16.0F - this.to.y(), this.to.x(), 16.0F - this.from.y()};
					case WEST:
						return new float[]{this.from.z(), 16.0F - this.to.y(), this.to.z(), 16.0F - this.from.y()};
					case EAST:
						return new float[]{16.0F - this.to.z(), 16.0F - this.to.y(), 16.0F - this.from.z(), 16.0F - this.from.y()};
				}
			}
		}

		public class BlockElementRotation {
			public final Vector3f origin;
			public final Direction.Axis axis;
			public final float angle;
			public final boolean rescale;

			public BlockElementRotation(Vector3f vector3f, Direction.Axis axis, float angle, boolean rescale) {
				this.origin = vector3f;
				this.axis = axis;
				this.angle = angle;
				this.rescale = rescale;
			}
		}

		public class BlockFaceUV {
			public float[] uvs;
			public final int rotation;

			public BlockFaceUV(@Nullable float[] uvs, int rotation) {
				this.uvs = uvs;
				this.rotation = rotation;
			}

			public void setMissingUv(float[] uvs) {
				if (this.uvs == null) {
					this.uvs = uvs;
				}

			}
		}
	}

	public enum FaceRotation {
		ZERO(0),
		CLOCKWISE_90(90),
		UPSIDE_DOWN(180),
		COUNTERCLOCKWISE_90(270),
		;

		final int rotation;

		private FaceRotation(int rotation) {
			this.rotation = rotation;
		}
	}

	public BlockModelBuilder child(String name, BlockModelBuilder modelBuilder){
		childModels.put(name, modelBuilder);
		itemRenderOrder.add(name);
		return this;
	}

	public BlockModelBuilder itemRenderOrder(String... names){
		for (String name : names)
			if (!childModels.containsKey(name))
				throw new IllegalArgumentException("names contains \"" + name + "\", which is not a child of this model");
		itemRenderOrder.clear();
		itemRenderOrder.addAll(Arrays.asList(names));
		return this;
	}

	public JsonObject toJson(JsonObject baseJson) {
		baseJson.addProperty("parent", this.parent);
		if (this.renderType != null) {
			baseJson.addProperty("render_type", this.renderType);
		}
		if (this.customLoader != null) {
			baseJson.addProperty("loader", this.customLoader);
		}
		if (!this.textures.isEmpty()) {
			JsonObject textures = new JsonObject();
			for (Map.Entry<String, String> e : this.textures.entrySet()) {
				textures.addProperty(e.getKey(), serializeLocOrKey(e.getValue()));
			}
			baseJson.add("textures", textures);
		}
		if (!this.elements.isEmpty()) {
			JsonArray elements = new JsonArray();
			this.elements.stream().map(ElementBuilder::build).forEach(part -> {
				JsonObject partObj = new JsonObject();
				partObj.add("from", serializeVector3f(part.from));
				partObj.add("to", serializeVector3f(part.to));

				JsonObject faces = new JsonObject();
				for (Direction dir : Direction.values()) {
					ElementBuilder.BlockElementFace face = part.faces.get(dir);
					if (face == null) continue;

					JsonObject faceObj = new JsonObject();
					faceObj.addProperty("texture", serializeLocOrKey(face.texture));
					if (!Arrays.equals(face.uv.uvs, part.uvsByFace(dir))) {
						faceObj.add("uv", new Gson().toJsonTree(face.uv.uvs));
					}
					if (face.cullForDirection != null) {
						faceObj.addProperty("cullface", face.cullForDirection.getSerializedName());
					}
					if (face.uv.rotation != 0) {
						faceObj.addProperty("rotation", face.uv.rotation);
					}
					if (face.tintIndex != -1) {
						faceObj.addProperty("tintindex", face.tintIndex);
					}
					faces.add(dir.getSerializedName(), faceObj);
				}
				if (!part.faces.isEmpty()) {
					partObj.add("faces", faces);
				}
				elements.add(partObj);
			});
			baseJson.add("elements", elements);
		}
		return baseJson;
	}

	public JsonObject childJson(JsonObject json)
	{
		JsonObject children = new JsonObject();
		for(Map.Entry<String, BlockModelBuilder> entry : childModels.entrySet())
		{
			children.add(entry.getKey(), entry.getValue().toJson(new JsonObject()));
		}
		json.add("children", children);

		JsonArray itemRenderOrder = new JsonArray();
		for (String name : this.itemRenderOrder) {
			itemRenderOrder.add(name);
		}
		json.add("item_render_order", itemRenderOrder);

		return json;
	}

	public class Result implements IFinishedGenericJSON {
		private final ResourceLocation id;
		private final String parent;
		private final String customLoader;
		private final List<ElementBuilder> elements;
		private final Map<String, String> textures;
		private final Map<String, BlockModelBuilder> childModels;
		private final List<String> itemRenderOrder;

		protected String renderType;

		public Result(ResourceLocation id, String parent, @Nullable String customLoader, List<ElementBuilder> elements, Map<String, String> textures, @Nullable String renderType, @Nullable Map<String, BlockModelBuilder> childModels, @Nullable List<String> itemRenderOrder) {
			this.id = id;
			this.parent = parent;
			this.customLoader = customLoader;
			this.elements = elements;
			this.textures = textures;
			this.renderType = renderType;
			this.childModels = childModels;
			this.itemRenderOrder = itemRenderOrder;
		}

		public void serializeJSONData(JsonObject json) {
			toJson(json);
			if(!this.childModels.isEmpty()) {
				childJson(json);
			}
		}

		public ResourceLocation getId() {
			return this.id;
		}
	}
}