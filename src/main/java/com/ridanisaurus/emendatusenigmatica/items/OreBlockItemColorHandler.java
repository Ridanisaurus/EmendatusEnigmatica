package com.ridanisaurus.emendatusenigmatica.items;

import com.ridanisaurus.emendatusenigmatica.blocks.BasicOreBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OreBlockItemColorHandler implements IItemColor {
    @Override
    public int getColor(ItemStack stack, int layer) {
        if (layer == 1){
            if (stack.getItem() instanceof BlockItem){
                BlockItem item = (BlockItem) stack.getItem();
                if (item.getBlock() instanceof BasicOreBlock){
                    return ((BasicOreBlock) item.getBlock()).color;
                }
            }
        }
        return 0xFFFFFF;
    }
}
