package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassHelper {
    /**
     * A method to search classes using a specific annotation in mods
     * @param annotation The annotation class to search
     * @return A list of classes that have the annotation
     */
    public static @NotNull List<Class<?>> getAnnotatedClasses(Class<? extends Annotation> annotation) {
        List<Class<?>> classList = new ArrayList<>();
        org.objectweb.asm.Type type = org.objectweb.asm.Type.getType(annotation);
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

    /**
     * Attempts to get the public no-argument constructor for a given class.
     * @param clazz The class to check.
     * @return The Constructor if found, otherwise null.
     */
    @Contract(pure = true)
    public static @Nullable Constructor<?> getNoArgConstructor(@NotNull Class<?> clazz) {
        try {
            return clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /**
     * Recursively finds the actual type argument for a generic interface
     * as implemented by a specific class (or its superclasses/superinterfaces).
     *
     * @param clazz The class to start the search from.
     * @param genericInterface The generic interface
     * @return A Class<?> of the generic type argument if found and resolved, null otherwise.
     */
    public static @Nullable Class<?> getGenericInterfaceType(@NotNull Class<?> clazz, @NotNull Class<?> genericInterface) {
        if (!genericInterface.isAssignableFrom(clazz) || clazz.equals(Object.class)) return null;

        for (Type type : clazz.getGenericInterfaces()) {
            if (type instanceof ParameterizedType pt && pt.getRawType().equals(genericInterface)) {
                Type[] args = pt.getActualTypeArguments();
                if (args.length > 0 && args[0] instanceof Class) return (Class<?>) args[0];
            }
        }

        return getGenericInterfaceType(clazz.getSuperclass(), genericInterface);
    }
}
