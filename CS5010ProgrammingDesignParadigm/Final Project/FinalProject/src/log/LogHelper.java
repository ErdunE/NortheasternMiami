package log;

import java.io.*;
import java.util.logging.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Provides logging support for the application.
 * Manages logging configuration, custom formatting, and log output.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class LogHelper {

    /**
     * The file path where log messages are saved.
     * Default is "./src/log/system_log.txt". The path can be overridden by setting the "logFilePath" system property.
     */
    private static final String LOG_FILE = System.getProperty("logFilePath", "./src/log/system_log.txt");

    static {
        if (!Boolean.getBoolean("disableLogging")) {
            try {
                PrintStream fileOut = new PrintStream(new FileOutputStream(LOG_FILE, true));
                System.setOut(new PrintStream(fileOut, true));
                System.setErr(new PrintStream(fileOut, true));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a logger configured with custom handlers and formatters.
     *
     * @param clazz The class for which the logger is created.
     * @return A configured {@link Logger} instance.
     */
    public static Logger getLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.setUseParentHandlers(false);

        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        if (!Boolean.getBoolean("disableLogging")) {
            try {
                FileHandler fileHandler = new FileHandler(LOG_FILE, true);
                fileHandler.setFormatter(new CustomFormatter());
                logger.addHandler(fileHandler);

                ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setFormatter(new CustomFormatter());
                logger.addHandler(consoleHandler);

                logger.info("Logger initialized for class: " + clazz.getName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return logger;
    }

    /**
     * Custom formatter for log messages.
     * Formats log messages with a timestamp, log level, logger name, and message.
     */
    private static class CustomFormatter extends Formatter {
        private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        @Override
        public String format(LogRecord record) {
            String timestamp = dateFormatter.format(Instant.ofEpochMilli(record.getMillis()));
            return String.format("[%s] [%s] [%s] %s%n",
                    timestamp,
                    record.getLevel(),
                    record.getLoggerName(),
                    record.getMessage());
        }
    }

    /**
     * Logs an info-level message.
     *
     * @param logger  The logger instance.
     * @param message The message to log.
     */
    public static void logInfo(Logger logger, String message) {
        logger.info(message);
    }

    /**
     * Logs a warning-level message.
     *
     * @param logger  The logger instance.
     * @param message The message to log.
     */
    public static void logWarning(Logger logger, String message) {
        logger.warning(message);
    }

    /**
     * Logs a severe-level message with an exception.
     *
     * @param logger    The logger instance.
     * @param message   The message to log.
     * @param throwable The exception to log.
     */
    public static void logSevere(Logger logger, String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }

    /**
     * Clears the log file by deleting its contents.
     */
    public static void clearLogFile() {
        try {
            FileWriter writer = new FileWriter(new File("./src/log/system_log.txt"), false);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
