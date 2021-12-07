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

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

// Credit: https://github.com/LatvianModder/CommonResources/blob/master/src/main/java/dev/latvian/mods/commonresources/CombinedTextureProvider.java

public abstract class CombinedTextureProvider implements IDataProvider {
	private static float[] rgb(int col)
	{
		return new float[] {((col >> 16) & 0xFF) / 255F, ((col >> 8) & 0xFF) / 255F, ((col >> 0) & 0xFF) / 255F};
	}

	public static class Layer
	{
		public final int color;
		public final String name;
		public float[] multiply;
		public float[] add;

		public Layer(int c, String n)
		{
			color = c;
			name = n;
			multiply = new float[] {1F, 1F, 1F};
			add = new float[] {0F, 0F, 0F};
		}
	}

	public static class CachedImage
	{
		public final int width;
		public final int height;
		public final int[] pixels;
		public final Int2ObjectOpenHashMap<Layer> layerMap;

		public CachedImage(int w, int h, int[] p)
		{
			width = w;
			height = h;
			pixels = p;
			layerMap = new Int2ObjectOpenHashMap<>();
		}

		@Nullable
		public Layer getLayer(int color)
		{
			return layerMap.get(0xFF000000 | color);
		}
	}

	public static class CombinedTexture
	{
		public final String output;
		public final String input;
		public final Map<String, Integer> layers;

		public CombinedTexture(String out, String in)
		{
			output = out;
			input = in;
			layers = new LinkedHashMap<>();
		}

		public CombinedTexture layerColor(String texture, int color)
		{
			layers.put(texture, color);
			return this;
		}
	}

	private final DataGenerator gen;
	private final ExistingFileHelper existingFileHelper;
	private final String modid;
	private final Map<String, CachedImage> textureCache;
	private final Map<String, CombinedTexture> map;

	public CombinedTextureProvider(DataGenerator g, ExistingFileHelper efh, String mod)
	{
		gen = g;
		existingFileHelper = efh;
		modid = mod;
		textureCache = new HashMap<>();
		map = new HashMap<>();
	}

	public CombinedTexture createTexture(String outputTexture, String inputPath)
	{
		CombinedTexture texture = new CombinedTexture(outputTexture, inputPath);
		map.put(outputTexture, texture);
		return texture;
	}

	public abstract void registerTextures();

	private boolean validateTexture(ResourceLocation texturePath)
	{
		boolean valid = existingFileHelper.exists(texturePath, ResourcePackType.CLIENT_RESOURCES, ".png", "textures");
		if (!valid)
		{
			String path = texturePath.getNamespace() + ":textures/" + texturePath.getPath() + ".png";
			EmendatusEnigmatica.LOGGER.info("Unable to find corresponding PNG files '{}'", path);
		}
		return valid;
	}

	@Override
	public void run(DirectoryCache cache) throws IOException
	{
		registerTextures();

		for (CombinedTexture texture : map.values())
		{
//			if (existingFileHelper.exists(new ResourceLocation(modid, texture.output), ResourcePackType.CLIENT_RESOURCES, ".png", "textures"))
			if(validateTexture(new ResourceLocation(modid, texture.output)))
			{
//				EmendatusEnigmatica.LOGGER.info("Skipping " + texture.output + " as it already exists");
				continue;
			}

			CachedImage cachedImage = textureCache.computeIfAbsent(texture.input, s -> {
				try (InputStream stream = existingFileHelper.getResource(new ResourceLocation(modid, s), ResourcePackType.CLIENT_RESOURCES, ".png", "textures").getInputStream())
				{
					BufferedImage img = ImageIO.read(stream);
					CachedImage c = new CachedImage(img.getWidth(), img.getHeight(), img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth()));

					try (InputStream stream1 = existingFileHelper.getResource(new ResourceLocation(modid, s), ResourcePackType.CLIENT_RESOURCES, ".colormap", "textures").getInputStream())
					{
						BufferedReader reader = new BufferedReader(new InputStreamReader(stream1, StandardCharsets.UTF_8));

						String line;

						while ((line = reader.readLine()) != null)
						{
							String[] l = line.trim().split(":", 2);

							if (l.length == 2 && l[0].trim().length() > 0 && l[1].trim().length() > 0)
							{
								String[] l1 = l[1].trim().split(" ");
								Layer layer = new Layer(0xFF000000 | Integer.decode("#" + l[0].trim()), l1[0].trim());
								c.layerMap.put(layer.color, layer);
							}
						}
					}

					return c;
				}
				catch (Exception ex)
				{
					throw new RuntimeException(ex);
				}
			});

			int[] combinedPixels = new int[cachedImage.pixels.length];

			for (int i = 0; i < combinedPixels.length; i++)
			{
				int a = (cachedImage.pixels[i] >> 24) & 0xFF;

				if (a == 0)
				{
					combinedPixels[i] = 0x00FFFFFF;
					continue;
				}

				Layer layer = cachedImage.getLayer(cachedImage.pixels[i]);

				if (layer == null)
				{
					combinedPixels[i] = cachedImage.pixels[i];
					continue;
				}

				float[] col = rgb(texture.layers.getOrDefault(layer.name, 0xFFFFFF));
				col[0] += layer.add[0];
				col[1] += layer.add[1];
				col[2] += layer.add[2];
				col[0] *= layer.multiply[0];
				col[1] *= layer.multiply[1];
				col[2] *= layer.multiply[2];

				combinedPixels[i] = (a << 24)
						| (MathHelper.clamp((int) (col[0] * 255F), 0, 255) << 16)
						| (MathHelper.clamp((int) (col[1] * 255F), 0, 255) << 8)
						| (MathHelper.clamp((int) (col[2] * 255F), 0, 255) << 0)
				;
			}

			Path target = gen.getOutputFolder().resolve("assets/" + modid + "/textures/" + texture.output + ".png");

			BufferedImage combinedImage = new BufferedImage(cachedImage.width, cachedImage.height, BufferedImage.TYPE_INT_ARGB);
			combinedImage.setRGB(0, 0, cachedImage.width, cachedImage.height, combinedPixels, 0, cachedImage.width);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(combinedImage, "PNG", out);
			byte[] data = out.toByteArray();

			String hash = IDataProvider.SHA1.hashBytes(data).toString();
			if (!Objects.equals(cache.had(target), hash) || !Files.exists(target))
			{
				Files.createDirectories(target.getParent());

				try (OutputStream outputStream = Files.newOutputStream(target))
				{
					outputStream.write(data);
				}
			}

			cache.putNew(target, hash);
		}
	}

	@Override
	public String getName()
	{
		return "Emendatus Enigmatica CombinedTextures";
	}
}