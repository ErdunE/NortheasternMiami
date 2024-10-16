package inventory;

import java.util.HashMap;
import java.util.Map;

/**
 *  The InventorySystem class manages the stock of ingredients used in the
 *  Brewery Control System. It maintains ingredients and their quantities.
 *  This class is important for maintaining the ingredient stock levels for
 *  production and ensuring the system has enough ingredients to produce beers.
 *
 * @author Erdun E
 * @version 1.3
 * @since 10/15/2024
 */

public class InventorySystem {
    private Map<Ingredient, Integer> stock;
    private InventoryController inventoryController;

    /**
     * Constructs an InventorySystem class.
     */
    public InventorySystem() {
        // Initial the stock as an empty HashMap and the ingredients will be stored with their quantities
        this.stock = new HashMap<>();
        // Creates a new InventoryController, pass InventorySystem to manage inventory operations
        this.inventoryController = new InventoryController(this);
    }

    /**
     * Returns the InventoryController associated with this inventory system.
     *
     * @return the InventoryController
     */
    public InventoryController getInventoryController() {
        return inventoryController;
    }

    /**
     * Returns the stock map that holds the current inventory of ingredients.
     *
     * @return A map representing the current stock of ingredients
     */
    public Map<Ingredient, Integer> getStock() {
        return stock;
    };
}
