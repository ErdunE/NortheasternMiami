package recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * The RecipeLibrary class manages a collection of recipes used in the Brewery
 * Control System. It provides storage for all created recipes and offers access
 * to the RecipeController for managing recipe-related operations.
 *
 * @author Erdun
 * @version 1.3
 * @since 10/15/2024
 */
public class RecipeLibrary {
    private List<Recipe> recipes;
    private RecipeController recipeController;

    /**
     * Constructs a RecipeLibrary instance.
     * Initializes an empty list to store recipes and sets up a RecipeController.
     */
    public RecipeLibrary() {
        this.recipes = new ArrayList<>();
        this.recipeController = new RecipeController(this);
    }

    /**
     * Returns the associated with this recipe library.
     *
     * @return The RecipeController managing recipe related operations.
     */
    public RecipeController getRecipeController() {
        return recipeController;
    }

    /**
     * Returns the list of all recipes stored in the library.
     *
     * @return A list of Recipe representing the stored recipes.
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }
}
