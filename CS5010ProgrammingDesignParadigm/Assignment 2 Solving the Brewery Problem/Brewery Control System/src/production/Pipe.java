package production;

/**
 * The Pipe class represents a connection between two vats in the brewery production system.
 * This pipe is used to transfer batches between vats during production.
 *
 * @author Erdun
 * @version 1.3
 * @since 10/17/2024
 */

public class Pipe {
    private final Vat sourceVat;
    private final Vat destinationVat;
    private boolean isOpen;

    /**
     * Constructor for Pipe class
     *
     * @param source The source vat where the batch transfer originates.
     * @param destination The destination vat where the batch transfer ends.
     */
    public Pipe(Vat source, Vat destination) {
        this.sourceVat = source;
        this.destinationVat = destination;
        this.isOpen = false;
    }

    /**
     * Opens the pipe, allowing batch transfer between the source vat and the destination vat.
     * The status is updated to "Pipeline Opened".
     */
    public void openPipe(){
        this.isOpen = true;
        System.out.println("Pipe opened between Vat " + sourceVat.getId() + " and Vat " + destinationVat.getId());
    }

    /**
     * Closes the pipe, stopping batch transfer between the source vat and the destination vat.
     * The status is updated to "Pipeline Closed".
     */
    public void closePipe(){
        this.isOpen = false;
        System.out.println("Pipe closed.");
    }

    /**
     * Checks if the pipe is open.
     *
     * @return true if the pipe is open, false otherwise.
     */
    public boolean isOpen() {
        return isOpen;
    }

}
