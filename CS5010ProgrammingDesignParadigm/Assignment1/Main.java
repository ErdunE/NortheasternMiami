public class Main {

    public static void main(String[] args) {
        // create each class of the MVC
        AdventureModel model = new AdventureModel();
        AdventureView view = new AdventureView();
        AdventureController controller = new AdventureController(model, view, System.in);

        // kick off the Adventure
        controller.createAdventure();
    }
}
