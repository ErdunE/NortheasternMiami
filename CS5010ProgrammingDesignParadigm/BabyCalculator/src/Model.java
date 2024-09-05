/** 
 * 
 * @author Lindsay Jamieson
 * @version 9/4/2024
 * 
 * Model for a baby calculator
 *
 */
public class Model {
	private int num1;
	private int num2;
	private int sum;

	public Model() {
		num1 = 0;
		num2 = 0;
		sum = num1+num2;
	}
	
	//accessors and mutators
	public int getNum1() {
		return this.num1;
	}


	public void setNum1(int num1) {
		this.num1 = num1;
	}


	public int getNum2() {
		return this.num2;
	}


	public void setNum2(int num2) {
		this.num2 = num2;
	}

	/**
	 * 
	 * This method adds together two numbers that have been previously entered
	 * by the user
	 * @return the sum of the two numbers entered by the user
	 */
	public int add() {
		return this.sum = this.num1 + this.num2;
	}
	
	/**
	 * 
	 * This method turns the integers stored into the class into a series of numbers that
	 * can be used elsewhere
	 * @return The String versions of the numbers in the class
	 */
	public String[] intToString() {
		String[] output = new String[3];
		output[0] = Integer.toString(num1);
		output[1] = Integer.toString(num2);
		output[2] = Integer.toString(sum);
		return output;
	}

}
