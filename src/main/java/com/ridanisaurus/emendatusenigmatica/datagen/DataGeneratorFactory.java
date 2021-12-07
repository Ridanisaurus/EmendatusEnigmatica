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

import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

public class DataGeneratorFactory {

    public static Path ROOT_PATH;

    public static void init() {
        ROOT_PATH = FMLPaths.CONFIGDIR.get().resolve("emendatusenigmatica/default");
    }

//    public static void init() {
//        try {
//            FileSystem fs = MemoryFileSystemBuilder.newEmpty().addRoot("/").setCurrentWorkingDirectory("/emendatusenigmatica").setCaseSensitive(true).build();
//            ROOT_PATH = fs.getPath("/emendatusenigmatica");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static DataGenerator createMemoryDataGenerator() {
        return new DataGenerator(ROOT_PATH, ImmutableList.of());
    }
}
