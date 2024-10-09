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
