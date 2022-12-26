package com.ridanisaurus.emendatusenigmatica.items;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ItemColorHandler implements ItemColor {
    @Override
    public int getColor(ItemStack stack, int layer) {
        Item item = stack.getItem();
        if (layer == 0) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).highlight2;
            }
            if (item instanceof BasicSwordItem){
                return ((BasicSwordItem) item).highlight2;
            }
            if (item instanceof BasicPickaxeItem){
                return ((BasicPickaxeItem) item).highlight2;
            }
            if (item instanceof BasicAxeItem){
                return ((BasicAxeItem) item).highlight2;
            }
            if (item instanceof BasicShovelItem){
                return ((BasicShovelItem) item).highlight2;
            }
            if (item instanceof BasicHoeItem){
                return ((BasicHoeItem) item).highlight2;
            }
            if (item instanceof BasicPaxelItem){
                return ((BasicPaxelItem) item).highlight2;
            }
            if (item instanceof BasicArmorItem){
                return ((BasicArmorItem) item).highlight2;
            }
        }
        if (layer == 1) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).highlight1;
            }
            if (item instanceof BasicSwordItem){
                return ((BasicSwordItem) item).highlight1;
            }
            if (item instanceof BasicPickaxeItem){
                return ((BasicPickaxeItem) item).highlight1;
            }
            if (item instanceof BasicAxeItem){
                return ((BasicAxeItem) item).highlight1;
            }
            if (item instanceof BasicShovelItem){
                return ((BasicShovelItem) item).highlight1;
            }
            if (item instanceof BasicHoeItem){
                return ((BasicHoeItem) item).highlight1;
            }
            if (item instanceof BasicPaxelItem){
                return ((BasicPaxelItem) item).highlight1;
            }
            if (item instanceof BasicArmorItem){
                return ((BasicArmorItem) item).highlight1;
            }
        }
        if (layer == 2) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).base;
            }
            if (item instanceof BasicSwordItem){
                return ((BasicSwordItem) item).base;
            }
            if (item instanceof BasicPickaxeItem){
                return ((BasicPickaxeItem) item).base;
            }
            if (item instanceof BasicAxeItem){
                return ((BasicAxeItem) item).base;
            }
            if (item instanceof BasicShovelItem){
                return ((BasicShovelItem) item).base;
            }
            if (item instanceof BasicHoeItem){
                return ((BasicHoeItem) item).base;
            }
            if (item instanceof BasicPaxelItem){
                return ((BasicPaxelItem) item).base;
            }
            if (item instanceof BasicArmorItem){
                return ((BasicArmorItem) item).base;
            }
        }
        if (layer == 3) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).shadow1;
            }
            if (item instanceof BasicSwordItem){
                return ((BasicSwordItem) item).shadow1;
            }
            if (item instanceof BasicPickaxeItem){
                return ((BasicPickaxeItem) item).shadow1;
            }
            if (item instanceof BasicAxeItem){
                return ((BasicAxeItem) item).shadow1;
            }
            if (item instanceof BasicShovelItem){
                return ((BasicShovelItem) item).shadow1;
            }
            if (item instanceof BasicHoeItem){
                return ((BasicHoeItem) item).shadow1;
            }
            if (item instanceof BasicPaxelItem){
                return ((BasicPaxelItem) item).shadow1;
            }
            if (item instanceof BasicArmorItem){
                return ((BasicArmorItem) item).shadow1;
            }
        }
        if (layer == 4) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).shadow2;
            }
            if (item instanceof BasicSwordItem){
                return -1;
            }
            if (item instanceof BasicPickaxeItem){
                return -1;
            }
            if (item instanceof BasicAxeItem){
                return -1;
            }
            if (item instanceof BasicShovelItem){
                return -1;
            }
            if (item instanceof BasicHoeItem){
                return -1;
            }
            if (item instanceof BasicPaxelItem){
                return -1;
            }
            if (item instanceof BasicArmorItem){
                return ((BasicArmorItem) item).shadow2;
            }
        }

        return 0xFFFFFF;
    }
}