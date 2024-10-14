package production;

/**
 * WMC
 * 6, getSourceVat, getDestinationVat, getStatus, setStatus, openPipe, closePipe
 * DIT
 * 1
 * NOC
 * 0
 * CBO
 * 0
 * RFC
 * 6
 * 6 internal constructor, getSourceVat, getDestinationVat, getStatus, setStatus,
 * openPipe, closePipe
 * 0 external
 * LCOM
 * Low, all methods work together to manage the pipe's state and operations.
 */

public class Pipe {
    private Vat sourceVat;
    private Vat destinationVat;
    private String status;

    public Pipe(Vat source, Vat destination) {
        this.sourceVat = source;
        this.destinationVat = destination;
        this.status = "Pipe line Closed";
    }

    public Vat getSourceVat() {
        return sourceVat;
    }
    public Vat getDestinationVat() {
        return destinationVat;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void openPipe(){
        this.status = "Pipe line Opened";
    }
    public void closePipe(){
        this.status = "Pipe line Closed";
    }
}
