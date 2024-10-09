//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TrafficeLightContext context = new TrafficeLightContext();

        context.setState(new StopState());
        context.request();

        context.setState(new GetReadyState());
        context.request();

        context.setState(new GoState());
        context.request();
    }
}