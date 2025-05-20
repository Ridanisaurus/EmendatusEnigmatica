package com.ridanisaurus.emendatusenigmatica.items;

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
            Component.translatable("smithing_template.emendatusenigmatica.shield_upgrade.applies_to"),
            Component.translatable("smithing_template.emendatusenigmatica.shield_upgrade.ingredients"),
            Component.translatable("smithing_template.emendatusenigmatica.shield_upgrade.upgrade_desc"),
            Component.translatable("smithing_template.emendatusenigmatica.shield_upgrade.base_slot_desc"),
            Component.translatable("smithing_template.emendatusenigmatica.shield_upgrade.addition_slot_desc"),
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
