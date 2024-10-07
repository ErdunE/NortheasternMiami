/**
 * @author Erdun E
 * Date:Sep 7th, 2024
 * Course: CS5010 Programming Design Paradigm
 * Program: Assignment 1
 *
 * main method to run the program
 *
 */
public class Main {

    public static void main(String[] args) {
        // create each class of the MVC
        AdventureModel model = new AdventureModel();
        AdventureView view = new AdventureView();
        AdventureController controller = new AdventureController(model, view, System.in);

        // kick off the Adventure
        controller.createAdventure();
    }
}
