import java.util.Scanner;

public class AdventureView {

    // display current scenario state base on different situation
    public void displayCurrentScenarioState(AdventureModel model){
        if(!model.isLightOn()){
            System.out.println("-------------------------------------------------------------------");
            System.out.println("It's pretty dark in the room, you can't see anything.");
            System.out.println("You might want to turn on the light to see better.");
        } else if (!model.isEaten()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("You haven't had breakfast yet. It's a good idea to eat something before heading out.");
            System.out.println("There's a sandwich on the table that looks delicious.");
        } else if (!model.isTakenBag()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Don't forget to grab your school bag.");
            System.out.println(":) You are student");
        } else if (!model.isTakenLaptop()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("You need to grab laptop before leaving.");
            System.out.println("You can't do nothing without laptop, since you are CS Major");
        } else if (!model.isTakenShoes()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("You should put on your shoes before you leave.");
            System.out.println("Your new AJ1 looks great.");
        } else if (!model.isTakenWater()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("You might want to take some water with you.");
            System.out.println("As we know, it's too hot in Miami. People need to stay hydrated.");
        } else if (!model.isOnRoad()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Let's finally check what we're going to use for school, bags, laptop, water. Shoes have been put on too.");
            System.out.println("You are all set! Ready to head out on the road to school?");
        } else if (!model.isArrivedSchool()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("You can already see the campus faintly.");
            System.out.println("Almost there! Just a bit more walking to reach school.");
        }
    }

    // display available actions to user
    public void displayAvailableActions(){
        System.out.println("What would you like to do next? You can:");
        System.out.println("********************************************************************");
        System.out.println("*Actions: EAT, TAKE, OPEN, WALK, ARRIVE                            *");
        System.out.println("*Objects: BREAKFAST, LIGHT, BAG, ROAD, SCHOOL, WATER, SHOES, LAPTOP*");
        System.out.println("*For example, you could say 'I would like to open the light first' *");
        System.out.println("*You can type 'quit' to Quit Adventure anytime                     *");
        System.out.println("********************************************************************");
    }



    // Display success message
    public void displaySuccess(String message) {
        System.out.println("Great! " + message);
    }

    // Display error message
    public void displayError(String message) {
        System.out.println("Oops! " + message);
        System.out.println("Try something like 'take water' or 'eat breakfast'.");
    }

    public void displayGuidance(String guidance) {
        System.out.println(guidance);
    }

}
