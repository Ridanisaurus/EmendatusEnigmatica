/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.api;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Type;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A util class used to search for specific annotations
 */
public class AnnotationUtil {

    /**
     * A method to search classes using a specific annotation in mods
     * @param annotation The annotation class to search
     * @return A list of classes that have the annotation
     */
    public static @NotNull List<Class<?>> getAnnotatedClasses(Class<? extends Annotation> annotation) {
        List<Class<?>> classList = new ArrayList<>();
        Type type = Type.getType(annotation);
        for (ModFileScanData allScanDatum : ModList.get().getAllScanData()) {
            for (ModFileScanData.AnnotationData allScanDatumAnnotation : allScanDatum.getAnnotations()) {
                if (Objects.equals(allScanDatumAnnotation.annotationType(), type)) {
                    try {
                        classList.add(Class.forName(allScanDatumAnnotation.memberName()));
                    } catch (ClassNotFoundException e) {
                        EmendatusEnigmatica.logger.error("Exception while scanning for annotated classes!", e);
                    }
                }
            }
        }
        return classList;
    }
}
