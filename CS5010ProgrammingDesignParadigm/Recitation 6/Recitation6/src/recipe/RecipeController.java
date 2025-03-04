package recipe;

import inventory.Ingredient;
import java.util.Map;

/**
 * WMC
 * 3, constructor, createRecipe, getRecipe
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 2, RecipeLibrary, Recipe
 * RFC
 * 5
 * 3 internal, constructor, createRecipe, getRecipe
 * 2 external, recipeLibrary.getRecipes, recipeLibrary.getRecipes().add
 * LCOM
 * Low, all methods manage recipes within the recipe library, make the class cohesive
 */

public class RecipeController {
    private RecipeLibrary recipeLibrary;

    public RecipeController(RecipeLibrary recipeLibrary) {
        this.recipeLibrary = recipeLibrary;
    }

    /**
     * Creates a new recipe and adds it to the library.
     *
     * @param recipeName The name of the recipe.
     * @param ingredients The ingredients required for the recipe.
     */
    public void createRecipe(String recipeName, Map<Ingredient,Integer> ingredients){
        Recipe recipe = new Recipe(recipeName, ingredients);
        recipeLibrary.getRecipes().add(recipe);
    }

    public Recipe getRecipe(String recipeName){
        for(Recipe recipe : recipeLibrary.getRecipes()){
            if(recipe.getName().equals(recipeName)){
                return recipe;
            }
        }
        return null;
    }
}
