/**
 *
 * @author Erdun E
 * @param computation, save the operator input by the user
 * @param a, number 1
 * @param b, number 2
 * @return result of calculated
 *
 * Main driver of the calculator program
 *
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        // Prompt the user to enter +, -, *, / and get the input value
        System.out.println("Enter the operation: (+, -, *, /): ");
        char computation = sc.next().charAt(0);

        // Prompt the user to enter number 1 and get the input value
        System.out.println("Enter the num1 : ");
        int a = sc.nextInt();

        // Prompt the user to enter number 2 and get the input value
        System.out.println("Enter the num2 : ");
        int b = sc.nextInt();

        // Base on the computation calculate the result of a & b, and return to result
        int result = Calculator.run(computation, a, b);

        // print out result
        System.out.println("The result of the operation " + a + " " + computation + " " + b + " = " + result);
        sc.close();
    }
}
