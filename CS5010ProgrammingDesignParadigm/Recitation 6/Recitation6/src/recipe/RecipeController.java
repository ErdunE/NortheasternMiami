package recipe;

import inventory.Ingredient;
import java.util.Map;

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