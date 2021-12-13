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
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class EEBloodMagicRegistrar {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    // Blood Magic Compat
    public static Map<String, RegistryObject<Item>> fragmentMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> gravelMap = new HashMap<>();

    public static void registerFragments(MaterialModel material) {
        String itemName = material.getId() + "_fragment";
        if (material.isBurnable()) {
            fragmentMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime(), material.getColor())));
        } else {
            fragmentMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void registerGravels(MaterialModel material) {
        String itemName = material.getId() + "_gravel";
        if (material.isBurnable()) {
            gravelMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime(), material.getColor())));
        } else {
            gravelMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void finalize(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}