package production;

import recipe.Recipe;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a batch of beer being produced in the brewery.
 * <p>
 * Each batch is has a specific recipe and a unique batch ID, a size means
 * the number of units being produced, and a status is  the current stage
 * of production. It also allows the batch status to be updated during
 * different stages of production.
 * </p>
 *
 * @author Erdun E
 * @version 1.4
 * @since 10/15/2024
 */

public class Batch {
    private int batchID;
    private Recipe recipe;
    private int size;
    private String status;
    private List<Double> gravityReadings;

    /**
     * Constructor for Batch class.
     * The initial status of the batch is set to "In Production".
     *
     * @param batchID The unique identifier for the batch.
     * @param recipe The Recipe associated with this batch, describing the ingredients and process.
     * @param size The size of the batch, means the number of units being produced.
     */
    public Batch(int batchID, Recipe recipe, int size) {
        this.batchID = batchID;
        this.recipe = recipe;
        this.size = size;
        this.status = "In Production";
        this.gravityReadings = new ArrayList<>();
    }

    /**
     * Returns the unique identifier for the batch.
     *
     * @return The batch ID.
     */
    public int getBatchID() {
        return batchID;
    }

    /**
     * Returns the recipe associated with this batch.
     *
     * @return The Recipe for this batch.
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Returns the size of the batch.
     *
     * @return The size of the batch.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the current status of the batch. such as "In Production", "Completed"
     *
     * @return The current status of the batch.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the status of the batch.
     *
     * @param status The new status for the batch.
     */
    public void updateStatus(String status) {
        this.status = status;
    }

    /**
     * Adds a gravity reading to the batch's history.
     *
     * @param reading The gravity reading to add.
     */
    public void addGravityReading(double reading) {
        gravityReadings.add(reading);
    }

}