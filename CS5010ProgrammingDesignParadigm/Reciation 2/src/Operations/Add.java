package Operations;
/**
 *
 * @author Erdun E
 * @version 9/13/2024
 *
 * Method of Add, Call the computable interface and override it
 *
 */
public class Add implements Computable{
    @Override
    public int compute(int a, int b) {
        return a + b;
    }
}
