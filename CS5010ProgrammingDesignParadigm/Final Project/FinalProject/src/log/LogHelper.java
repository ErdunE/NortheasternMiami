package log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogHelper {

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.setUseParentHandlers(false);

        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomFormatter());
        logger.addHandler(handler);

        return logger;
    }

    private static class CustomFormatter extends SimpleFormatter {
        @Override
        public String format(LogRecord record) {
            return String.format("[%s] [%s] [%s] %s%n",
                    record.getLevel(),
                    record.getLoggerName(),
                    record.getInstant(),
                    record.getMessage());
        }
    }

    public static void logInfo(Logger logger, String message) {
        logger.info(message);
    }

    public static void logWarning(Logger logger, String message) {
        logger.warning(message);
    }

    public static void logSevere(Logger logger, String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }
}
