/**
 * The Driver class serves as the entry point for the Assignment 3 chomp game .
 * It initializes the game by creating an instance of the Controller class
 * and invoking its init() method, which starts the game setup and interface.
 *
 * <p><b>Expected Design:</b> According to the provided class diagram, the Driver class
 * is expected to function as the main entry point for the application, initializing
 * the Controller to begin the game. The class diagram specifies that Driver should
 * instantiate the Controller and trigger the game initialization process.</p>
 *
 * <p><b>Deviations and Extensions:</b> This class strictly adheres to the design provided
 * in the class diagram with no deviations. The Driver class has no additional attributes
 * or methods beyond what is outlined in the class diagram, as its sole responsibility is
 * to initialize and start the game through the Controller.</p>
 *
 * <p><b>Reasoning for Adherence:</b> Since the Driver class is solely responsible for
 * initializing the game, no further extensions or modifications were deemed necessary.
 * The provided design aligns perfectly with the intended purpose of this class.</p>
 *
 * @author Erdun E
 * @since 2024-11-12
 * @version 1.1
 */
public class Driver {
    /**
     * The main method launches the Chomp game by instantiating the Controller
     * and initializing the game setup. This method serves as the starting point
     * of the application.
     * This method adheres exactly to the provided design in the class diagram.
     * No additional functionality or deviations are present, as the method's
     * purpose is solely to launch the application.
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.init();
    }
}