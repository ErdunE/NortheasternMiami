package production;

import inventory.Ingredient;
import inventory.InventorySystem;
import recipe.Recipe;
import recipe.RecipeLibrary;

import java.util.Map;
import java.util.Scanner;

/**
 * The ProductionController class is responsible for managing the production
 * process in a brewery, including creating batch, monitoring batch, bottle batch,
 * schedule and transfer, create and clean vat, and record the daily reading.
 *
 * @author Erdun E
 * @version 1.3
 * @since 10/15/2024
 */

public class ProductionController {
    private InventorySystem inventorySystem;
    private RecipeLibrary recipeLibrary;
    private ProductionSystem productionSystem;

    /**
     * Constructor for ProductionController class.
     *
     * @param inventorySystem The inventory system to manage ingredient stock.
     * @param recipeLibrary The recipe library to retrieve beer recipes.
     * @param productionSystem The production system to manage batches and vats.
     */
    public ProductionController(InventorySystem inventorySystem, RecipeLibrary recipeLibrary, ProductionSystem productionSystem) {
        this.inventorySystem = inventorySystem;
        this.recipeLibrary = recipeLibrary;
        this.productionSystem = productionSystem;
    }

    /**
     * Creates a new production batch using a recipe provided by the user. The method
     * checks if there are enough ingredients in stock to produce the batch, and if so,
     * the ingredients are deducted from the stock and the batch is created.
     *
     * @param scanner A Scanner to take user input for recipe name and batch size.
     */
    public void createBatch(Scanner scanner) {
        System.out.println("Enter the recipe name to create a batch:");
        // Get the recipe name from the user
        String recipeName = scanner.nextLine();
        // Retrieve the recipe from the library
        Recipe recipe = recipeLibrary.getRecipeController().getRecipeByName(recipeName);

        if (recipe != null) {
            // If the recipe exists, ask for the batch size
            System.out.println("Enter the batch size:");
            int size = scanner.nextInt();
            scanner.nextLine();

            // Check if there is enough stock to create the batch
            if (inventorySystem.getInventoryController().checkStock(recipe, size)) {
                // Deduct the required ingredients from the inventory
                for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
                    Ingredient ingredient = entry.getKey();
                    // Scale ingredient quantity by batch size
                    int requiredQuantity = entry.getValue() * size / 100;
                    inventorySystem.getStock().put(ingredient,
                            inventorySystem.getStock().get(ingredient) - requiredQuantity);
                }

                // Create and add the new batch to the production system
                Batch newBatch = new Batch(productionSystem.getBatches().size() + 1, recipe, size);
                // Add the batch to the system
                productionSystem.addBatch(newBatch);
                System.out.println("Batch created for recipe: " + recipeName);
            } else {
                System.out.println("Failed to create batch. Insufficient stock.");
            }
        } else {
            System.out.println("Recipe not found.");
        }
    }

    /**
     * Monitors an existing batch based on its ID.
     * The method displays the batch ID and its current production status.
     *
     * @param scanner A Scanner to take user input for batch id.
     */
    public void monitorBatch(Scanner scanner) {
        System.out.println("Enter the batch ID to monitor:");
        int batchId = scanner.nextInt();
        scanner.nextLine();

        // Retrieve the batch by ID from the production system
        Batch batch = productionSystem.findBatchById(batchId);
        if (batch != null) {
            System.out.println("Monitoring batch ID: " + batch.getBatchID());
            // Display the batch's current status
            System.out.println("Status: " + batch.getStatus());
        } else {
            System.out.println("Batch ID not found.");
        }
    }

    /**
     * Bottles a completed batch and updates its status to "Completed".
     *
     * @param scanner A Scanner to take user input for batch id.
     */
    public void bottleBatch(Scanner scanner) {
        System.out.println("Enter the batch ID to bottle:");
        int batchId = scanner.nextInt();
        scanner.nextLine();

        // Retrieve the batch by ID
        Batch batch = productionSystem.findBatchById(batchId);
        if (batch != null) {
            // Update the batch status to "Completed"
            batch.updateStatus("Completed");
            System.out.println("Batch ID " + batchId + " bottled and marked as completed.");
        } else {
            System.out.println("Batch ID not found.");
        }
    }

    /**
     * Schedule transfer a batch.
     *
     * @param scanner A Scanner to take user input for batch id and vat id.
     */
    public void scheduleTransfer(Scanner scanner) {
        System.out.println("Enter the batch ID to schedule transfer:");
        int batchId = scanner.nextInt();
        System.out.println("Enter the destination vat ID:");
        int destinationVatId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Transfer scheduled for batch ID: " + batchId);
    }

    /**
     * Transfers a batch to a different vat.
     *
     * @param scanner A Scanner to take user input for batch id and vat id.
     */
    public void transferBatch(Scanner scanner) {
        System.out.println("Enter the batch ID to transfer:");
        int batchId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Batch ID " + batchId + " transferred.");
    }

    /**
     * Marks a specified vat as cleaned.
     *
     * @param scanner A Scanner to take user input for vat id.
     */
    public void cleanVat(Scanner scanner) {
        System.out.println("Enter the vat ID to clean:");
        int vatId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Vat ID " + vatId + " has been cleaned.");
    }

    /**
     * Records a daily gravity reading for a batch.
     *
     * @param scanner A Scanner to take user input for reading.
     */
    public void recordDailyReading(Scanner scanner) {
        System.out.println("Enter the batch ID to record the daily reading for:");
        int batchId = scanner.nextInt();
        System.out.println("Enter the gravity reading:");
        double gravity = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Daily reading recorded for batch ID: " + batchId);
    }

    /**
     * Creates a new vat with a specified capacity.
     *
     * @param scanner A Scanner to take user input for vat id.
     */
    public void createVat(Scanner scanner) {
        System.out.println("Enter the capacity of the new vat:");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Vat created with capacity: " + capacity);
    }
}
