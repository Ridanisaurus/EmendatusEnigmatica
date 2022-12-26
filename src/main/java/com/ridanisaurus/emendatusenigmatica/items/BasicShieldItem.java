/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
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

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tier;

public class BasicShieldItem extends ShieldItem {

  public final int highlight2;
  public final int highlight1;
  public final int base;
  public final int shadow1;

  public BasicShieldItem(MaterialModel material) {
    super(new Properties().tab(EmendatusEnigmatica.TAB).durability(durability));
    this.highlight2 = highlight2;
    this.highlight1 = highlight1;
    this.base = base;
    this.shadow1 = shadow1;
  }

  
}