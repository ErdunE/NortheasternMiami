/**
 * The Driver class serves as the entry point for the Assignment 3 chomp game.
 * It initializes the game by creating an instance of the Controller class
 * and invoking its init() method, which starts the game setup and interface.
 *
 * @author Erdun E
 * @since 2024-11-13
 * @version 1.3
 */
public class Driver {
    /**
     * The main method launches the Chomp game by instantiating the Controller
     * and initializing the game setup.
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.init();
    }
}