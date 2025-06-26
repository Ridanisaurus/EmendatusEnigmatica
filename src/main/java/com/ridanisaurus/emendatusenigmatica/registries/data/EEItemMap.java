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

package com.ridanisaurus.emendatusenigmatica.registries.data;

import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * An "addon" to Java's HashMap, allowing for easier access to values with just material provided.
 * @apiNote Key and Value type is not customizable. Only Strings -> DeferredItem map is supported.
 */
public class EEItemMap<T extends Item> extends HashMap<String, DeferredItem<T>> {

    /**
     * Used to get the DeferredItem of the specified material.
     * @param model MaterialModel of the item.
     * @return DeferredItem associated with the material provided.
     */
    public DeferredItem<T> get(@NotNull MaterialModel model) {
        return this.get(model.getId());
    }

    /**
     * Used to get the item under a specified Material ID.
     * @param key Material ID under which the required item is stored.
     * @return Direct Reference to the item associated with the material provided.
     */
    public T getValue(@NotNull String key) {
        return this.get(key).get();
    }

    /**
     * Used to get the item of the specified material.
     * @param model MaterialModel of the item.
     * @return Direct Reference to the item associated with the material provided.
     */
    public T getValue(@NotNull MaterialModel model) {
        return this.get(model).get();
    }

    /**
     * Used to get the ID of the stored item with use of the material model.
     * @param model MaterialModel of the item.
     * @return ResourceLocation of the item associated with the material provided.
     */
    public ResourceLocation getId(@NotNull MaterialModel model) {
        return this.get(model).getId();
    }

    /**
     * Used to get the ID of the stored item with use of the material model.
     * @param model MaterialModel of the item.
     * @return String representation of the item ID associated with the material provided.
     */
    public String getIdAsString(MaterialModel model) {
        return this.getId(model).toString();
    }
}
