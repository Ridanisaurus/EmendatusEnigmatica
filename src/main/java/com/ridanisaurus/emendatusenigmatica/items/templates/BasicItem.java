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

package com.ridanisaurus.emendatusenigmatica.items.templates;

import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.util.IColorable;
import net.minecraft.world.item.Item;

public class BasicItem extends Item implements IColorable
{
    private final int highlight2;
    private final int highlight1;
    private final int base;
    private final int shadow1;
    private final int shadow2;

    public BasicItem(MaterialModel material) {
        super(new Properties());
        this.highlight2 = material.getColors().getHighlightColor(3);
        this.highlight1 = material.getColors().getHighlightColor(1);
        this.base = material.getColors().getMaterialColor();
        this.shadow1 = material.getColors().getShadowColor(1);
        this.shadow2 = material.getColors().getShadowColor(2);
    }

    @Override
    public int getHighlight2() {
        return highlight2;
    }

    @Override
    public int getHighlight1() {
        return highlight1;
    }

    @Override
    public int getBase() {
        return base;
    }

    @Override
    public int getShadow1() {
        return shadow1;
    }

    @Override
    public int getShadow2() {
        return shadow2;
    }
}
