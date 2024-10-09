public class TrafficeLightContext {
    // TrafficLightState

    private TrafficLightState state;


    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void request(){
        this.state.handleRequest();
    }
}
