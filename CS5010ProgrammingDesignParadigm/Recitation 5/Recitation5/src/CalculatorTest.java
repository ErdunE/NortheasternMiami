import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    // Test call computation with operations
    @Test
    public void testCalculatorAdd() {
        assertEquals(10, Calculator.run('+', 9, 1), "9 + 1 added should equal to 10");
    }

    @Test
    public void testCalculatorubtract() {
        assertEquals(8,Calculator.run('-', 9, 1), "9 - 1 subtracted should equal to 8");
    }

    @Test
    public void testCalculatorMultiply() {
        assertEquals(9, Calculator.run('*', 9, 1), "9 * 1 multiplied should equal to 9");
    }

    @Test
    public void testCalculatorDivide() {
        assertEquals(9, Calculator.run('/', 9, 1), "9 / 1 divided should equal to 9");
    }

    // Test special cases
    @Test
    public void testCalculatorDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {Calculator.run('/', 9, 0);}, "Division by zero should throw ArithmeticException");
    }

    @Test
    public void testCalculatorInvalidOperation() {
        assertEquals(0, Calculator.run('%',1,9),"Invalid operation should return 0 or default return");
    }
}
