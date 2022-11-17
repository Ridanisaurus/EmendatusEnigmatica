/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.registries;

import com.ridanisaurus.emendatusenigmatica.items.BasicBurnableItem;
import com.ridanisaurus.emendatusenigmatica.items.BasicItem;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class EECreateRegistrar {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    // Create Compat
    public static Map<String, RegistryObject<Item>> crushedOreMap = new HashMap<>();

    public static void registerCrushedOres(MaterialModel material) {
        String itemName = "crushed_" + material.getId() + "_ore";
        if (material.getProperties().isBurnable()) {
            crushedOreMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(
                    material.getProperties().getBurnTime(),
                    material.getColors().getHighlightColor(3),
                    material.getColors().getHighlightColor(1),
                    material.getColors().getMaterialColor(),
                    material.getColors().getShadowColor(1),
                    material.getColors().getShadowColor(2)
            )));
        } else {
            crushedOreMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(
                    material.getColors().getHighlightColor(3),
                    material.getColors().getHighlightColor(1),
                    material.getColors().getMaterialColor(),
                    material.getColors().getShadowColor(1),
                    material.getColors().getShadowColor(2)
            )));
        }
    }

    public static void finalize(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}