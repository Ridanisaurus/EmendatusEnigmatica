package com.ridanisaurus.emendatusenigmatica.loader;

import org.slf4j.Logger;

import java.util.Objects;

/**
 * Simple class that wraps around the Logger returned by LoggerFactory. Only used to make errors and warnings more readable.
 */
public class ValidatorLogger {
    private static final String spacer = "------------------------------------------------------------------------------------------------------------------------";
    private final Logger logger;
    private boolean spacerPrinted = false;

    public ValidatorLogger(Logger logger) {
        Objects.requireNonNull(logger, "Can't create logger wrapper from Null logger!");
        this.logger = logger;
    }

    public void info(String msg) {
        log(msg, 0);
    }

    public void warn(String msg) {
        log(msg, 1);
    }

    public void error(String msg) {
        log(msg, 2);
    }

    public void debug(String msg) {
        log(msg, -1);
    }

    public void log(String msg, int level) {
        if (!spacerPrinted) {
            printSpacer(level);
            spacerPrinted = true;
        }
        switch (level) {
            case 1 -> logger.warn(" " + msg);
            case 2 -> logger.error(msg);
            case -1 -> logger.debug(msg);
            default -> logger.info(" " + msg);
        }
    }
    public void restartSpacer() {
        this.spacerPrinted = false;
    }

    public void printSpacer(int level) {
        switch (level) {
            case 1 -> logger.warn(" " + spacer);
            case 2 -> logger.error(spacer);
            case -1 -> logger.debug(spacer);
            default -> logger.info(" " + spacer);
        }
    }
}
