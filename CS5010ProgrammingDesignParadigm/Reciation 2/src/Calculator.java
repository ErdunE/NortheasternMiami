/**
 *
 * @author Erdun E
 * @version 9/13/2024
 * @param computation, save the operator input by the user
 * @param a, number 1
 * @param b, number 2
 * @return result of calculated
 *
 * Method of calculate
 *
 */
import Operations.*;

public class Calculator {
    int a;
    int b;
    char computation;

    static Add add = new Add();
    static Subtract subtract = new Subtract();
    static Multiply multiply = new Multiply();
    static Divide divide = new Divide();

    public Calculator(int a, int b, char computation){
        this.a = a;
        this.b = b;
        this.computation = computation;
    }

    // Determine the calculation symbol and return the result after calculation
    public static int run(char computation, int a, int b){
        // set variable, result
        int result = 0;
        // determine the computation
        switch(computation){
            case '+':
                // call add class
                result = add.compute(a,b);
                break;
            case '-':
                // call subtract class
                result = subtract.compute(a,b);
                break;
            case '*':
                // call multiply class
                result = multiply.compute(a,b);
                break;
            case '/':
                // call divide class
                result = divide.compute(a,b);
                break;

            // If user input invalid, return
            default:
                System.out.println("Invalid computation");
        }
        // return result of calculated
        return result;

    }
}
