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

package com.ridanisaurus.emendatusenigmatica.items.templates;

import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.renderers.ShieldTextureRenderer;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class BasicShieldItem extends ShieldItem {
    public final TagKey<Item> repairItem;
    public final MaterialModel material;

    public BasicShieldItem(@NotNull MaterialModel material, TagKey<Item> repairItem) {
        super(new Properties().durability(material.getArmor().getShield().getDurability()));
        this.repairItem = repairItem;
        this.material = material;
    }

    public Ingredient getRepairMaterial() {
        return Ingredient.of(repairItem);
    }

    @Override
    public int getMaxDamage(@NotNull ItemStack stack) {
        return material.getArmor().getShield().getDurability();
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, @NotNull ItemStack repair) {
        return getRepairMaterial().test(repair);
    }

    @Override
    public int getEnchantmentValue(@NotNull ItemStack stack) {
        return material.getArmor().getEnchantability();
    }

    public MaterialModel getMaterialModel() {
        return material;
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(
            @NotNull ItemStack stack,
            @NotNull Entity entity,
            @NotNull EquipmentSlot slot,
            ArmorMaterial.@NotNull Layer layer,
            boolean innerModel
    ) {
        if (!material.getColors().hasMaterialColor()) {
            return super.getArmorTexture(stack, entity, slot, layer, innerModel);
        } else {
            return ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/armor/empty.png"); //toString no longer needed here.
        }
    }

    public int getColorForIndex(int index) {
        switch (index) {
            case 0: return material.getColors().getHighlightColor(2);
            case 1: return material.getColors().getHighlightColor(1);
            case 3: return material.getColors().getShadowColor(1);
            case 4: return material.getColors().getShadowColor(2);
            default: material.getColors().getMaterialColor();
        }
        return material.getColors().getMaterialColor();
    }
}
