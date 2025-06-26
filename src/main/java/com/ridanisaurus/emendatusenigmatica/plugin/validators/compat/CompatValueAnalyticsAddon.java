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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.compat;

import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import com.ridanisaurus.emendatusenigmatica.util.analytics.AnalyticsAddon;
import com.ridanisaurus.emendatusenigmatica.util.analytics.AnalyticsWriteContext;

public class CompatValueAnalyticsAddon implements AnalyticsAddon {
    private static boolean shouldRun = false;

    public static void shouldRun() {
        if (!shouldRun) {
            shouldRun = true;
            Analytics.registerAddon(new CompatValueAnalyticsAddon());
        }
    }

    /**
     * Method executed by the {@link Analytics} with write context provided.
     *
     * @param cx Write Context for the file.
     */
    @Override
    public void accept(AnalyticsWriteContext cx) {
        cx.writeLine("Array at <code>root.recipes[x].values[x].input</code> in <code>Compat</code> files is only required when at least one criteria is met from the table below. ");
        StringBuilder table = new StringBuilder();
        table.append("<table><tr><th>Mod</th><th>Machine</th><th>Type</th></tr>");
        CompatModData.VALUES.cellSet().forEach(cell -> table
            .append("<tr><td>")
            .append(cell.getRowKey())
            .append("</td><td>")
            .append(cell.getColumnKey())
            .append("</td><td>")
            .append(cell.getValue())
            .append("</td></tr>"));
        table.append("</table>\n");
        cx.write(table.toString());
//        cx.writeLine("""
//        Mod, Machine and Type specified in the table are the values of fields at:
//        - <code>root.recipes[x].values[x].type</code> > Type
//        - <code>root.recipes[x].machine</code> > Machine
//        - <code>root.recipes[x].mod</code> > Mod
//        """);
        cx.writeComment("""
        Mod, Machine and Type specified in the table are the values of fields at:
        - <code>root.recipes[x].values[x].type</code> > Type
        - <code>root.recipes[x].machine</code> > Machine
        - <code>root.recipes[x].mod</code> > Mod
        """);
    }
}
