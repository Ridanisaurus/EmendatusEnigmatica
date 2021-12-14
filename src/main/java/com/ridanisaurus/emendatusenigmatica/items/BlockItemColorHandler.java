package com.ridanisaurus.emendatusenigmatica.items;

import com.ridanisaurus.emendatusenigmatica.blocks.BasicStorageBlock;
import com.ridanisaurus.emendatusenigmatica.blocks.BasicStorageBlockItem;
import com.ridanisaurus.emendatusenigmatica.blocks.GemOreBlock;
import com.ridanisaurus.emendatusenigmatica.blocks.MetalOreBlock;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class BlockItemColorHandler implements IItemColor {
    @Override
    public int getColor(ItemStack stack, int layer) {
        if (layer == 1) {
            if (stack.getItem() instanceof BlockItem) {
                BlockItem item = (BlockItem) stack.getItem();
                if (item.getBlock() instanceof MetalOreBlock) {
                    return ((MetalOreBlock) item.getBlock()).highlightColor;
                } else if (item.getBlock() instanceof GemOreBlock) {
                    return ((GemOreBlock) item.getBlock()).highlightColor;
                }
            }
        }
        if (stack.getItem() instanceof BasicStorageBlockItem) {
            BlockItem item = (BasicStorageBlockItem) stack.getItem();
            if (item.getBlock() instanceof BasicStorageBlock) {
                return ((BasicStorageBlock) item.getBlock()).highlightColor;
            }
        }
        return 0xFFFFFF;
    }
}
