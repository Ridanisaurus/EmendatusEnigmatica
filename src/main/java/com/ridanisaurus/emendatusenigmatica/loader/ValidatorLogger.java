package com.ridanisaurus.emendatusenigmatica.loader;

import com.mojang.datafixers.util.Pair;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import org.slf4j.Logger;

import java.util.Objects;

/**
 * Simple class that wraps around the Logger returned by LoggerFactory. Only used to make errors and warnings more readable.
 */
public class ValidatorLogger {
    private static final String spacer = "------------------------------------------------------------------------------------------------------------------------";
    private final Logger logger;
    private final boolean shouldLog;
    private boolean spacerPrinted = false;
    private int warns = 0;
    private int errors = 0;

    /**
     * Creates instance of Validator Logger Wrapper.
     * @param logger Logger to use.
     */
    public ValidatorLogger(Logger logger) {
        Objects.requireNonNull(logger, "Can't create logger wrapper from Null logger!");
        this.logger = logger;
        //TODO:
        // Remove checks "if log" in places where methods below are used,
        // this is now handled here by the logger wrapper.
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
        warns++;
        if (shouldLog) log(msg, 1);
    }

    /**
     * Log the message at level ERROR.
     * @param msg Message to log.
     */
    public void error(String msg) {
        errors++;
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
     * Used to get logger statistics and reset them to 0.
     * @return Pair with left -> warns / right -> errors
     */
    public Pair<Integer, Integer> getStatistics() {
        var pair = new Pair<>(warns,errors);
        warns = 0;
        errors = 0;
        return pair;
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
