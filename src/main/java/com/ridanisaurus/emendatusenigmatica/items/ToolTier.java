/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
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

import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ToolTier implements Tier {
    private final int level;
    private final int durability;
    private final float efficiency;
    private final float attackDmg;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    public ToolTier(@NotNull MaterialModel material, int durability, TagKey<Item> repairTag) {
        this(durability, material.getTools().getLevel(), material.getTools().getEfficiency(), material.getTools().getAttackDamage(), material.getTools().getEnchantability(), repairTag);
    }

    public ToolTier(int durability, int level, float efficiency, float attackDmg, int enchantability, TagKey<Item> repairTag) {
        this.level = level;
        this.durability = durability;
        this.efficiency = efficiency;
        this.attackDmg = attackDmg;
        this.enchantability = enchantability;
        this.repairIngredient = () -> Ingredient.of(repairTag);
    }

    public ToolTier(int durability, int level, float efficiency, float attackDmg, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.durability = durability;
        this.efficiency = efficiency;
        this.attackDmg = attackDmg;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return this.durability;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDmg;
    }

    @Override
    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return switch (this.level) {
            case 0 -> Tiers.WOOD.getIncorrectBlocksForDrops();
            case 1 -> Tiers.STONE.getIncorrectBlocksForDrops();
            case 2 -> Tiers.IRON.getIncorrectBlocksForDrops();
            case 3 -> Tiers.DIAMOND.getIncorrectBlocksForDrops();
            case 4 -> Tiers.NETHERITE.getIncorrectBlocksForDrops();
            default -> throw new IllegalStateException("Unexpected value for tool level: " + this.level);
        };
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
