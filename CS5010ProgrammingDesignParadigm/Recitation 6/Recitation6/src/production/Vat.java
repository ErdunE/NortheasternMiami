package production;

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
