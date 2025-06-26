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

package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class EEVirtualPackHandler implements PackResources {
    private final Logger logger;
    private final Path path;
    private final PackLocationInfo locationInfo;

    public EEVirtualPackHandler(Path path, PackType type) {
        this.path = path;
        if (type == PackType.CLIENT_RESOURCES) {
            this.locationInfo = new PackLocationInfo(
                "EE Virtual Resource Pack",
                Component.translatable("resourcepack.emendatusenigmatica.client.title"),
                PackSource.DEFAULT,
                Optional.empty()
            );
        } else {
            this.locationInfo = new PackLocationInfo(
                "EE Virtual Data Pack",
                Component.translatable("resourcepack.emendatusenigmatica.server.title"),
                PackSource.DEFAULT,
                Optional.empty()
            );
        }
        this.logger = LoggerFactory.getLogger(locationInfo.id() + " Handler");
    }

    private static @NotNull String getFullPath(@NotNull PackType type, @NotNull ResourceLocation location) {
        return String.format("%s/%s/%s", type.getDirectory(), location.getNamespace(), location.getPath());
    }

    @Override
    public IoSupplier<InputStream> getRootResource(String @NotNull ... fileName) {
        //FIXME No idea how this is supposed to work? For some reason now it accepts multiple inputs but single output? Just ignore all except first element?
        if (fileName.length == 0) return null;
        Path file = path.resolve(fileName[0]);
        if (Files.notExists(file)) return null;
        return IoSupplier.create(file);
    }

    @Override
    public IoSupplier<InputStream> getResource(@NotNull PackType type, @NotNull ResourceLocation location) {
        Path file = path.resolve(getFullPath(type, location));
        if (Files.notExists(file)) return null;
        return IoSupplier.create(file);
    }

    @Override
    public void listResources(@NotNull PackType type, @NotNull String namespaceIn, @NotNull String pathIn, @NotNull ResourceOutput resourceOutput) {
        getChildResourceLocations(resourceOutput, 0, path.resolve(type.getDirectory() + "/" + namespaceIn + "/" + pathIn), namespaceIn, pathIn);
    }

    @Override
    public @NotNull PackLocationInfo location() {
        return locationInfo;
    }

    //TODO: Test if some mod (like apotheosis) lists all of the resources with a filter, this doesn't cause issues (like it used to in 1.19.2)
    private void getChildResourceLocations(ResourceOutput rsOut, int depth, Path current, String currentRLNS, String currentRLPath) {
        if (Files.notExists(current) || !Files.isDirectory(current)) return;
        try (Stream<Path> list = Files.list(current)) {
            for (Path child : list.toList()) {
                if (!Files.isDirectory(child)) {
                    ResourceLocation loc = ResourceLocation.fromNamespaceAndPath(currentRLNS, currentRLPath + "/" + child.getFileName());
                    rsOut.accept(loc, IoSupplier.create(child));
                } else {
                    getChildResourceLocations(rsOut, depth + 1, child, currentRLNS, currentRLPath + "/" + child.getFileName());
                }
            }
        } catch (IOException e) {
            logger.error("Exception caught while iterating generated resource folder!", e);
        }
    }

    @Override
    public @NotNull Set<String> getNamespaces(@NotNull PackType type) {
        Set<String> result = new HashSet<>();
        Path dir = path.resolve(type.getDirectory());
        try {
            if (Files.notExists(dir)) Files.createDirectories(dir);
            
            try (Stream<Path> list = Files.list(dir)) {
                for (Path resultingPath : list.toList()) result.add(resultingPath.getFileName().toString());
            }
        } catch (IOException e) {
            logger.error("Exception caught while iterating generated resource folder!", e);
        }
        return result;
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(@NotNull MetadataSectionSerializer<T> deserializer) {
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
    public void close() {

    }
}