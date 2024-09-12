//This import line uses an asterisk to let Calculator know
//that it needs everything in the Operations folder,
//and should pass priority to all its classes.

import Operations.*;


public class Calculator {
    int a;
    int b;
    char computation;
    //I have given you the three attributes that you will need
    //in the Calculator's construction.
    //But remember that you need to call your operations.
    //It might be good to add them as attributes of this class as well
    //and generate them as objects inside Calculator
    //so that they exist before the program is running.
    //The operation's methods can then be used by Calculator
    //depending on what computation you want to do.

    static Add add = new Add();
    static Subtract subtract = new Subtract();
    static Multiply multiply = new Multiply();
    static Divide divide = new Divide();


    public Calculator(int a, int b, char computation){
        this.a = a;
        this.b = b;
        this.computation = computation;
    }


    public static int run(char computation, int a, int b){
        int result = 0;
        switch(computation){
            case '+':
                result = add.compute(a,b);
                break;
            case '-':
                result = subtract.compute(a,b);
                break;
            case '*':
                result = multiply.compute(a,b);
                break;
            case '/':
                result = divide.compute(a,b);
                break;

            default:
                System.out.println("Invalid computation");
        }
        return result;

    }
}
