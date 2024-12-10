import javafx.application.Application;
import log.LogHelper;
import ui.EntertainmentGUI;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = LogHelper.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Entertainment Recommendation System is starting...");
        startApplication(args);
    }

    private static void startApplication(String[] args) {
        try {
            Application.launch(EntertainmentGUI.class, args);
            logger.info("Entertainment Recommendation System launched successfully.");
        } catch (Exception e) {
            logger.severe("Entertainment Recommendation System failed to launch: " + e.getMessage());
            e.printStackTrace();
        } finally {
            logger.info("Entertainment Recommendation System has exited.");
        }
    }
}