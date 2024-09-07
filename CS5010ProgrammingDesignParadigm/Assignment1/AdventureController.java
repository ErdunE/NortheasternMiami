import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Erdun E
 * Date:Sep 7th, 2024
 * Course: CS5010 Programming Design Paradigm
 * Program: Assignment 1
 *
 * This class controls the model and the view while making sure the program runs appropriately
 *
 */

public class AdventureController {

    AdventureModel model;
    AdventureView view;
    public static Scanner in;

    public AdventureController(AdventureModel model, AdventureView view, InputStream in) {
        this.model = model;
        this.view = view;
        AdventureController.in = new Scanner(in);
    }

    public void createAdventure() {
        boolean quit = false;
        while (!quit) {

            view.welcomeMessage();

            String userName = in.nextLine();
            model.setUserName(userName);
            view.showUserName(userName);
            view.backgroundMessage();

            view.situation_1();
            view.verbMenu();
            model.ifVerbIsValid();
            view.nounMenu();
            model.ifNounIsValid();

            view.situation_2();
            view.verbMenu();
            model.ifVerbIsValid();
            view.nounMenu();
            model.ifNounIsValid();

            view.situation_3();
            view.verbMenu();
            model.ifVerbIsValid();
            view.nounMenu();
            model.ifNounIsValid();

            view.situation_4();
            view.verbMenu();
            model.ifVerbIsValid();
            view.nounMenu();
            model.ifNounIsValid();

            view.endMessage();
            quit = true;
        }
    }
}
