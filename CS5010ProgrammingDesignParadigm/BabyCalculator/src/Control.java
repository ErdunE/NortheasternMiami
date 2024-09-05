import java.io.InputStream;
import java.util.Scanner;

/**
 * 
 * @author Lindsay Jamieson
 * @version 9/4/2024
 * 
 * This class controls the model and the view while making sure the program runs appropriately
 *
 */
public class Control {
	
	Model model;
	View view;
	public Scanner in;

	public Control(Model m, View v, InputStream in) {
		this.model = m;
		this.view = v;
		this.in = new Scanner(in);
	}
	
	/**
	 * This method runs the program as expected so that the user gets the intended result
	 */
	public void run(){
        boolean quit = false;
        while (!quit){
            view.welcomeMessage();
            int numOne = in.nextInt();
            model.setNum1(numOne);
            int numTwo = in.nextInt();
            model.setNum2(numTwo);
            model.add();
            view.displayString(model.intToString());
            quit = true;
        }

    }

}
