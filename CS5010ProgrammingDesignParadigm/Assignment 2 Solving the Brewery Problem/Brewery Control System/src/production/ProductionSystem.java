package production;

import java.util.ArrayList;
import java.util.List;
import inventory.InventorySystem;
import recipe.RecipeLibrary;

/**
 * The ProductionSystem class manages the entire production process in the
 * Brewery Control System. It handles the creation and monitor of batches,
 * as well as interactions with the vats and transfers between them. The
 * production system also integrates with the inventory system to manage
 * ingredient stock and the recipe library for creating batches.

 *
 * @author Erdun
 * @version 1.5
 * @since 10/17/2024
 */
public class ProductionSystem {
    private ProductionController productionController;
    private List<Batch> batches;
    private List<Vat> vats;

    /**
     * Constructor for the ProductionSystem class.
     *
     * @param inventorySystem The inventory system to manage ingredient stocks.
     * @param recipeLibrary The recipe library to retrieve beverage recipes.
     */
    public ProductionSystem(InventorySystem inventorySystem, RecipeLibrary recipeLibrary) {
        this.productionController = new ProductionController(inventorySystem, recipeLibrary, this);
        // Initializes the list to hold all production batches
        this.batches = new ArrayList<>();
        this.vats = new ArrayList<>();

        // Initialize vats
        for (int i = 1; i <= 5; i++){
            vats.add(new Vat(i, 1000));
        }
    }

    /**
     * Returns the production controller responsible for managing production tasks.
     *
     * @return The ProductionController for handling operations like create and manage batch.
     */
    public ProductionController getProductionController() {
        return productionController;
    }

    /**
     * Returns the list of all batches currently in production.
     *
     * @return A list of Batch represent the batches in production.
     */
    public List<Batch> getBatches() {
        return batches;
    }

    /**
     * Add a new batch to the production system.
     *
     * @param batch The Batch to be added to the production system.
     */
    public void addBatch(Batch batch) {
        // Adds the batch to the internal list of batches
        batches.add(batch);
    }

    /**
     * Finds and returns a batch based on the given batch ID.
     *
     * @param batchId The ID of the batch to be retrieved.
     * @return The Batch with the matching batch ID, or null if not found.
     */
    public Batch findBatchById(int batchId) {
        // Loop through each batch in the list to find the batch with the given ID
        for (Batch batch : batches) {
            // Check if the batch ID matches the input ID
            if (batch.getBatchID() == batchId) {
                // Return the batch if found
                return batch;
            }
        }
        // Return null if no batch with the specified ID is found
        return null;
    }

    /**
     * Finds and returns a vat based on the given vat ID.
     *
     * @param vatId The ID of the vat to be retrieved.
     * @return The Vat with the matching ID, or null if not found.
     */
    public Vat getVatById(int vatId) {
        for (Vat vat : vats) {
            if (vat.getId() == vatId) {
                return vat;
            }
        }
        return null;
    }

    /**
     * Retrieves the first available vat that is currently empty and ready for use.
     * If no vat is available, this method returns null.
     *
     * @return The first available Vat object, or null if all vats are occupied.
     */
    public Vat getAvailableVat() {
        for (Vat vat : vats) {
            if (vat.getStatus().equals("This Vat is Empty.")) {
                return vat;
            }
        }
        return null;
    }

    /**
     * Searches for a vat by its unique ID. This is a helper method used in batch
     * transfers and vat-related operations.
     *
     * @param vatId The ID of the vat to search for.
     * @return The Vat if found, or null if no vat matches the given ID.
     */
    public Vat findVatById(int vatId) {
        for (Vat vat : vats) {
            if (vat.getId() == vatId) {
                return vat;
            }
        }
        return null;
    }

    /**
     * Adds a new vat to the production system. This method is used when expanding the
     * production capacity by adding more vats.
     *
     * @param vat The Vat object to be added to the production system.
     */
    public void addVat(Vat vat) {
        vats.add(vat);
        System.out.println("Added new Vat with ID: " + vat.getId());
    }


}
