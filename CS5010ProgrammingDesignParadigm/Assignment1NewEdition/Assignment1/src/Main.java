public class Main {
    public static void main(String[] args) {

        AdventureModel model = new AdventureModel();
        AdventureView view = new AdventureView();
        AdventureController controller = new AdventureController();

        controller.run();

    }
}
