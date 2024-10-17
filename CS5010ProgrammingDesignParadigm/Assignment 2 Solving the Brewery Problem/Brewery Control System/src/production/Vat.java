package production;

/**
 * The Vat class represents a storage container in the Brewery Control System.
 * Vats are used to store or process beverage batches during production. Each
 * vat has a unique ID, a specific capacity, and a status indicating whether
 * it is empty or in use.
 *
 * @author Erdun
 * @version 1.3
 * @since 10/17/2024
 */
public class Vat {
    private int vatId;
    private int capacity;
    private String status;

    /**
     * Constructor for Vat class.
     *
     * @param vatId unique identifier for the vat.
     * @param capacity The total capacity of the vat.
     */
    public Vat(int vatId, int capacity) {
        this.vatId = vatId;
        this.capacity = capacity;
        this.status = "This Vat is Empty.";
    }

    /**
     * Returns the Vat id.
     *
     * @return The ID of the vat.
     */
    public int getId() {
        return vatId;
    }

    /**
     * Returns the capacity of the vat.
     *
     * @return The capacity of the vat.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the current status of the vat.
     *
     * @return The current status of the vat.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the status of the vat to a new value.
     *
     * @param status The new status to set for the vat (e.g., "In Use", "Cleaned", "Empty").
     */
    public void updateStatus(String status) {
        this.status = status;
    }
}
