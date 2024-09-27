package recipe;

import inventory.Ingredient;

import java.util.Map;

/**
 *
 * @author Erdun E
 * @version 09/26/2024
 *
 * Represents a recipe for making a beer in the brewery.
 *
 */

public class Recipe {
    private String recipeName;
    private String instructions;
    private Map<Ingredient, Integer> ingredients;

    public Recipe(String recipeName, Map<Ingredient, Integer> ingredients) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = "Default instructions";
    }

    public String getName() {
        return recipeName;
    }
    public String getInstructions() {
        return instructions;
    }
    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
