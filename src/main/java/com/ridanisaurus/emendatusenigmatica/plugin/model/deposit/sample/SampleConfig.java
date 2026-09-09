/*
 * MIT License
 *
 * Copyright (c) 2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.SampleBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.SampleBlocksValidator;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;

import java.util.List;

public class SampleConfig {
    public static final Codec<SampleConfig> CODEC = RecordCodecBuilder.create(x -> x.group(
        Codec.INT.optionalFieldOf("chance", 0).forGetter(it -> it.chance),
        SampleBlockModel.CODEC.listOf().optionalFieldOf("blocks", List.of()).forGetter(it -> it.blocks.unwrap())
    ).apply(x, SampleConfig::new));

    public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
        .addValidator("chance", new NumberRangeValidator(Types.INTEGER, 0, 100))
        .addValidator("blocks", new SampleBlocksValidator());

    public final int chance;
    public final WeightedRandomList<SampleBlockModel> blocks;

    public SampleConfig() {
        this.chance = 0;
        this.blocks = WeightedRandomList.create();
    }

    public SampleConfig(int chance, List<SampleBlockModel> blocks) {
        this.chance = chance;
        this.blocks = WeightedRandomList.create(blocks);
    }

    public boolean shouldGenerateSample(RandomSource rand) {
        return this.chance > 0 && chance < rand.nextInt(100);
    }
}
