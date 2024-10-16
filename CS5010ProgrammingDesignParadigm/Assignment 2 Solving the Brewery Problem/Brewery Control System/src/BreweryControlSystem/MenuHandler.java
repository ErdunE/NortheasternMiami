package BreweryControlSystem;

import production.ProductionSystem;
import inventory.InventorySystem;
import recipe.RecipeLibrary;
import java.util.Scanner;

/**
 * The MenuHandler class is responsible for managing the user interface
 * and handling user interactions within the Brewery Control System.
 * <p>
 * It provides methods for displaying and navigating through the main
 * menu, production menu, inventory menu, and recipe menu. Meanwhile
 * each menu to a specific subsystem (Production, Inventory, Recipe
 * Library), and the user can interact with these menus to manage
 * different brewery operations, such as creating batches, checking
 * stock and viewing recipes.
 * </p>
 *
 * @author Erdun E
 * @version 1.3
 * @since 10/15/2024
 */

public class MenuHandler {
    private ProductionSystem productionSystem;
    private InventorySystem inventorySystem;
    private RecipeLibrary recipeLibrary;
    private Scanner scanner;

    /**
     * Constructor for the MenuHandler class
     *
     * @param productionSystem The production system used to manage beer production processes.
     * @param inventorySystem The inventory system used to manage ingredient stock and inventory operations.
     * @param recipeLibrary The recipe library used to create and store recipes for beer production.
     */
    public MenuHandler(ProductionSystem productionSystem, InventorySystem inventorySystem, RecipeLibrary recipeLibrary) {
        this.productionSystem = productionSystem;
        this.inventorySystem = inventorySystem;
        this.recipeLibrary = recipeLibrary;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Handles the main menu of the Brewery Control System.
     * This method continuously displays the main menu and processes user commands until the user exits the system.
     * The user can select between Production, Inventory, Recipe Library, Help, or Exit options.
     */
    public void handleMainMenu() {
        String command;
        do {
            // Display main menu
            mainMenuContent();
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    // Display production menu
                    handleProductionMenu();
                    break;
                case "2":
                    // Display inventory menu
                    handleInventoryMenu();
                    break;
                case "3":
                    // Display recipe menu
                    handleRecipeMenu();
                    break;
                case "4":
                    // Display main menu help message
                    HelpMessageHandler.displayMainMenuHelpMessage();
                    break;
                case "5":
                    // Exit system
                    System.out.println("Brewery Control System Is Exiting...");
                    System.exit(0);
                default:
                    // Display invalid message
                    System.out.println("Invalid command. Please try again.");
            }
        } while (true);
    }

