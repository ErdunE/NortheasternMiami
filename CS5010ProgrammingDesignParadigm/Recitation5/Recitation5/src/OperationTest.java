import Operations.Add;
import Operations.Divide;
import Operations.Multiply;
import Operations.Subtract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationTest {

    private Add add;
    private Subtract subtract;
    private Multiply multiply;
    private Divide divide;

    @BeforeEach
    public void Setup(){
        add = new Add();
        subtract = new Subtract();
        multiply = new Multiply();
        divide = new Divide();
    }

    // Test add operation
    @Test
    public void testAddPositiveNumber(){
        assertEquals(10, add.compute(1, 9), "1 + 9 added should equal to 10");
    }

    @Test
    public void testAddZeroNumber(){
        assertEquals(1, add.compute(1, 0), "1 + 0 added should equal to 1");
    }

    @Test
    public void testAddNegativeNumber(){
        assertEquals(-8, add.compute(1, -9), "1 + -9 added should equal to -8");
    }

    // Test subtract operation
    @Test
    public void testSubtractPositiveNumber(){
        assertEquals(8, subtract.compute(9, 1), "9 - 1 subtracted should equal to 8");
    }

    @Test
    public void testSubtractZeroNumber(){
        assertEquals(9, subtract.compute(9, 0), "9 - 0 subtracted should equal to 9");
    }

    @Test
    public void testSubtractNegativeNumber(){
        assertEquals(10,subtract.compute(9, -1), "9 - -1 subtracted should equal to 10");
    }

    // Test multiply operation
    @Test
    public void testMultiplyPositiveNumber(){
        assertEquals(9,multiply.compute(9, 1), "9 * 1 multiplied should equal to 9");
    }

    @Test
    public void testMultiplyZeroNumber(){
        assertEquals(0, multiply.compute(9, 0), "9 * 0 multiplied should equal to 0");
    }

    @Test
    public void testMultiplyNegativeNumber(){
        assertEquals(-9,multiply.compute(9, -1), "9 * -1 multiplied should equal to -9");
    }

    @Test
    public void testDividePositiveNumber(){
        assertEquals(9, divide.compute(9, 1), "9 / 1 divided should equal to 9");
    }

    @Test
    public void testDivideZeroNumber(){
        assertThrows(ArithmeticException.class, () -> divide.compute(9, 0), "Diving by 0 should throw ArithmeticException");
    }

    @Test
    public void testDivideNegativeNumber(){
        assertEquals(-9,divide.compute(9, -1), "9 / 0 divided should equal to -9");
    }
}
