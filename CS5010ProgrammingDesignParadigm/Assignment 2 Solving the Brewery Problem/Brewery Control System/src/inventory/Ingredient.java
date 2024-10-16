package inventory;

import java.util.Objects;

/**
 * The Ingredient class represents an ingredient used in beer production
 * within the Brewery Control System. Each ingredient has a name and a
 * quantity, which can be updated as ingredients are added or used in
 * production.
 * <p>
 * Ingredient equality is based solely on the name of the ingredient.
 * This allows different batches of the same ingredient (with different
 * quantities) to be treated as equivalent when they share the same name.
 * </p>
 *
 * @author Erdun E
 * @version 1.3
 * @since 10/15/2024
 */

public class Ingredient {
    private String ingredientName;
    private int quantity;

    /**
     * Constructors for ingredient class.
     *
     * @param ingredientName The name of the ingredient.
     * @param quantity Representing the available number of an ingredient.
     */
    public Ingredient(String ingredientName, int quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    /**
     * Returns the name of the ingredient.
     *
     * @return A string representing the name of the ingredient.
     */
    public String getName() {
        return ingredientName;
    }

    /**
     * Returns the current quantity of the ingredient.
     *
     * @return An integer representing the current quantity of the ingredient in stock.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity of the ingredient. This method is used when ingredients
     * are added to or consumed from the inventory.
     *
     * @param quantity The new quantity of the ingredient to set. This should be a non-negative integer.
     */
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Compares this ingredient with another object for equality. Two ingredients are considered equal
     * if they have the same name, regardless of their quantities.
     *
     * @param object The object to compare this ingredient to.
     * @return {@code true} if the object is an Ingredient with the same name as this ingredient, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        // Check if the objects are identical by reference
        if (this == object){
            return true;
        }

        // Check if the provided object is null or not of the same class
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        // Cast the object to Ingredient class and compare their ingredientName properties
        Ingredient that = (Ingredient) object;
        return Objects.equals(ingredientName, that.ingredientName);
    }

    /**
     * Returns the hash code of this ingredient based on its name.
     *
     * @return The hash code of the ingredient.
     */
    @Override
    public int hashCode() {
        // Generate a hash code using the ingredientName field
        return Objects.hash(ingredientName);
    }
}