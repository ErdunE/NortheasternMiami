package production;

/**
 *
 * @author Erdun E
 * @version 09/26/2024
 *
 * Represents a pipe connecting vats for transferring beers.
 *
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
