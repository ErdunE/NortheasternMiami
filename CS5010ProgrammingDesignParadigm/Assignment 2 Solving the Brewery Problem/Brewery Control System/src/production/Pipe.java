package production;

/**
 * The Pipe class represents a connection between two vats in the brewery production system.
 * This pipe is used to transfer batches between vats during production.
 *
 * @author Erdun
 * @version 1.2
 * @since 10/15/2024
 */

public class Pipe {
    private Vat sourceVat;
    private Vat destinationVat;
    private String status;

    /**
     * Constructor for Pipe class
     *
     * @param source The source vat where the batch transfer originates.
     * @param destination The destination vat where the batch transfer ends.
     */
    public Pipe(Vat source, Vat destination) {
        this.sourceVat = source;
        this.destinationVat = destination;
        this.status = "Pipe line Closed";
    }

    /**
     * Returns the source vat of this pipe, which is the vat where the batch transfer begins.
     *
     * @return The source vat.
     */
    public Vat getSourceVat() {
        return sourceVat;
    }

    /**
     * Returns the destination vat of this pipe, which is the vat where the batch transfer ends.
     *
     * @return The destination vat.
     */
    public Vat getDestinationVat() {
        return destinationVat;
    }

    /**
     * Returns the current status of this pipe that opened or closed.
     *
     * @return The status of the pipe.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the status of the pipe.
     *
     * @param status The new status for the pipe.
     */
    public void updateStatus(String status) {
        this.status = status;
    }

    /**
     * Opens the pipe, allowing batch transfer between the source vat and the destination vat.
     * The status is updated to "Pipe line Opened".
     */
    public void openPipe(){
        this.status = "Pipe line Opened";
    }

    /**
     * Closes the pipe, stopping batch transfer between the source vat and the destination vat.
     * The status is updated to "Pipe line Closed".
     */
    public void closePipe(){
        this.status = "Pipe line Closed";
    }
}
