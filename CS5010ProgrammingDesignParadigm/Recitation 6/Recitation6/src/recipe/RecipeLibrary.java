package recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * WMC
 * 3, constructor, getRecipeController, getRecipes
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 1 RecipeController
 * RFC
 * 5
 * 3 internal, constructor, getRecipeController, getRecipes
 * 2 external, ArrayList, RecipeController
 * LCOM
 * Low, all method manage the recipe library, make the class cohesive
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
