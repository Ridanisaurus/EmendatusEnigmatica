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

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.util.GsonHelper;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratedPack implements PackResources {
    private final Path path;

    public GeneratedPack(Path path) {
        EmendatusEnigmatica.generate();
        this.path = path;
    }

    private static String getFullPath(PackType type, ResourceLocation location) {
        return String.format("%s/%s/%s", type.getDirectory(), location.getNamespace(), location.getPath());
    }

    @Override
    public InputStream getRootResource(String fileName) throws IOException {
        Path resolved = path.resolve(fileName);
        return Files.newInputStream(resolved);
    }

    @Override
    public InputStream getResource(PackType type, ResourceLocation location) throws IOException {
        Path resolved = path.resolve(getFullPath(type, location));
        if (!Files.exists(resolved)){
            throw new IOException("Resource does not exist");
        }
        return Files.newInputStream(resolved);
    }

    @Override
    public Collection<ResourceLocation> getResources(PackType type, String namespaceIn, String pathIn, Predicate<ResourceLocation> filterIn) {
        List<ResourceLocation> result = new ArrayList<>();
        getChildResourceLocations(result, 0, filterIn, path.resolve(type.getDirectory() + "/" + namespaceIn + "/" + pathIn), namespaceIn, pathIn);
        return result;
    }

    private void getChildResourceLocations(List<ResourceLocation> result, int depth, Predicate<ResourceLocation> filter, Path current, String currentRLNS, String currentRLPath) {
        try {
            if (!Files.exists(current) || !Files.isDirectory(current)){
                return;
            }
            Stream<Path> list = Files.list(current);
            for (Path child : list.collect(Collectors.toList())) {
                if (!Files.isDirectory(child)) {
                    result.add(new ResourceLocation(currentRLNS, currentRLPath + "/" + child.getFileName()));
                    continue;
                }
                getChildResourceLocations(result, depth + 1, filter, child, currentRLNS, currentRLPath + "/" + child.getFileName());
            }
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }


    @Override
    public boolean hasResource(PackType type, ResourceLocation location) {
        Path finalPath = path.resolve(type.getDirectory() + "/" + location.getNamespace() + "/" + location.getPath());
        return Files.exists(finalPath);
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        Set<String> result = new HashSet<>();
        try {
            Stream<Path> list = Files.list(path.resolve(type.getDirectory()));
            for (Path resultingPath : list.collect(Collectors.toList())) {
                result.add(resultingPath.getFileName().toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> deserializer) throws IOException {
        JsonObject jsonobject = new JsonObject();
        JsonObject packObject = new JsonObject();
        packObject.addProperty("pack_format", 9);
        packObject.addProperty("description", "emendatusenigmatica");
        jsonobject.add("pack", packObject);
        if (!jsonobject.has(deserializer.getMetadataSectionName())) {
            return null;
        } else {
            try {
                return deserializer.fromJson(GsonHelper.getAsJsonObject(jsonobject, deserializer.getMetadataSectionName()));
            } catch (JsonParseException jsonparseexception) {
                return null;
            }
        }
    }

    @Override
    public String getName() {
        return "EE Generated Pack";
    }

    @Override
    public void close() {

    }
}