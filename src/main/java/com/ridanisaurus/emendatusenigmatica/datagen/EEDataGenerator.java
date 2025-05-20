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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.google.common.base.Stopwatch;
import com.mojang.logging.LogUtils;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import net.minecraft.Util;
import net.minecraft.WorldVersion;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.neoforged.fml.ModLoader;
import net.neoforged.fml.loading.ImmediateWindowHandler;
import net.neoforged.fml.loading.progress.StartupNotificationManager;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class EEDataGenerator extends DataGenerator {
    private static final Logger logger = LogUtils.getLogger();
    private boolean executed = false;
    private boolean crashed = false;

    public EEDataGenerator(Path rootOutputFolder, WorldVersion version, boolean alwaysGenerate) {
        super(rootOutputFolder, version, alwaysGenerate);
    }

    @Override
    public void run() {
        //TODO: Add own caching logic. Currently entire DataGen has to be executed to even know what to cache.
        if (executed) return;
        executed = true;

        ExecutorService dataGenExecutor = Executors.newFixedThreadPool(8, r -> {
            final Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setDaemon(true);
            thread.setName("EmendatusEnigmatica-Data-Generation | " + thread.threadId());
            return thread;
        });

        try (dataGenExecutor) {
            // Run-Logic reimplemented to add Custom Progress bar and own Analytics.
            HashCache cache = new HashCache(this.rootOutputFolder, this.allProviderIds, this.version);
            Stopwatch sMain = Stopwatch.createStarted();
            var bar = StartupNotificationManager.addProgressBar("Emendatus Enigmatica: Data Generation", this.providersToRun.size());

            logger.info("Executing Emendatus Enigmatica data generation ({} providers to execute)...", this.providersToRun.size());
            List<Future<?>> futures = new ArrayList<>();
            this.providersToRun.forEach((name, provider) -> futures.add(dataGenExecutor.submit(() -> {
                Stopwatch sPerTask = Stopwatch.createStarted();
                logger.debug("Starting provider: {}", name);
                try {
                    cache.applyUpdate(cache.generateUpdate(name, provider::run).join());
                } catch (Exception e) {
                    logger.error("Caught exception while executing provider \"{}\" after {}ms!", name, sPerTask.elapsed(TimeUnit.MILLISECONDS), e);
                    throw e;
                } finally {
                    bar.increment();
                }
                sPerTask.stop();
                logger.debug("{} finished after {} ms", name, sPerTask.elapsed(TimeUnit.MILLISECONDS));
            })));

            // Keep EarlyFMLWindow alive, so progress bar renders properly and the window doesn't freeze.
            dataGenExecutor.shutdown();
            do {
                ImmediateWindowHandler.renderTick();
            } while (!dataGenExecutor.awaitTermination(50, TimeUnit.MILLISECONDS));

            for (Future<?> future : futures) {
                switch (future.state()) {
                    case RUNNING -> throw new IllegalStateException("Data Provider still running, after executor terminated!");
                    case CANCELLED -> throw new IllegalStateException("Data Provider was canceled!");
                    case FAILED -> throw future.exceptionNow();
                    default -> {}
                }
            }

            bar.complete();

            CompletableFuture<Void> saveIO = CompletableFuture.runAsync(() -> {
                try {
                    cache.purgeStaleAndWrite();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, Util.nonCriticalIoPool());
            ModLoader.waitForTask("Emendatus Enigmatica: Saving generated data", ImmediateWindowHandler::renderTick, saveIO);

            String msg = "EE Data Generation finished after %s ms.".formatted(sMain.elapsed(TimeUnit.MILLISECONDS));
            StartupNotificationManager.addModMessage(msg);
            logger.info(msg);
            Analytics.addPerformanceAnalytic("Data Generation", sMain);
        } catch (Throwable e) {
            crashed = true;
            if (ModLoader.hasErrors()) {
                // If somehow there are errors, but minecraft will load, EE will crash on later stage.
                logger.error("Exception caught while running EE Data Generation, however different mod loading errors are present!");
                logger.error("This exception is most likely caused by another mod causing a crash earlier and is going to be suppressed.", e);
                return;
            }
            throw new RuntimeException("Caught exception while running Emendatus Enigmatica Data Generation! Check the latest log for more details.", e);
        }
    }

    public boolean hasExecuted() {
        return executed && !crashed;
    }
}
