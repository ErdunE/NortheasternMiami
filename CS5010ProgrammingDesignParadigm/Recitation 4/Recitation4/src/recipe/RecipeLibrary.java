package recipe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erdun E
 * @version 09/26/2024
 *
 * Manages the collection of recipes in the brewery.
 *
 */

public class RecipeLibrary {
    private List<Recipe> recipes;
    private RecipeController recipeController;

    public RecipeLibrary() {
        this.recipes = new ArrayList<>();
        this.recipeController = new RecipeController(this);
    }

    public RecipeController getRecipeController() {
        return recipeController;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
