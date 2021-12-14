package com.ridanisaurus.emendatusenigmatica.items;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemColorHandler implements IItemColor {
    @Override
    public int getColor(ItemStack stack, int layer) {
        if (layer == 0) {
            Item item = stack.getItem();
            if (item instanceof BasicItem){
                return ((BasicItem) item).highlightColor;
            }
        }
        if (layer == 1) {
            Item item = stack.getItem();
            if (item instanceof BasicItem){
                return ((BasicItem) item).baseColor;
            }
        }
        if (layer == 2) {
            Item item = stack.getItem();
            if (item instanceof BasicItem){
                return ((BasicItem) item).shadeColor;
            }
        }
        return 0xFFFFFF;
    }
}
