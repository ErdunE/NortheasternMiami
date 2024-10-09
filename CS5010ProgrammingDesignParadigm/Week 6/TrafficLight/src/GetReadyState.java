public class GetReadyState implements TrafficLightState {
    @Override
    public void handleRequest() {
        System.out.println("Get Ready! The light is yellow!");
    }
}
