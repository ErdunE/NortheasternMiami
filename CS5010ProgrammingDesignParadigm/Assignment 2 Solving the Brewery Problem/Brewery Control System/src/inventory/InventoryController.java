package inventory;

import recipe.Recipe;
import java.util.Scanner;
import java.util.Map;

/**
 * The InventoryController class manages the operations related to the inventory system,
 * including adding ingredients, checking stock levels, and verifying if enough stock exists
 * to fulfill a production batch.
 *
 * @author Erdun E
 * @version 1.7
 * @since 10/15/2024
 */

public class InventoryController {
    private InventorySystem inventorySystem;

    /**
     * Constructs an InventoryController class.
     *
     * @param inventorySystem The inventory system used to manage ingredient stock levels.
     */
    public InventoryController(InventorySystem inventorySystem) {
        this.inventorySystem = inventorySystem;
    }

    /**
     * Adds an ingredient to the inventory based on user input.
     * The user is prompted to enter the name of the ingredient and the quantity to add.
     *
     * @param scanner A Scanner object used to read user input.
     */
    public void addIngredientToInventory(Scanner scanner) {
        // Prompt the user for the ingredient name
        System.out.println("Enter the name of the ingredient:");
        String name = scanner.nextLine();

        // Prompt the user for the ingredient quantity
        System.out.println("Enter the quantity of the ingredient:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Check if the ingredient already exists in the stock
        for (Ingredient ingredient : inventorySystem.getStock().keySet()) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                // If found, update the quantity
                int currentQuantity = inventorySystem.getStock().get(ingredient);
                inventorySystem.getStock().put(ingredient, currentQuantity + quantity);
                System.out.println("Updated " + name + " quantity to " + (currentQuantity + quantity));
                return;
            }
        }

        // Create a new Ingredient object with the specified name and quantity
        Ingredient ingredient = new Ingredient(name, quantity);

        // If the ingredient already exists, add the quantity to the existing amount.
        // Otherwise, add a new entry
        inventorySystem.getStock().put(ingredient, inventorySystem.getStock().getOrDefault(ingredient, 0) + quantity);

        // Display that the ingredient was added successfully
        System.out.println("Ingredient " + name + " added to inventory.");
    }

    /**
     * Displays the current stock of ingredients in the inventory.
     * If the inventory is empty, the user is notified.
     *
     * @param scanner A Scanner object used to read user input.
     */
    public void checkStock(Scanner scanner) {
        // Check if the inventory is empty
        if (inventorySystem.getStock().isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            // If the inventory is not empty, start by displaying a message
            // indicating that the system is checking the stock levels.
            System.out.println("Checking stock...");

            // Loop through each entry in the inventory's stock map, where
            // the key is the Ingredient object and the value is the corresponding
            // quantity available in the inventory.
            for (Map.Entry<Ingredient, Integer> entry : inventorySystem.getStock().entrySet()) {
                // Retrieve the ingredient from the current map entry
                Ingredient ingredient = entry.getKey();

                // Retrieve the quantity from the current map entry
                int quantity = entry.getValue();

                // Display the ingredient's name and the current quantity available in the inventory
                System.out.println(ingredient.getName() + ": " + quantity + " units");
            }
        }
    }

    /**
     * Checks if there are enough ingredients in the inventory to create a batch of the given size based on the specified recipe. For each ingredient in the recipe, it verifies that the stock meets the required quantity for the batch.
     *
     * @param recipe The Recipe containing the list of ingredients and their required quantities.
     * @param size The size of the batch to be created, used to scale the ingredient quantity.
     * @return {@code true} if the stock has enough ingredients to create the batch, otherwise {@code false}.
     */
    public boolean checkStock(Recipe recipe, int size) {
        // Iterate through each ingredient required by the recipe
        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
            Ingredient ingredient = entry.getKey();
            int requiredQuantity = entry.getValue() * size / 100;

            // Check if the available stock is less than the required quantity
            if (inventorySystem.getStock().getOrDefault(ingredient, 0) < requiredQuantity) {
                System.out.println("Insufficient " + ingredient.getName() + " for the batch.");
                return false;
            }
        }

        // Return true if all ingredients have enough stock
        return true;
    }
}