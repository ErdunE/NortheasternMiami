package recipe;

import inventory.Ingredient;

import java.util.Map;

/**
 * WMC
 * 5, constructor, getName, getInstructions, getIngredients, setInstructions
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 0
 * RFC
 * 5
 * 5 internal. constructor, getName, getInstructions, getIngredients, setInstructions
 * 0 external
 * LCOM
 * Low, all methods relate to recipe management, make the class cohesive
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
