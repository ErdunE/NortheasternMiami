package inventory;

/**
 *
 * @author Erdun E
 * @version 09/26/2024
 *
 * Represents an ingredient used in beer production.
 *
 */

public class Ingredient {
    private String ingredientName;
    private int quantity;

    public Ingredient(String ingredientName, int quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public String getName() {
        return ingredientName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
