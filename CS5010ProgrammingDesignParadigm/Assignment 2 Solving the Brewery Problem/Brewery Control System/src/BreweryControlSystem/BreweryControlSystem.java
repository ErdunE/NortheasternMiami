package BreweryControlSystem;

import production.ProductionSystem;
import recipe.RecipeLibrary;
import inventory.InventorySystem;

/**
 * The BreweryControlSystem class serves as the main entry point to the Brewery Control System.
 * It initializes the primary systems: Production, Inventory, and Recipe Library, and then
 * launches the command-line interface for user interaction.
 * <p>
 * This system is designed to manage various aspects of a brewery, such as creating beer batches,
 * managing stock inventory, and storing beer recipes. The command-line interface allows users to
 * interact with the different subsystems.
 * </p>
 *
 * @author Erdun E
 * @version 1.3
 * @since 10/15/2024
 */


public class BreweryControlSystem {
    /**
     * The main method of the Brewery Control System
     * Initializes the primary systems that the production, inventory, and recipe
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialize the recipe library, inventory system, and production system
        RecipeLibrary recipeLibrary = new RecipeLibrary();
        InventorySystem inventorySystem = new InventorySystem();
        ProductionSystem productionSystem = new ProductionSystem(inventorySystem, recipeLibrary);

        // Create the menu handler to manage user interaction
        MenuHandler menuHandler = new MenuHandler(productionSystem, inventorySystem, recipeLibrary);

        // Start with a welcome message
        System.out.println("Welcome to Brewery Control System!");
        // Handle the main menu to allow user interaction
        menuHandler.handleMainMenu();
    }
}
