package production;

import inventory.InventorySystem;
import recipe.RecipeLibrary;

/**
 *
 * @author Erdun E
 * @version 09/26/2024
 *
 * Manages the production process in the brewery.
 * batch creation, monitoring production, scheduling transfers etc.
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
