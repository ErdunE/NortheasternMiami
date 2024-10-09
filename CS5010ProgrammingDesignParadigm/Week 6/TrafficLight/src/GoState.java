public class GoState implements TrafficLightState {
    @Override
    public void handleRequest() {
        System.out.println("Go! The light is green!");
    }
}
