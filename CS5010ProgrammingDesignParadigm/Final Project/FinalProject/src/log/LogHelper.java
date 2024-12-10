package log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LogHelper {

    private static final String LOG_FILE = "./src/log/system_log.txt";

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.setUseParentHandlers(false);

        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        try {
            PrintStream fileOut = new PrintStream(new FileOutputStream(LOG_FILE, false));

            StreamHandler streamHandler = new StreamHandler(fileOut, new CustomFormatter());
            logger.addHandler(streamHandler);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new CustomFormatter());
            logger.addHandler(consoleHandler);

            logger.info("Logger initialized for class: " + clazz.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return logger;
    }


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
