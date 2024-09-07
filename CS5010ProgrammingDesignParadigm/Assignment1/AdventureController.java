import java.io.InputStream;
import java.util.Scanner;

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
            /*
            * Write an algorithm in create Adventure()
            * to determine whether the input is a verb or noun or invalid,
            * and return an exception if invalid.
            */
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

            view.endMessage();
            quit = true;
        }
    }
}
