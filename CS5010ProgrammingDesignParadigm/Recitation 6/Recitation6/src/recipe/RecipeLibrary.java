package recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * WMC
 *
 * DIT
 *
 * NOC
 *
 * CBO
 *
 * RFC
 *
 * LCOM
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
