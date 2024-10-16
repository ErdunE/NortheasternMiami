package BreweryControlSystem;

/**
 * The HelpMessageHandler class provides static methods to display help messages for
 * multiply menus in the Brewery Control System. Each method to a different menu and
 * provides information about the available options in that menu.
 *
 * @author Erdun E
 * @version 1.3
 * @since 10/15/2024
 */
public class HelpMessageHandler {

    /**
     * Displays the help message for the Main Menu, describing each available option
     * such as Production, Inventory, Recipe Library, Help, and Exit.
     */
    public static void displayMainMenuHelpMessage(){
        System.out.println("\n Main Menu Help :");
        System.out.println("     Production : Manage beverage production processes, including batch creation, monitoring, and transfer.");
        System.out.println("      Inventory : Manage the ingredients in the brewery's inventory.");
        System.out.println(" Recipe Library : Create and view recipes for beverage production.");
        System.out.println("           Help : Display help for each options.");
        System.out.println("           Exit : Exit the application.");
    }

    /**
     * Displays the help message for the Production Menu, describing each available
     * option such as creating a batch, monitoring batches, bottling batches,
     * transferring batches etc.
     */
    public static void displayProductionMenuHelpMessage(){
        System.out.println("\nProduction Menu Help :");
        System.out.println("        Create Batch : Start the production of a new batch of beverage.");
        System.out.println("       Monitor Batch : Check the status of a batch in production.");
        System.out.println("        Bottle Batch : Bottle the completed batch of beverage.");
        System.out.println("   Schedule Transfer : Plan the transfer of a batch from one vat to another.");
        System.out.println("      Transfer Batch : Execute the transfer of a batch between vats.");
        System.out.println("     Clean Container : Mark a vat or container as cleaned.");
        System.out.println("Record Daily Reading : Log daily readings for a fermenting batch.");
        System.out.println("          Create Vat : Add a new vat to the brewery.");
        System.out.println("   Back to Main Menu : Return to the main menu.");
        System.out.println("                Help : Display help for production operations.");
        System.out.println("                Exit : Exit the application.");
    }

    /**
     * Displays the help message for the Inventory Menu, providing information on how to
     * add ingredients to the inventory and check stock levels.
     */
    public static void displayInventoryMenuHelpMessage(){
        System.out.println("\n Inventory Menu Help : ");
        System.out.println("       Add Ingredient: Add ingredients to the inventory.");
        System.out.println("          Check Stock: Verify the stock levels of ingredients in inventory.");
        System.out.println("   Back to Main Menu : Return to the main menu.");
        System.out.println("                 Help: Display help for inventory operations.");
        System.out.println("                 Exit: Exit the application.");
    }

    /**
     * Displays the help message for the Recipe Menu, explaining how to create new recipes
     * and view existing recipes in the recipe library.
     */
    public static void displayRecipeMenuHelpMessage(){
        System.out.println("\n    Recipe Menu Help :");
        System.out.println("       Create Recipe : Add a new recipe to the recipe library.");
        System.out.println("         View Recipe : Display a specific recipe from the recipe library.");
        System.out.println("   Back to Main Menu : Return to the main menu.");
        System.out.println("                Help : Display help for recipe management.");
        System.out.println("                Exit : Exit the application.");
    }
}
