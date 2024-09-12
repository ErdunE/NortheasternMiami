package Operations;

/**
 * This class implements Computable, and will take two
 * integers as parameters. It will return those integers
 * as the sum of them.
 */
public class Add implements Computable{
    @Override
    public int compute(int a, int b) {
        return a + b;
    }
}
