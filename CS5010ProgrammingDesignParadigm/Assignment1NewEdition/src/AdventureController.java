import java.util.Scanner;

/**
 * @author Erdun E
 * @version 1.1
 * @since 2024-09-29
 * Course: CS5010 Programming Design Paradigm
 * Program: Assignment 1
 *
 * AdventureController class is responsible for handling the user inputs and interacting with
 * the AdventureModel and AdventureView to execute the game logic and display results.
 * This class processes user actions (verbs and nouns) and determines the game's response
 * based on the current state stored in the AdventureModel.
 */

public class AdventureController {
    private final AdventureModel adventureModel;
    private final AdventureView adventureView;
    private final Scanner scanner;

    public AdventureController(AdventureModel model, AdventureView view) {
        this.adventureModel = new AdventureModel();
        this.adventureView = new AdventureView();
        this.scanner = new Scanner(System.in);
    }

    // This method runs the program as expected
    // So user could get the intended result
    // before each step, there has sleep time for reading context
    public void run() {
        adventureView.welcomeMessage();
        String userName = scanner.nextLine();
        adventureView.backgroundMessage(userName);
        sleepForUserRead(5000);
        adventureView.introMessage();
        sleepForUserRead(10000);
        adventureView.askUserIfStart();
        boolean start = false;
        while (!start) {
            String startButton = scanner.nextLine().toLowerCase();
            if (startButton.equals("start")) {
                while(!adventureModel.isArrivedSchool()){
                    adventureView.displayCurrentScenarioState(adventureModel);
                    adventureView.displayAvailableActions();
                    String input = scanner.nextLine().toLowerCase();
                    if(input.equals("quit")){
                        adventureView.exitMessage();
                    }
                    handleInput(input);
                    sleepForUserRead(2000);
                }
                start = true;
            }else{
                if(startButton.equals("quit")){
                    adventureView.exitMessage();
                }
                System.out.println("Invalid input. Try to enter 'start' to begin your journey again.");
                System.out.println("Or enter 'quit' to quit.");
            }
        }
    }

    // This method to handle the user's input, and get back to user
    private void handleInput(String input) {
        // Preprocess input to recognize potential verbs and nouns
        String[] words = preprocessUserInput(input);

        // Return error message if the verb or noun or all are missing
        if (words[0] == null || words[1] == null) {
            adventureView.displayError("I couldn't quite catch that. You need to specify both an action and an object.");
            return;
        }

        // Validate user input or return error
        AdventureModel.Verbs verb;
        AdventureModel.Nouns noun;
        try {
            verb = AdventureModel.Verbs.valueOf(words[0]);
            noun = AdventureModel.Nouns.valueOf(words[1]);
        } catch (IllegalArgumentException e) {
            adventureView.displayError("Hmm, I'm not sure what you meant. Make sure you're using the right words.");
            return;
        }

        // Check if action is appropriate and provide guidance if not
        if(AdventureModel.isValidAction(verb, noun)){
            executeAction(verb, noun);
        }else{
            provideGuidance(verb, noun);
        }
    }

    // This method to extract verbs and nouns from user input
    private String[] preprocessUserInput(String input){
        input = input.toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
        String[] words = input.split("\\s+");

        String verb = null;
        String noun = null;

        for(String word : words){
            try{
                if (verb == null && isVerb(word)) {
                    verb = word.toUpperCase();
                } else if (noun == null && isNoun(word)) {
                    noun = word.toUpperCase();
                }
            }catch(IllegalArgumentException e){

            }
        }
        return new String[]{verb, noun};
    }

