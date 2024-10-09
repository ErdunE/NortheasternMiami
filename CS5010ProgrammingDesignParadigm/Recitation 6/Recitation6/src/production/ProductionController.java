package production;

import inventory.InventorySystem;
import recipe.Recipe;
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

public class ProductionController {
    private InventorySystem inventorySystem;
    private RecipeLibrary recipeLibrary;

    public ProductionController(InventorySystem inventorySystem, RecipeLibrary recipeLibrary) {
        this.inventorySystem = inventorySystem;
        this.recipeLibrary = recipeLibrary;
    }

    /**
     * Starts a new batch of a beer.
     *
     * @param recipeName The name of the recipe to use.
     * @param size The size of the batch to produce.
     */
    public void createBatch(String recipeName, int size) {
        Recipe recipe = recipeLibrary.getRecipeController().getRecipe(recipeName);
        if (recipe != null && inventorySystem.getInventoryController().checkStock(recipe, size)) {
            // Implementation logic for creating batch
        }
    }

    /**
     * Monitors the status of a batch.
     *
     * @param batchId The ID of the batch to monitor.
     */
    public void monitorBatch(int batchId){
        // Implementation logic for monitoring batch
    }

    /**
     * Bottles a batch after it has completed production.
     *
     * @param batchId The ID of the batch to bottle.
     */
    public void bottleBatch(int batchId) {
        // Implementation logic for bottling batch
    }

    /**
     * Schedules a transfer for a batch to a different vat.
     *
     * @param batchId The ID of the batch to transfer.
     * @param destinationVatId The ID of the destination vat.
     */
    public void scheduleTransfer(int batchId, int destinationVatId) {
        // Implementation logic for scheduling transfer
    }

    /**
     * Transfers a batch to a different vat.
     *
     * @param batchId The ID of the batch to transfer.
     */
    public void transferBatch(int batchId) {
        // Implementation logic for transferring batch
    }

    /**
     * Cleans a container and updates its status.
     *
     * @param vatId The ID of the vat to clean.
     */
    public void cleanContainer(int vatId) {
        // Implementation logic for cleaning container
    }

    /**
     * Records a daily reading for a batch in production.
     *
     * @param batchId The ID of the batch.
     * @param gravity The gravity reading to record.
     */
    public void recordDailyReading(int batchId, double gravity) {
        // Implementation logic for recording daily reading
    }

    /**
     * Creates a new vat in the production system.
     *
     * @param capacity The capacity of the new vat.
     */
    public void createVat(int capacity) {
        // Implementation logic for creating vat
    }
}
