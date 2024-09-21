/**
 * @author Erdun E
 * Date:Sep 20th, 2024
 * Course: CS5010 Programming Design Paradigm
 * Program: Assignment 1
 *
 * main method to run the program
 *
 */
public class Main {
    public static void main(String[] args) {
        // Create each of the classes
        AdventureModel model = new AdventureModel();
        AdventureView view = new AdventureView();
        AdventureController controller = new AdventureController(model, view);

        //Run the controller
        controller.run();

    }
}
