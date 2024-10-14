package inventory;

import recipe.Recipe;

import java.util.HashMap;
import java.util.Map;

/**
 * WMC
 * 3, getInventoryController, getStock, constructor
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 2, Ingredient, InventorController
 * RFC
 * 5
 * 3 internal, constructor， getInventoryController, getStock
 * 2 external, HashMap, InventoryController
 * LCOM
 * Low, all methods to manage stock and its controller,
 * So they are cohesive
 */

public class InventorySystem {
    private Map<inventory.Ingredient, Integer> stock;
    private inventory.InventoryController inventoryController;

    public InventorySystem() {
        this.stock = new HashMap<>();
        this.inventoryController = new inventory.InventoryController(this);
    }

    public inventory.InventoryController getInventoryController() {
        return inventoryController;
    }

    public Map<inventory.Ingredient, Integer> getStock() {
        return stock;
    };
}
