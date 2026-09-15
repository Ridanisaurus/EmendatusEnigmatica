/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.items.templates;

import com.ridanisaurus.emendatusenigmatica.plugin.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.jarjar.nio.util.Lazy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BasicShieldItem extends ShieldItem {
    private final Lazy<Ingredient> repairItem;
    private final boolean hasColor;
    private final int durability;
    private final int enchantment;
    private final int highlight1;
    private final int highlight2;
    private final int shadow1;
    private final int shadow2;
    private final int base;
    private final String id;

    public BasicShieldItem(@NotNull MaterialModel material, TagKey<Item> repairItem) {
        super(new Properties().durability(material.getArmor().getShield().getDurability()));
        this.repairItem = Lazy.of(() -> Ingredient.of(repairItem));
        this.hasColor = material.getColors().hasMaterialColor();
        this.durability = material.getArmor().getShield().getDurability();
        this.enchantment = material.getArmor().getEnchantability();
        this.highlight1 = material.getColors().getHighlightColor(1);
        this.highlight2 = material.getColors().getHighlightColor(2);
        this.shadow1 = material.getColors().getShadowColor(1);
        this.shadow2 = material.getColors().getShadowColor(2);
        this.base = material.getColors().getMaterialColor();
        this.id = material.getId();
    }


    @Override
    public int getMaxDamage(@NotNull ItemStack stack) {
        return durability;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, @NotNull ItemStack repair) {
        return repairItem.get().test(repair);
    }

    @Override
    public int getEnchantmentValue(@NotNull ItemStack stack) {
        return enchantment;
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(
            @NotNull ItemStack stack,
            @NotNull Entity entity,
            @NotNull EquipmentSlot slot,
            ArmorMaterial.@NotNull Layer layer,
            boolean innerModel
    ) {
        if (this.base > -1) return super.getArmorTexture(stack, entity, slot, layer, innerModel);
        return ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/armor/empty.png"); //toString no longer needed here.
    }

    public boolean hasColor() {
        return this.hasColor;
    }

    public int getColorForIndex(int index) {
        return switch (index) {
            case 0 -> highlight2;
            case 1 -> highlight1;
            case 3 -> shadow1;
            case 4 -> shadow2;
            default -> this.base;
        };
    }

    public String getId() {
        return this.id;
    }
}
