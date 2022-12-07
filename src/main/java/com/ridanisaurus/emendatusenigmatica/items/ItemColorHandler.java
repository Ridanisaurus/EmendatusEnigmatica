package com.ridanisaurus.emendatusenigmatica.items;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemColorHandler implements ItemColor {
    @Override
    public int getColor(ItemStack stack, int layer) {
        Item item = stack.getItem();
        if (layer == 0) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).highlight2;
            }
        }
        if (layer == 1) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).highlight1;
            }
        }
        if (layer == 2) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).base;
            }
        }
        if (layer == 3) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).shadow1;
            }
        }
        if (layer == 4) {
            if (item instanceof BasicItem){
                return ((BasicItem) item).shadow2;
            }
        }

        return 0xFFFFFF;
    }
}