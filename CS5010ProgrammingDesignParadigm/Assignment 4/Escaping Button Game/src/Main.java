/**
 * The Main class serves as the entry point for the Escaping Button Game.
 * It initializes the GUI and the game controller, then starts the game.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-28
 */
public class Main {
    public static void main(String[] args) {
        // Create the GUI
        GameView view = new GameView();
        // Initial controller
        GameController controller = new GameController(view);
        // Start the game
        controller.startGame();
    }
}