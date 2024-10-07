package inventory;

import recipe.Recipe;

/**
 *
 * @author Erdun E
 * @version 09/26/2024
 *
 * Manages and handles inventory operations, such as adding
 * ingredients, checking stock, and placing reorders.
 */

public class InventoryController {
    private InventorySystem inventorySystem;

    public InventoryController(InventorySystem inventorySystem) {
        this.inventorySystem = inventorySystem;
    }

    /**
     * Adds a specified quantity of an ingredient to the inventory.
     *
     * @param ingredient The ingredient to add.
     * @param quantity The quantity to add.
     */
    public void addIngredientToInventory(Ingredient ingredient, int quantity) {
        inventorySystem.getStock().put(ingredient, inventorySystem.getStock().getOrDefault(ingredient, 0) + quantity);
    }

    /**
     * Checks if there’s enough stock available for a given recipe.
     *
     * @param recipe The recipe to check.
     * @param size The size of the batch.
     * @return true if sufficient stock is available, false otherwise.
     */
    public boolean checkStock(Recipe recipe, int size) {
        // Implementation logic for checking stock
        return true;
    }
}
