package production;

import recipe.Recipe;

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
