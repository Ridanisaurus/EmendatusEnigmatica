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

package com.ridanisaurus.emendatusenigmatica.items;

import com.ridanisaurus.emendatusenigmatica.datagen.gen.LangGen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

/**
 * A basic SmithingTemplateItem, required for Smithing Table recipes. Used only for EE shield crafting.
 */
public class ShieldTemplate extends SmithingTemplateItem {
    public ShieldTemplate() {
        super(
            LangGen.get("smithing_template", "shield_upgrade.applies_to"),
            LangGen.get("smithing_template", "shield_upgrade.ingredients"),
            LangGen.get("smithing_template", "shield_upgrade.upgrade_desc"),
            LangGen.get("smithing_template", "shield_upgrade.base_slot_desc"),
            LangGen.get("smithing_template", "shield_upgrade.additional_slot_desc"),
            List.of(ResourceLocation.withDefaultNamespace("item/empty_armor_slot_shield")),
            List.of(
                ResourceLocation.withDefaultNamespace("item/empty_slot_ingot"),
                ResourceLocation.withDefaultNamespace("item/empty_slot_redstone_dust"),
                ResourceLocation.withDefaultNamespace("item/empty_slot_quartz"),
                ResourceLocation.withDefaultNamespace("item/empty_slot_emerald"),
                ResourceLocation.withDefaultNamespace("item/empty_slot_diamond"),
                ResourceLocation.withDefaultNamespace("item/empty_slot_lapis_lazuli"),
                ResourceLocation.withDefaultNamespace("item/empty_slot_amethyst_shard")
            )
        );
    }
}