    // Determine if it is a valid verb
    private boolean isVerb(String word){
        try{
            AdventureModel.Verbs.valueOf(word.toUpperCase());
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }

    // Determine if it is a valid noun
    private boolean isNoun(String word){
        try{
            AdventureModel.Nouns.valueOf(word.toUpperCase());
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }

    // When the user input and judged valid and successful, get back to user
    private void executeAction(AdventureModel.Verbs verb, AdventureModel.Nouns noun) {
        switch (verb) {
            case EAT:
                adventureModel.setEaten(true);
                adventureView.displaySuccess("You enjoyed the sandwich, so now, feel ready to start your day.");
                break;
            case TAKE:
                switch (noun) {
                    case BAG:
                        adventureModel.setTakenBag(true);
                        adventureView.displaySuccess("You picked up your bag. Don't forget anything else!");
                        break;
                    case SHOES:
                        adventureModel.setTakenShoes(true);
                        adventureView.displaySuccess("You put on the AJ1. You're almost ready to leave.");
                        break;
                    case WATER:
                        adventureModel.setTakenWater(true);
                        adventureView.displaySuccess("You grabbed your water bottle. Stay hydrated!");
                        break;
                    case LAPTOP:
                        adventureModel.setTakenLaptop(true);
                        adventureView.displaySuccess("You took your laptop, the most thing for CS Student.");
                        break;
                }
                break;
            case OPEN:
                adventureModel.setLightOn(true);
                adventureView.displaySuccess("You turned on the light. The room brightens up now.");
                break;
            case WALK:
                adventureModel.setOnRoad(true);
                adventureView.displaySuccess("You step outside and start walking towards campus.");
                break;
            case ARRIVE:
                adventureModel.setArrivedSchool(true);
                adventureView.displaySuccess("You arrived the campus, and seat in the CS5010 classroom on time, adventure ends!");
                adventureView.congratsImage();
                break;
        }
    }

    // When the user input and judged valid but unsuccessful, get guidance back to user
    private void provideGuidance(AdventureModel.Verbs verb, AdventureModel.Nouns noun) {
        String guidance = null;
        // Check if the user is on the road or at school
        boolean leftHome = adventureModel.isOnRoad() || adventureModel.isArrivedSchool();

        switch (verb) {
            case EAT:
                // User input verb and noun is validated but logic wrong
                if(noun == AdventureModel.Nouns.BREAKFAST){
                    // Check if light on
                    if(!adventureModel.isLightOn()){
                        guidance = "It's too dark to see, how about try to turn on the light before having breakfast?";
                    // Check if left home
                    } else if (leftHome) {
                        guidance = "You've already had breakfast, and you're feeling full right now.";
                    // Check if eat breakfast already
                    } else if (adventureModel.isEaten()) {
                        guidance = "You've already had breakfast, how about try to grab the stuffs and take shoes?";
                    // In other cases, first guide the user to eat breakfast first
                    } else {
                        guidance = "You're feeling hungry, how about enjoy your breakfast first?";
                    }
                }
                break;
            case TAKE:
                if(noun == AdventureModel.Nouns.BAG){
                    if(!adventureModel.isLightOn()){
                        guidance = "It's too dark to see, how about turn on the light before grabbing your bag?";
                    } else if (!adventureModel.isEaten()) {
                        guidance = "You are hungry now, how about have breakfast before taking your bag and leaving?";
                    } else if (!adventureModel.isTakenWater() || !adventureModel.isTakenLaptop()) {
                        String missingItems = "";
                        if(!adventureModel.isTakenWater()){
                            missingItems += "water ";
                        }
                        if(!adventureModel.isTakenLaptop()){
                            missingItems += "laptop ";
                        }
                        guidance = "Before leaving, make sure to take your " + missingItems;
                    } else if (!adventureModel.isTakenShoes()) {
                        guidance = "Don't forget put your shoes on before leave home.";
                    } else if (adventureModel.isTakenBag()) {
                        guidance = "You double-checked to be sure you had your bag with you. Yes, you did.";
                    }
                } else if (noun == AdventureModel.Nouns.SHOES) {
                    if(!adventureModel.isLightOn()){
                        guidance = "It's too dark to see, how about turn on the light before wear shoes?";
                    } else if (!adventureModel.isEaten()) {
                        guidance = "You are hungry now, how about have breakfast before wear shoes?";
                    } else if (adventureModel.isTakenShoes()) {
                        guidance = "Your new AJ1 is on your foot.";
                    }
                } else if (noun == AdventureModel.Nouns.WATER) {
                    if(!adventureModel.isLightOn()){
                        guidance = "It's too dark to see, how about turn on the light before grabbing the water bottle?";
                    } else if (!adventureModel.isEaten()) {
                        guidance = "You are hungry now, how about have breakfast before grabbing the water bottle and leaving?";
                    } else if (!adventureModel.isTakenBag() || !adventureModel.isTakenLaptop()) {
                        String missingItems = "";
                        if(!adventureModel.isTakenBag()){
                            missingItems += "bag ";
                        }
                        if(!adventureModel.isTakenLaptop()){
                            missingItems += "laptop ";
                        }
                        guidance = "Before leaving, make sure to take your " + missingItems;
                    } else if (!adventureModel.isTakenShoes()) {
                        guidance = "Don't forget put your shoes on before leave home.";
                    } else if (adventureModel.isTakenWater()) {
                        guidance = "You double-checked to be sure you had your water bottle with you. Yes, you did.";
                    }
                } else if (noun == AdventureModel.Nouns.LAPTOP) {
                    if(!adventureModel.isLightOn()){
                        guidance = "It's too dark to see, how about turn on the light before grabbing the laptop?";
                    } else if (!adventureModel.isEaten()) {
                        guidance = "You are hungry now, how about have breakfast before grabbing the laptop and leaving?";
                    } else if (!adventureModel.isTakenBag() || !adventureModel.isTakenWater()) {
                        String missingItems = "";
                        if(!adventureModel.isTakenBag()){
                            missingItems += "bag ";
                        }
                        if(!adventureModel.isTakenWater()){
                            missingItems += "water ";
                        }
                        guidance = "Before leaving, make sure to take your " + missingItems;
                    } else if (!adventureModel.isTakenShoes()) {
                        guidance = "Don't forget put your shoes on before leave home.";
                    } else if (adventureModel.isTakenLaptop()) {
                        guidance = "You double-checked to be sure you had your laptop with you. Yes, you did.";
                    }
                }
                break;
            case OPEN:
                if (noun == AdventureModel.Nouns.LIGHT) {
                    if(!adventureModel.isLightOn()){
                        guidance = "It's too dark around, how about try to open the light?";
                    } else if (leftHome) {
                        guidance = "You forgot to turn off the light, but it's too late to go back now.";
                    } else if (adventureModel.isLightOn()) {
                        guidance = "The light has been turned on.";
                    }
                }
                break;
            case WALK:
                if(noun == AdventureModel.Nouns.ROAD){
                    if(!adventureModel.isLightOn()){
                        guidance = "It's too dark to see, how about turn on the light before heading out";
                    } else if (!adventureModel.isEaten()) {
                        guidance = "You are hungry now, how about have breakfast before heading out?";
                    } else if (!adventureModel.isTakenBag() || !adventureModel.isTakenLaptop() || !adventureModel.isTakenWater()) {
                        String missingItems = "";
                        if(!adventureModel.isTakenBag()){
                            missingItems += "bag ";
                        }
                        if(!adventureModel.isTakenLaptop()){
                            missingItems += "laptop ";
                        }
                        if(!adventureModel.isTakenWater()){
                            missingItems += "water ";
                        }
                        guidance = "Before heading out, make sure to take your " + missingItems;
                    } else if (!adventureModel.isTakenShoes()) {
                        guidance = "Don't forget put your shoes on before heading out.";
                    } else if (adventureModel.isOnRoad()){
                        guidance = "You've been reached to campus.";
                    }
                }
                break;
            case ARRIVE:
                if (noun == AdventureModel.Nouns.SCHOOL) {
                    if(!adventureModel.isLightOn()){
                        guidance = "It's too dark to see, how about turn on the light before heading out";
                    } else if (!adventureModel.isEaten()) {
                        guidance = "You are hungry now, how about have breakfast before heading out?";
                    } else if (!adventureModel.isTakenBag() || !adventureModel.isTakenLaptop() || !adventureModel.isTakenWater()) {
                        String missingItems = "";
                        if(!adventureModel.isTakenBag()){
                            missingItems += "bag, ";
                        }
                        if(!adventureModel.isTakenLaptop()){
                            missingItems += "laptop, ";
                        }
                        if(!adventureModel.isTakenWater()){
                            missingItems += "water";
                        }
                        guidance = "Before heading out, make sure to take your " + missingItems + ".";
                    } else if (!adventureModel.isTakenShoes()) {
                        guidance = "Don't forget put your shoes on before heading out.";
                    } else if (!adventureModel.isOnRoad()) {
                        guidance = "You haven't left home yet.";
                    }
                }
                break;
        }
        // If there have guidance, return the guidance, if not e.g eat light, return the verb and noun is invalid
        if (guidance != null) {
            adventureView.displayGuidance(guidance);
        } else {
            adventureView.displayError("Sorry, the object " + noun + " is not valid with the action " + verb + " in this adventure. Please try to other objects.");
        }
    }

    // Set up sleep time for user reading context
    private void sleepForUserRead(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            System.err.println("Sleep interrupted" + e.getMessage());
        }
    }
}