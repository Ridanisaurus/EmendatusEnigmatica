package com.ridanisaurus.emendatusenigmatica.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to register {@link com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EmendatusPluginReference {

    String modid();

    String name();

}
