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
 * @version 1.5
 * @since 10/17/2024
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
            // Capture batch size from user input
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

                // Get an available vat
                Vat vat = productionSystem.getAvailableVat();
                if (vat != null) {
                    Batch newBatch = new Batch(productionSystem.getBatches().size() + 1, recipe, size);
                    vat.updateStatus("In Use");
                    productionSystem.addBatch(newBatch);
                    System.out.println("Batch created and stored in Vat ID: " + vat.getId());
                } else {
                    System.out.println("No available vats. Please free a vat before creating a new batch.");
                }
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

        Batch batch = productionSystem.findBatchById(batchId);
        Vat destinationVat = productionSystem.findVatById(destinationVatId);

        if (batch == null) {
            System.out.println("Batch ID not found.");
            return;
        }

        if (destinationVat == null) {
            System.out.println("Destination Vat ID not found.");
            return;
        }

        if (destinationVat.getCapacity() < batch.getSize()) {
            System.out.println("Transfer failed: Destination vat capacity exceeded.");
            return;
        }

        System.out.println("Batch ID " + batchId + " is scheduled to transfer to Vat ID " + destinationVatId);
    }

    /**
     * Transfers a batch to a different vat.
     *
     * @param scanner A Scanner to take user input for batch id and vat id.
     */
    public void transferBatch(Scanner scanner) {
        System.out.println("Enter the batch ID to transfer:");
        int batchId = scanner.nextInt();

        System.out.println("Enter the source vat ID:");
        int sourceVatId = scanner.nextInt();

        System.out.println("Enter the destination vat ID:");
        int destinationVatId = scanner.nextInt();
        scanner.nextLine();

        Batch batch = productionSystem.findBatchById(batchId);
        Vat sourceVat = productionSystem.findVatById(sourceVatId);
        Vat destinationVat = productionSystem.findVatById(destinationVatId);

        if (batch == null || sourceVat == null || destinationVat == null) {
            System.out.println("Invalid batch or vat ID.");
            return;
        }

        if (destinationVat.getCapacity() < batch.getSize()) {
            System.out.println("Transfer failed: Destination vat capacity exceeded.");
            return;
        }

        Pipe pipe = new Pipe(sourceVat, destinationVat);
        if (!pipe.isOpen()) {
            pipe.openPipe();
        }

        System.out.println("Transferring batch ID: " + batch.getBatchID() +
                " from Vat " + sourceVat.getId() + " to Vat " + destinationVat.getId());

        // Logic to finalize the transfer (update vat status, remove from source, etc.)
        sourceVat.updateStatus("This Vat is Empty.");
        destinationVat.updateStatus("In Use");

        pipe.closePipe();
        System.out.println("Batch transfer completed successfully.");
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

        Vat vat = productionSystem.getVatById(vatId);
        if (vat != null) {
            vat.updateStatus("This Vat is Empty.");
            System.out.println("Vat ID " + vatId + " has been cleaned.");
        } else {
            System.out.println("Vat ID not found.");
        }
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
        scanner.nextLine();  // Consume newline

        Batch batch = productionSystem.findBatchById(batchId);
        if (batch == null) {
            System.out.println("Batch ID not found.");
            return;
        }

        // Store or log the gravity reading
        batch.addGravityReading(gravity);
        System.out.println("Daily gravity reading of " + gravity + " recorded for batch ID: " + batchId);
    }

    /**
     * Creates a new vat with a specified capacity.
     *
     * @param scanner A Scanner to take user input for vat id.
     */
    public void createVat(Scanner scanner) {
        System.out.println("Enter the ID of the new vat:");
        int vatId = scanner.nextInt();
        System.out.println("Enter the capacity of the new vat:");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        Vat vat = new Vat(vatId, capacity);
        productionSystem.addVat(vat);
    }
}