    /**
     * Handles the production menu where users can create, monitor, and manage production batches.
     * This method processes user commands to manage various production related tasks such as create batch,
     * monitor batch, bottle batch, schedule and transfers batch, create and clean vats, and record
     * daily reading.
     */
    private void handleProductionMenu(){
        String command;
        do {
            // Display production menu
            productionMenuContent();
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    // Create a new batch
                    productionSystem.getProductionController().createBatch(scanner);
                    break;
                case "2":
                    // Monitor exist batch
                    productionSystem.getProductionController().monitorBatch(scanner);
                    break;
                case "3":
                    // Bottle a complete batch
                    productionSystem.getProductionController().bottleBatch(scanner);
                    break;
                case "4":
                    // Schedule transfer a batch
                    productionSystem.getProductionController().scheduleTransfer(scanner);
                    break;
                case "5":
                    // Transfer a batch
                    productionSystem.getProductionController().transferBatch(scanner);
                    break;
                case "6":
                    // Clean a vat
                    productionSystem.getProductionController().cleanVat(scanner);
                    break;
                case "7":
                    // Record the daily reading
                    productionSystem.getProductionController().recordDailyReading(scanner);
                    break;
                case "8":
                    // Create a new Vat
                    productionSystem.getProductionController().createVat(scanner);
                    break;
                case "9":
                    // Go back to the main menu
                    return;
                case "10":
                    // Display production menu help message
                    HelpMessageHandler.displayProductionMenuHelpMessage();
                    break;
                case "11":
                    // Exit system
                    System.out.println("Brewery Control System Is Exiting...");
                    System.exit(0);
                default:
                    // Display invalid message
                    System.out.println("Invalid command. Please try again.");
            }
        } while (true);
    }
    /**
     * Handles the inventory menu where users can add ingredients to the inventory or check stock levels.
     * This method processes user commands for inventory management operations. Such as add ingredient
     * check stock etc.
     */
    private void handleInventoryMenu(){
        String command;
        do {
            // Display inventory menu
            inventoryMenuContent();
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    // Add new ingredient to the inventory store
                    inventorySystem.getInventoryController().addIngredientToInventory(scanner);
                    break;
                case "2":
                    // Check current stock capacity
                    inventorySystem.getInventoryController().checkStock(scanner);
                    break;
                case "3":
                    // Go back to the main menu
                    return;
                case "4":
                    // Display inventory menu help message
                    HelpMessageHandler.displayInventoryMenuHelpMessage();
                    break;
                case "5":
                    // Exit system
                    System.out.println("Brewery Control System Is Exiting...");
                    System.exit(0);
                default:
                    // Display invalid message
                    System.out.println("Invalid command. Please try again.");
            }
        } while (true);
    }
    /**
     * Handles the recipe menu where users can create or view beverage recipes stored in the recipe library.
     * This method processes user commands for recipe creation and retrieval. Such as create recipe,
     * view recipe etc.
     */
    private void handleRecipeMenu(){
        String command;
        do {
            // Display recipe menu
            recipeMenuContent();
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    // Create a new recipe
                    recipeLibrary.getRecipeController().createRecipe(scanner);
                    break;
                case "2":
                    // View current exist recipe
                    recipeLibrary.getRecipeController().viewRecipe(scanner);
                    break;
                case "3":
                    // Go back to the main menu
                    return;
                case "4":
                    // Display recipe menu help message
                    HelpMessageHandler.displayRecipeMenuHelpMessage();
                    break;
                case "5":
                    // Exit system
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    // Display invalid message
                    System.out.println("Invalid command. Please try again.");
            }
        } while (true);
    }

    /**
     * Displays the main menu content
     */
    private static void mainMenuContent(){
        System.out.println("====Main Menu====");
        System.out.println("1. Production");
        System.out.println("2. Inventory");
        System.out.println("3. Recipe Library");
        System.out.println("4. Help");
        System.out.println("5. Exit");
        System.out.println("Please select an option: ");
    }


    /**
     * Displays the production menu content
     */
    private static void productionMenuContent(){
        System.out.println("====Production Menu====");
        System.out.println("1. Create Batch");
        System.out.println("2. Monitor Batch");
        System.out.println("3. Bottle Batch");
        System.out.println("4. Schedule Transfer");
        System.out.println("5. Transfer Batch");
        System.out.println("6. Clean Container");
        System.out.println("7. Record Daily Reading");
        System.out.println("8. Create Vat");
        System.out.println("9. Back to Main Menu");
        System.out.println("10. Help");
        System.out.println("11. Exit");
        System.out.println("Please select an option: ");
    }


    /**
     * Displays the inventory menu content
     */
    private static void inventoryMenuContent(){
        System.out.println("====Inventory Menu====");
        System.out.println("1. Add Ingredient");
        System.out.println("2. Check Stock");
        System.out.println("3. Back to Main Menu");
        System.out.println("4. Help");
        System.out.println("5. Exit");
        System.out.println("Please select an option: ");
    }



    /**
     * Displays the recipe menu content
     */
    private static void recipeMenuContent(){
        System.out.println("====Recipe Menu====");
        System.out.println("1. Create Recipe");
        System.out.println("2. View Recipe");
        System.out.println("3. Back to Main Menu");
        System.out.println("4. Help");
        System.out.println("5. Exit");
        System.out.println("Please select an option: ");
    }

}