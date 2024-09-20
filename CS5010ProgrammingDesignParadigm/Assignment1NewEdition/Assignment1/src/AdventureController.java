import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AdventureController {
    private final AdventureModel adventureModel;
    private final AdventureView adventureView;
    private final Scanner scanner;

    public AdventureController(AdventureModel model, AdventureView view) {
        this.adventureModel = new AdventureModel();
        this.adventureView = new AdventureView();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        adventureView.welcomeMessage();
        String userName = scanner.nextLine();
        adventureView.backgroundMessage(userName);
        // sleepForUserRead(10000);
        adventureView.introMessage();
        // sleepForUserRead(20000);
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
                    // sleepForUserRead(5000);
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

    private void handleInput(String input) {

        String[] words = preprocessUserInput(input);

        if (words[0] == null || words[1] == null) {
            adventureView.displayError("I couldn't quite catch that. You need to specify both an action and an object.");
            return;
        }

        AdventureModel.Verbs verb;
        AdventureModel.Nouns noun;

        try {
            verb = AdventureModel.Verbs.valueOf(words[0]);
            noun = AdventureModel.Nouns.valueOf(words[1]);
        } catch (IllegalArgumentException e) {
            adventureView.displayError("Hmm, I'm not sure what you meant. Make sure you're using the right words.");
            return;
        }

        if(AdventureModel.isValidAction(verb, noun)){
            executeAction(verb, noun);
        }else{
            provideGuidance(verb, noun);
        }
    }
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
    private boolean isVerb(String word){
        try{
            AdventureModel.Verbs.valueOf(word.toUpperCase());
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
    private boolean isNoun(String word){
        try{
            AdventureModel.Nouns.valueOf(word.toUpperCase());
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
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
                break;
        }
    }
    private void provideGuidance(AdventureModel.Verbs verb, AdventureModel.Nouns noun) {
        String guidance = null;
        boolean leftHome = adventureModel.isOnRoad() || adventureModel.isArrivedSchool();
        switch (verb) {
            case EAT:
                if(noun == AdventureModel.Nouns.BREAKFAST){
                    if(!adventureModel.isLightOn()){
                        guidance = "It's too dark to see, how about try to turn on the light before having breakfast?";
                    } else if (leftHome) {
                        guidance = "You've already had breakfast, and you're feeling full right now.";
                    } else if (adventureModel.isEaten()) {
                        guidance = "You've already had breakfast, how about try to grab the stuffs and take shoes?";
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
                            missingItems += "bag";
                        }
                        if(!adventureModel.isTakenLaptop()){
                            missingItems += "laptop";
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
                            missingItems += "bag";
                        }
                        if(!adventureModel.isTakenWater()){
                            missingItems += "water";
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
                            missingItems += "bag";
                        }
                        if(!adventureModel.isTakenLaptop()){
                            missingItems += "laptop";
                        }
                        if(!adventureModel.isTakenWater()){
                            missingItems += "water";
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
        if (guidance != null) {
            adventureView.displayGuidance(guidance);
        } else {
            adventureView.displayError("Sorry, the object " + noun + " is not valid with the action " + verb + " in this adventure. Please try to other objects.");
        }
    }
    private void sleepForUserRead(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            System.err.println("Sleep interrupted" + e.getMessage());
        }
    }
}