package Operations;
/**
 *
 * @author Erdun E
 * @version 9/13/2024
 *
 * Method of Divide, Call the computable interface and override it
 * Meanwhile throws ArithmeticException once number 2 = 0.
 *
 */
public class Divide implements Computable{
    @Override
    public int compute(int a, int b) throws ArithmeticException {
        return a / b;
    }
}
