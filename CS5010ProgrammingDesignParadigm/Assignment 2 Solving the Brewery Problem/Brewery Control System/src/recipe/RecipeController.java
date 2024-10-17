package recipe;

import inventory.Ingredient;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The RecipeController class manages the creation and retrieval of recipes in the
 * Brewery Control System. It interacts with the RecipeLibrary to store and retrieve recipes.
 *
 * @author Erdun
 * @version 1.4
 * @since 10/15/2024
 */
public class RecipeController {
    private RecipeLibrary recipeLibrary;

    /**
     * Constructor for RecipeController class.
     *
     * @param recipeLibrary The recipe library where recipes are stored and managed.
     */
    public RecipeController(RecipeLibrary recipeLibrary) {
        this.recipeLibrary = recipeLibrary;
    }

    /**
     * Creates a new recipe by taking input from the user. Prompts the user to input the recipe name and the ingredients along with their quantities. The created recipe is added to the recipe library.
     *
     * @param scanner A  Scanner to take input from the user.
     */
    public void createRecipe(Scanner scanner) {
        // Prompt the user to enter the recipe name
        System.out.println("Enter the name of the recipe:");
        String recipeName = scanner.nextLine();

        // Capture the number of ingredients
        int ingredientCount;
        // Loop to ensure the user enters a valid positive integer for the number of ingredients
        while (true) {
            System.out.println("Enter the number of ingredients (positive integer):");
            if (scanner.hasNextInt()) {
                // Read the number of ingredients
                ingredientCount = scanner.nextInt();
                // Exit loop if valid positive integer is entered
                if (ingredientCount > 0) {
                    break;
                }
                else {
                    System.out.println("The number of ingredients must be positive.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
        scanner.nextLine();

        // Initialize a map to store ingredients and their quantities
        Map<Ingredient, Integer> ingredients = new HashMap<>();

        // Loop through each ingredient based on the specified ingredient count
        for (int i = 1; i <= ingredientCount; i++) {
            // Prompt the user to enter the name of the ingredient
            System.out.println("Enter the name of ingredient " + i + " (or type 'exit' to cancel):");
            String ingredientName = scanner.nextLine().trim();

            // Check if the user wants to exit the recipe creation process
            if (ingredientName.equalsIgnoreCase("exit")) {
                System.out.println("Recipe creation cancelled.");
                return;
            }

            int quantity;
            // Loop to ensure the user enters a valid positive integer for the ingredient quantity
            while (true) {
                System.out.println("Enter the quantity of ingredient " + i + ":");
                if (scanner.hasNextInt()) {
                    // Read the quantity
                    quantity = scanner.nextInt();
                    if (quantity > 0) {
                        // Exit loop if a valid positive integer is entered
                        break;
                    }
                    else {
                        System.out.println("Quantity must be a positive integer.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next();
                }
            }
            scanner.nextLine();

            // Add the ingredient and its quantity to the map
            ingredients.put(new Ingredient(ingredientName, quantity), quantity);
        }

        // Create a new Recipe object with the provided name and ingredients map
        Recipe recipe = new Recipe(recipeName, ingredients);

        // Add the new recipe to the recipe library
        recipeLibrary.getRecipes().add(recipe);

        // Confirm that the recipe was successfully created
        System.out.println("Recipe created: " + recipeName);
    }

    /**
     * Displays the details of a recipe based on user input.
     * Prompts the user to enter the recipe name and retrieves it from the library.
     * If the recipe is found, its name and details are displayed.
     *
     * @param scanner Scanner to take input from the user.
     */
    public void viewRecipe(Scanner scanner) {
        // Capture the recipe name
        System.out.println("Enter the name of the recipe to view:");
        String name = scanner.nextLine();

        // Retrieve the recipe by its name
        Recipe recipe = getRecipeByName(name);
        if (recipe != null) {
            // Display the recipe details if found
            System.out.println("Recipe Name: " + recipe.getName());
            // Additional details about the ingredients can be displayed here if needed
        } else {
            // Display to the user if the recipe was not found
            System.out.println("Recipe not found.");
        }
    }

    /**
     * Gets the recipe object based on the recipe name.
     *
     * @param name The name of the recipe to retrieve.
     * @return The Recipe object if found, otherwise null.
     */
    public Recipe getRecipeByName(String name) {
        // Iterate over all recipes in the library
        for (Recipe recipe : recipeLibrary.getRecipes()) {
            // Check if the recipe name matches the input name
            if (recipe.getName().equals(name)) {
                // Return the matching recipe
                return recipe;
            }
        }
        // Return null if no matching recipe is found
        return null;
    }
}
