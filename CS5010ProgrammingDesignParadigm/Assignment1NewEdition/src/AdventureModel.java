/**
 * @author Erdun E
 * Date:Sep 20th, 2024
 * Course: CS5010 Programming Design Paradigm
 * Program: Assignment 1
 *
 * Model for Assignment 1, include storage of Verb and Noun, and program logic and states.
 *
 */
public class AdventureModel {

    // Verb and Noun library
    public enum Verbs {
        EAT, TAKE, OPEN, WALK, ARRIVE
    }
    public enum Nouns {
        BREAKFAST, LIGHT, BAG, ROAD, SCHOOL, WATER, SHOES, LAPTOP
    }

    // Scenario state and adventure logic
    private static boolean isLightOn;
    private static boolean isEaten;
    private static boolean isTakenBag;
    private static boolean isOnRoad;
    private static boolean isTakenWater;
    private static boolean isTakenShoes;
    private static boolean isTakenLaptop;
    private boolean isArrivedSchool;

    public AdventureModel(){
        isLightOn = false;
        isEaten = false;
        isTakenBag = false;
        isOnRoad = false;
        isArrivedSchool = false;
        isTakenWater = false;
        isTakenShoes = false;
        isTakenLaptop = false;
    }

    // Determine the current scenario state
    public boolean isLightOn() {
        return isLightOn;
    }
    public boolean isEaten() {
        return isEaten;
    }
    public boolean isTakenBag() {
        return isTakenBag;
    }
    public boolean isOnRoad() {
        return isOnRoad;
    }
    public boolean isTakenWater() {
        return isTakenWater;
    }
    public boolean isTakenShoes() {
        return isTakenShoes;
    }
    public boolean isTakenLaptop() {
        return isTakenLaptop;
    }
    public boolean isArrivedSchool() {
        return isArrivedSchool;
    }

    // Update the current scenario state
    public void setLightOn(boolean lightOn) {
        isLightOn = lightOn;
    }
    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }
    public void setTakenBag(boolean takenBag) {
        isTakenBag = takenBag;
    }
    public void setOnRoad(boolean onRoad) {
        isOnRoad = onRoad;
    }
    public void setTakenWater(boolean takenWater) {
        isTakenWater = takenWater;
    }
    public void setTakenShoes(boolean takenShoes) {
        isTakenShoes = takenShoes;
    }
    public void setTakenLaptop(boolean takenLaptop) {
        isTakenLaptop = takenLaptop;
    }
    public void setArrivedSchool(boolean arrivedSchool) {
        isArrivedSchool = arrivedSchool;
    }

    // Determine the user action is valid on current state
    public static boolean isValidAction(Verbs verb, Nouns noun){
        switch(verb){
            case EAT:
                // Must turn on light before eat breakfast
                if(!isLightOn){
                    return false;
                }
                return noun == Nouns.BREAKFAST && !isEaten;
            case TAKE:
                // Must turn on light before take bag, shoes, water, and laptop
                if(!isLightOn){
                    return false;
                }
                // Must eat breakfast before take bag, shoes, water, and laptop
                if(!isEaten){
                    return false;
                }
                switch (noun){
                    case BAG:
                        return !isTakenBag;
                    case SHOES:
                        return !isTakenShoes;
                    case WATER:
                        return !isTakenWater;
                    case LAPTOP:
                        return !isTakenLaptop;
                    default: return false;
                }
            case OPEN:
                return noun == Nouns.LIGHT && !isLightOn;
            case WALK:
                // Walk road must before eat breaking and take everything
                return noun == Nouns.ROAD && isTakenBag && isEaten && isTakenShoes && isTakenLaptop && isTakenWater;
            case ARRIVE:
                // Arrive road must after walk road
                return noun == Nouns.SCHOOL && isOnRoad;
            default:
                return false;
        }
    }
}