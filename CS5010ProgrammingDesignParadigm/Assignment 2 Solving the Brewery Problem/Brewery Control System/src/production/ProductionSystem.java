package production;

import inventory.InventorySystem;
import recipe.RecipeLibrary;

/**
 * WMC
 * 2, constructor, getProductionController
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 1 productionController
 * RFC
 * 3
 * 2 internal, constructor, getProductionController
 * 1 external, getProductionController
 * LCOM
 * Low, all methods focus on managing the production system
 */

public class ProductionSystem {

    private ProductionController productionController;

    public ProductionSystem(InventorySystem inventorySystem, RecipeLibrary recipeLibrary) {
        this.productionController = new ProductionController(inventorySystem, recipeLibrary);
    }

    public ProductionController getProductionController() {
        return productionController;
    }

}
