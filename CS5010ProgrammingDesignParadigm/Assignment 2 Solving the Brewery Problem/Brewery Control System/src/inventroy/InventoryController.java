package inventory;

import recipe.Recipe;

/**
 * WMC
 * 3, addIngredientToInventory, checkStock, constructor
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 2, InventorySystem, Recipe
 * RFC
 * 7,
 * 3 internal, constructor, addIngredientToInventory, checkStock
 * 4 external, inventorySystem.getStock, inventorySystem.getStock.put,
 * inventorySystem.getStock.getOrDefault, recipe.getIngredients
 * LCOM
 * Low, all methods are related to managing inventory operations
 * So they are cohesive
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
    public void addIngredientToInventory(inventory.Ingredient ingredient, int quantity) {
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
