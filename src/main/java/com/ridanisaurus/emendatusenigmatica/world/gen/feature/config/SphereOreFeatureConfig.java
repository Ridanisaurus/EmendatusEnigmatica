package com.ridanisaurus.emendatusenigmatica.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class SphereOreFeatureConfig implements FeatureConfiguration {
	public static final Codec<SphereOreFeatureConfig> CODEC = RecordCodecBuilder.create((x) -> x.group(
			MultiStrataRuleTest.CODEC.fieldOf("target").forGetter(it -> (MultiStrataRuleTest) it.target)
	).apply(x, SphereOreFeatureConfig::new));
	public final RuleTest target;

	public SphereOreFeatureConfig(RuleTest target) {
		this.target = target;
	}
}
