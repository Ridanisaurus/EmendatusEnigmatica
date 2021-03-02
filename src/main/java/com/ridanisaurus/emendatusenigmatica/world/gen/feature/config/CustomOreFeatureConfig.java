package com.ridanisaurus.emendatusenigmatica.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;

public class CustomOreFeatureConfig implements IFeatureConfig {
    public static final Codec<CustomOreFeatureConfig> CODEC = RecordCodecBuilder.create((x) -> x.group(
            MultiStrataRuleTest.CODEC.fieldOf("target").forGetter(it -> (MultiStrataRuleTest) it.target)
    ).apply(x, CustomOreFeatureConfig::new));
    public final RuleTest target;

    public CustomOreFeatureConfig(RuleTest target) {
        this.target = target;
    }
}
