/**
 * 
 * @author Lindsay Jamieson
 * 
 * main method to run the program
 *
 */
public class Main {

	public static void main(String[] args) {
		// Create each of the classes
		 Model model = new Model();
	     View view = new View();
	     Control controller = new Control(model, view, System.in);
	     
	     //Run the controller
	     controller.run();
	}

}
