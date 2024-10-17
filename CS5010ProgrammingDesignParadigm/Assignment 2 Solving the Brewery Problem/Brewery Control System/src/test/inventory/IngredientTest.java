package test.inventory;

import inventory.Ingredient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void testConstructorWithValidQuantity() {
        Ingredient ingredient = new Ingredient("Malt", 10);
        assertEquals("Malt", ingredient.getName());
        assertEquals(10, ingredient.getQuantity());
    }

    @Test
    void testConstructorWithNegativeQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Ingredient("Malt", -5));
        assertEquals("Initial quantity cannot be negative.", exception.getMessage());
    }

    @Test
    void testUpdateQuantityValidValue() {
        Ingredient ingredient = new Ingredient("Hops", 10);
        ingredient.updateQuantity(20);
        assertEquals(20, ingredient.getQuantity(), "Quantity should be updated to 20.");
    }

    @Test
    void testUpdateQuantityNegativeValue() {
        Ingredient ingredient = new Ingredient("Hops", 10);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ingredient.updateQuantity(-10));
        assertEquals("Quantity cannot be negative.", exception.getMessage());
    }

    @Test
    void testAdjustQuantityReduceWithSufficientStock() {
        Ingredient ingredient = new Ingredient("Malt", 10);
        boolean result = ingredient.adjustQuantity(-5);
        assertTrue(result, "Adjusting quantity should succeed.");
        assertEquals(5, ingredient.getQuantity(), "Quantity should be reduced to 5.");
    }

    @Test
    void testAdjustQuantityReduceWithInsufficientStock() {
        Ingredient ingredient = new Ingredient("Malt", 10);
        boolean result = ingredient.adjustQuantity(-15);
        assertFalse(result, "Adjusting quantity should fail due to insufficient stock.");
        assertEquals(10, ingredient.getQuantity(), "Quantity should remain unchanged.");
    }

    @Test
    void testAdjustQuantityIncreaseStock() {
        Ingredient ingredient = new Ingredient("Hops", 5);
        boolean result = ingredient.adjustQuantity(10);
        assertTrue(result, "Increasing quantity should succeed.");
        assertEquals(15, ingredient.getQuantity(), "Quantity should be increased to 15.");
    }

    @Test
    void testEqualsWithSameName() {
        Ingredient ingredient1 = new Ingredient("Malt", 10);
        Ingredient ingredient2 = new Ingredient("Malt", 20);
        assertEquals(ingredient1, ingredient2, "Ingredients with the same name should be equal.");
    }

    @Test
    void testEqualsWithDifferentNames() {
        Ingredient ingredient1 = new Ingredient("Malt", 10);
        Ingredient ingredient2 = new Ingredient("Hops", 10);
        assertNotEquals(ingredient1, ingredient2, "Ingredients with different names should not be equal.");
    }

    @Test
    void testHashCodeWithSameName() {
        Ingredient ingredient1 = new Ingredient("Malt", 10);
        Ingredient ingredient2 = new Ingredient("Malt", 20);
        assertEquals(ingredient1.hashCode(), ingredient2.hashCode(),
                "Hash codes should be equal for ingredients with the same name.");
    }
}
