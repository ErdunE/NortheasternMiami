import jdk.dynalink.Operation;

import java.util.Scanner;

public class Main {
    /*
    This is the main method where you will run the program.
    Also, note how this is not a Javadoc-style comment!
    It only begins with one asterisk, making it a general
    comment that will not be pinned to the documentation.
     */
    public static void main(String[] args){
        //Inside this method, set up your calculator class.
        //When it runs, it should be able to produce
        //a computation based on the values passed through
        //as parameters.
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the operation: (+, -, *, /): ");
        char computation = sc.next().charAt(0);
        System.out.println("Enter the num1 : ");
        int a = sc.nextInt();
        System.out.println("Enter the num2 : ");
        int b = sc.nextInt();
        int result = Calculator.run(computation, a, b);
        System.out.println("The result of the operation is : " + result);
        sc.close();
    }
}
