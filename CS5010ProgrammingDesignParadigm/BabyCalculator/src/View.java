/**
 * 
 * @author Lindsay Jamieson
 * @version 9/4/2024
 * 
 * The view for our adder project
 *
 */
public class View {

	public View() {
	}
	
	/**
	 * 
	 * This method turns the data from the model into something that can be shown to the user
	 * @param modelData - the data collected from the model class
	 */
	public void displayString(String[] modelData) {
		System.out.println("The sum of " +
                modelData[0] +
                " and " +
                modelData[1] +
                " is " +
                modelData[2]);
	}
	
	/**
	 * This method welcomes the user and prompts for the initial input
	 */
	public void welcomeMessage() {
		System.out.println("Welcome to the adding machine!" + "\n" + "Enter two numbers to add together.");
	}

}
