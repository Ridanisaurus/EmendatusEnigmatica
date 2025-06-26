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

package com.ridanisaurus.emendatusenigmatica.util.analytics;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class AnalyticsWriteContext {
    /**
     * Path to the file where all messages will be written to.
     */
    private final Path summaryFile;

    protected AnalyticsWriteContext(Path file) {
        this.summaryFile = file;
    }

    /**
     *
     * @param msg Header to print
     * @param lvl Header Level, in range [1,6].
     */
    public void writeHeader(String msg, int lvl) {
        if (lvl < 1 || lvl > 6) throw new IllegalArgumentException("Lvl out of range. Provided: %d, expected: [1-6]".formatted(lvl));
        write("%s %s".formatted("#".repeat(lvl), msg));
    }

    public void writeComment(@NotNull String msg) {
        write(msg.lines().map(it -> "> " + it + "\n").collect(Collectors.joining()));
    }

    public void writeSpacer() {
        write("<hr>\n");
    }

    public void writeLine(String msg) {
        write(msg + "<br>");
    }

    public void write(String msg) {
        try {
            Files.writeString(summaryFile, msg + "\n", StandardOpenOption.APPEND);
        } catch (Exception e) {
            EmendatusEnigmatica.logger.error("Exception caught while logging a message!", e);
        }
    }
}
