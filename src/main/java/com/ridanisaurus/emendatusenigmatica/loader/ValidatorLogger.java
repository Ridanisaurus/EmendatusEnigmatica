package com.ridanisaurus.emendatusenigmatica.loader;

import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import org.slf4j.Logger;

import java.util.Objects;

/**
 * Simple class that wraps around the Logger returned by LoggerFactory. Only used to make errors and warnings more readable.
 */
public class ValidatorLogger {
    private static final String spacer = "------------------------------------------------------------------------------------------------------------------------";
    private final Logger logger;
    public final boolean shouldLog;
    private boolean spacerPrinted = false;

    /**
     * Creates instance of Validator Logger Wrapper.
     * @param logger Logger to use.
     */
    public ValidatorLogger(Logger logger) {
        Objects.requireNonNull(logger, "Can't create logger wrapper from Null logger!");
        this.logger = logger;
        shouldLog = EEConfig.common.logConfigErrors.get();
    }

    /**
     * Log the message at level INFO.
     * @param msg Message to log.
     */
    public void info(String msg) {
        log(msg, 0);
    }

    /**
     * Log the message at level WARN.
     * @param msg Message to log.
     */
    public void warn(String msg) {
        if (shouldLog) log(msg, 1);
    }

    /**
     * Log the message at level ERROR.
     * @param msg Message to log.
     */
    public void error(String msg) {
        if (shouldLog) log(msg, 2);
    }

    /**
     * Log the message at level DEBUG.
     * @param msg Message to log.
     */
    public void debug(String msg) {
        log(msg, -1);
    }

    /**
     * Used to log the message at specified level.
     * Levels 1 and 2 aren't printed when "logConfigErrors" is set to false.
     *<br>Levels:<br>
     * <ul>
     *     <li>-1 -> debug</li>
     *     <li>0 -> info</li>
     *     <li>1 -> warn</li>
     *     <li>2 -> error</li>
     * </ul>
     * @param msg Message to log.
     * @param level Integer level to print spacer at.
     */
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

    /**
     * Used to print the spacer at specified level.
     * Levels 1 and 2 aren't printed when "logConfigErrors" is set to false.
     *<br>Levels:<br>
     * <ul>
     *     <li>-1 -> debug</li>
     *     <li>0 -> info</li>
     *     <li>1 -> warn</li>
     *     <li>2 -> error</li>
     * </ul>
     * @param level Integer level to print spacer at.
     */
    public void printSpacer(int level) {
        switch (level) {
            case 1 -> {if (shouldLog) logger.warn(" " + spacer);}
            case 2 -> {if (shouldLog) logger.error(spacer);}
            case -1 -> logger.debug(spacer);
            default -> logger.info(" " + spacer);
        }
    }
}
