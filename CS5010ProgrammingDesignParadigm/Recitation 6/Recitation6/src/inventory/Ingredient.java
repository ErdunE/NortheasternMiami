package inventory;

/**
 * WMC
 * 4, getName, getQuantity, setQuantity, constructor
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 0
 * RFC
 * 4
 * 4 internal, getName, getQuantity, setQuantity, constructor
 * LCOM
 * Low, all methods operate on ingredientName and quantity.
 * So they are highly cohesive.
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
