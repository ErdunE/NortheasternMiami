package Operations;
/**
 * This class implements Computable, and will take two
 * integers as parameters. It will return those integers
 * as the division of them.
 *
 * Remember that your division class needs a try/catch block
 * to handle a "divide by zero" exception.
 */
public class Divide implements Computable{
    @Override
    public int compute(int a, int b) throws ArithmeticException {
        return a / b;
    }
}
