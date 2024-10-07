package production;

/**
 *
 * @author Erdun E
 * @version 09/26/2024
 *
 * Represents a vat used for holding batches during the production process.
 *
 */

public class Vat {
    private int batchId;
    private int capacity;
    private String status;

    public Vat(int batchId, int capacity) {
        this.batchId = batchId;
        this.capacity = capacity;
        this.status = "This Vat is Empty.";
    }

    public int getId() {
        return batchId;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
