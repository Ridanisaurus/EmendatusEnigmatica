package com.ridanisaurus.emendatusenigmatica.api;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;
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
    public static List<Class> getAnnotatedClasses(Class<? extends Annotation> annotation) {
        List<Class> classList = new ArrayList<>();
        Type type = Type.getType(annotation);
        for (ModFileScanData allScanDatum : ModList.get().getAllScanData()) {
            for (ModFileScanData.AnnotationData allScanDatumAnnotation : allScanDatum.getAnnotations()) {
                if (Objects.equals(allScanDatumAnnotation.annotationData(), type)) {
                    try {
                        classList.add(Class.forName(allScanDatumAnnotation.memberName()));
                    } catch (ClassNotFoundException e) {
                        EmendatusEnigmatica.LOGGER.error(e);
                    }
                }
            }
        }
        return classList;
    }
}
