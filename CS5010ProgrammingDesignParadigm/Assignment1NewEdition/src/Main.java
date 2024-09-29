/**
 * @author Erdun E
 * @version 1.1
 * @since 2024-09-29
 * Course: CS5010 Programming Design Paradigm
 * Program: Assignment 1
 *
 * Main class contains the entry point for running the adventure game
 * The game is structured using the MVC pattern,
 * where the AdventureModel stores the game state,
 * AdventureView handles player interaction,
 * and AdventureController manages the game logic.
 *
 */
public class Main {
    /**
     * The main method initializes the game by creating the model, view, and controller
     * and then starts the adventure game by calling the controller's run method.
     */
    public static void main(String[] args) {
        AdventureModel model = new AdventureModel();
        AdventureView view = new AdventureView();
        AdventureController controller = new AdventureController(model, view);

        //Run the controller
        controller.run();

    }
}
