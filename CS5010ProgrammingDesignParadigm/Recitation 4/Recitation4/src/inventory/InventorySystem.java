package inventory;

import recipe.Recipe;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Erdun E
 * @version 09/26/2024
 *
 * Manages the inventory of ingredients and beers.
 *
 */

public class InventorySystem {
    private Map<Ingredient, Integer> stock;
    private InventoryController inventoryController;

    public InventorySystem() {
        this.stock = new HashMap<>();
        this.inventoryController = new InventoryController(this);
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public Map<Ingredient, Integer> getStock() {
        return stock;
    };
}
