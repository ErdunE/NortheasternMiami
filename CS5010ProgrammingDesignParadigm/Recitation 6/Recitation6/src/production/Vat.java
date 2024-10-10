package production;

/**
 * WMC
 * 5, Constructor, getID, getCapacity, getStatus, setStatus
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 0
 * RFC
 * 5
 * 5 internal, constructor, getId, getCapacity, getStatus, setStatus
 * 0 external
 * LCOM
 * Low, all methods relate to ma manging a vat, make the class cohesive
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
