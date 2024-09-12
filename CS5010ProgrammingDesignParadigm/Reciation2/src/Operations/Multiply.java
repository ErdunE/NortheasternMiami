package Operations;
/**
 * This class implements Computable, and will take two
 * integers as parameters. It will return those integers
 * as the multiplication of them.
 */
public class Multiply implements Computable{
    @Override
    public int compute(int a, int b) {
        return a * b;
    }
}
