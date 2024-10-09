package production;

import inventory.InventorySystem;
import recipe.RecipeLibrary;

/**
 * WMC
 *
 * DIT
 *
 * NOC
 *
 * CBO
 *
 * RFC
 *
 * LCOM
 *
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
