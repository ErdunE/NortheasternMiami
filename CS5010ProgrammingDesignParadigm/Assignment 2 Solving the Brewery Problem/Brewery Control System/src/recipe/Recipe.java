package recipe;

import inventory.Ingredient;
import java.util.Map;

/**
 * The Recipe class represents a recipe used in the Brewery Control System for producing beer.
 * Each recipe has a name and a list of ingredients with their corresponding quantities.
 *
 * @author Erdun
 * @version 1.3
 * @since 10/15/2024
 */
public class Recipe {
    private String recipeName;
    private Map<Ingredient, Integer> ingredients;

    /**
     * Constructor for Recipe class.
     *
     * @param recipeName The name of the recipe.
     * @param ingredients A map containing the ingredients required for this recipe and quantities.
     */
    public Recipe(String recipeName, Map<Ingredient, Integer> ingredients) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
    }

    /**
     * Returns the name of the recipe.
     *
     * @return The name of the recipe.
     */
    public String getName() {
        return recipeName;
    }

    /**
     * Returns the map of ingredients required for this recipe.
     *
     * @return A map of Ingredient and their required quantities.
     */
    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }
}
