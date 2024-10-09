public class StopState implements TrafficLightState{
    @Override
    public void handleRequest() {
        System.out.println("Stop! The light is read!");
    }
}
