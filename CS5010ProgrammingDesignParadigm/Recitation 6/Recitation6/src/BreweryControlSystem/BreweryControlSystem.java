package BreweryControlSystem;

import inventory.InventorySystem;
import production.ProductionSystem;
import recipe.RecipeLibrary;

/**
 * WMC
 * 2, main, constructor
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 3, ProductionSystem, InventorySystem, RecipeLibrary
 * RFC
 * 12,
 * 2 internal, constructor, main,
 * 10 external, 10 scenarios
 * LCOM
 * Low, cohesive because it manages the coordination of subsystems.
 * All methods work towards the same goal of controlling production,
 * inventory, and recipes.
 */


public class BreweryControlSystem {

    private ProductionSystem productionSystem;
    private InventorySystem inventorySystem;
    private RecipeLibrary recipeLibrary;

    // Create instances of all systems
    public BreweryControlSystem() {
        this.inventorySystem = new InventorySystem();
        this.recipeLibrary = new RecipeLibrary();
        this.productionSystem = new ProductionSystem(inventorySystem, recipeLibrary);
    }

    // Start the brewery control system
    public static void main(String[] args) {
        BreweryControlSystem breweryControlSystem = new BreweryControlSystem();

        // Add initial data and start program with the 10 scenarios

        // 1. Add ingredients to Inventory
        breweryControlSystem.inventorySystem.getInventoryController().addIngredientToInventory(null, 100);
        System.out.println("Add ingredient to Inventory success.");

        // 2. Bottle a Batch
        breweryControlSystem.productionSystem.getProductionController().bottleBatch(1);
        System.out.println("Bottle a batch success.");

        // 3. Clean a Container
        breweryControlSystem.productionSystem.getProductionController().cleanContainer(1);
        System.out.println("Clean a container.");

        // 4. Create a Batch
        breweryControlSystem.productionSystem.getProductionController().createBatch("Ale", 50);
        System.out.println("Create a batch.");

        // 5. Create a Vat
        breweryControlSystem.productionSystem.getProductionController().createVat(200);
        System.out.println("Create a vat.");

        // 6. Create a Recipe
        breweryControlSystem.recipeLibrary.getRecipeController().createRecipe("Ale", null);
        System.out.println("Create a recipe.");

        // 7. Monitor a Batch
        breweryControlSystem.productionSystem.getProductionController().monitorBatch(1);
        System.out.println("Monitor a batch.");

        // 8. Record a Daily Reading
        breweryControlSystem.productionSystem.getProductionController().recordDailyReading(1, 1.);
        System.out.println("Record a daily reading.");

        // 9. Schedule a Transfer
        breweryControlSystem.productionSystem.getProductionController().scheduleTransfer(1, 2);
        System.out.println("Schedule a transfer.");

        // 10. Transfer a Batch
        breweryControlSystem.productionSystem.getProductionController().transferBatch(1);
        System.out.println("Transfer a batch.");
    }
}
