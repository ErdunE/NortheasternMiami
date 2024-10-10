package production;

import recipe.Recipe;

/**
 * WMC
 * 6, getBatchID, getRecipe, getSize, getStatus, updateStatus, constructor
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 1, Recipe
 * RFC
 * 6
 * 6 internal, Constructor， getBatchID, getRecipe, getSize, getStatus, updateStatus
 * 0 external
 * LCOM
 * Low, all methods work with batch-related data (ID, recipe, size, status),
 * making the class cohesive.
 */

public class Batch {
    private int batchID;
    private Recipe recipe;
    private int size;
    private String status;

    public Batch(int batchID, Recipe recipe, int size) {
        this.batchID = batchID;
        this.recipe = recipe;
        this.size = size;
        this.status = "Production is in processing.";
    }

    public int getBatchID() {
        return batchID;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public int getSize() {
        return size;
    }
    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

}
