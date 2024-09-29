/**
 * @author Erdun E
 * @version 1.1
 * @since 2024-09-29
 * Course: CS5010 Programming Design Paradigm
 * Program: Assignment 1
 *
 * AdventureView class represents the view in the MVC pattern.
 * It is responsible for displaying information to the player and gathering input from the player.
 * This class provides methods for message, states, error, success, guidance and exit etc.
 *
 */
public class AdventureView {

    // Welcome message, and ask user nickname to improve interaction
    public void welcomeMessage() {
        welcomeImage();
        System.out.println("Welcome to the Adventure of Go to Campus, before start the adventure, please enter your nickname:");
    }

    // Adventure background, who you are, where you are, and what is the goal, what is the challenge
    public void backgroundMessage(String userName){
        String background = "Hola! " + userName + ", you are a NEU MSCS student living in Miami right now. " +
                            "\nEvery Wednesday, your adventure begins at the comfort of your home, " +
                            "\nand through a series of challenges, you aim to reach your eagerly " +
                            "\nanticipated campus. Today, your goal is to arrive at campus on time, " +
                            "\nensuring you have everything you need. This is not just a regular " +
                            "\njourney, it’s an adventure filled with preparation, decision-making, " +
                            "\nand exploration.";
        System.out.println(background);
        System.out.println("-------------------------------------------------------------------");
    }

    // Adventure introduction, some basic logic to guide user
    public void introMessage() {
        System.out.println("The morning sunlight streams through the gaps in your curtains, and");
        System.out.println("your alarm clock rings, signaling the start of a new day. You get out");
        System.out.println("of bed, stretch, and realize you have a lot to do today. You know that");
        System.out.println("completing these tasks requires careful planning and precise actions.");
        System.out.println(" ");
        System.out.println("At home, you notice it’s a bit dark, you've used to the black clouds");
        System.out.println("in Miami, so you need to address this issue first.");
        System.out.println(" ");
        System.out.println("Breakfast is waiting for you in the kitchen, and it's essential to fuel");
        System.out.println("up before starting your day of learning. Only in a well-lit environment");
        System.out.println("can you efficiently prepare all your necessities. ");
        System.out.println(" ");
        System.out.println("Before you left home, you need to grab a backpack, a water bottle, a ");
        System.out.println("laptop, and put shoes. Each item is crucial for your successful departure.");
        System.out.println("You need to ensure that you have everything prepared before you can set ");
        System.out.println("out for campus.");
        System.out.println(" ");
        System.out.println("Once get ready, you’re finally ready to start your journey to campus. As");
        System.out.println("you step outside, you breathe in the fresh morning air. The view of the ");
        System.out.println("street becomes clearer, and you begin to see the outline of your school in ");
        System.out.println("the distance. ");
        System.out.println(" ");
        System.out.println("Your goal is to reach campus smoothly and be prepared for the day ahead. ");
        System.out.println("Every decision and action will impact your adventure progress. You must ");
        System.out.println("plan carefully to ensure you don’t forget any important items. Successfully ");
        System.out.println("reaching campus is not just a personal achievement; it’s a victory over the");
        System.out.println("challenges of starting each day.");
        System.out.println("-------------------------------------------------------------------");
    }

    // Ask user if would like to start the adventure
    public void askUserIfStart(){
        System.out.println("Are you ready to start the adventure???????");
        System.out.println("Please enter 'start' to begin your journey!!!");
    }

    // display current scenario state base on different situation
    public void displayCurrentScenarioState(AdventureModel model){
        System.out.println("-------------------------Current Scenario--------------------------");
        if(!model.isLightOn()){
            System.out.println("It's pretty dark in the room, you can't see anything.");
            System.out.println("You might want to turn on the light to see better.");
        } else if (!model.isEaten()) {
            System.out.println("You haven't had breakfast yet. It's a good idea to eat something before heading out.");
            System.out.println("There's a sandwich on the table that looks delicious.");
        } else if (!model.isTakenBag()) {
            System.out.println("Don't forget to grab your school bag before you heading out.");
        } else if (!model.isTakenLaptop()) {
            System.out.println("You need to grab laptop before heading out.");
            System.out.println("You can't do nothing without laptop, since you are CS Major");
        } else if (!model.isTakenShoes()) {
            System.out.println("You should put on your shoes before you leave.");
            System.out.println("Your new AJ1 looks great.");
        } else if (!model.isTakenWater()) {
            System.out.println("You might want to take some water with you.");
            System.out.println("As we know, it's too hot in Miami. People need to stay hydrated.");
        } else if (!model.isOnRoad()) {
            System.out.println("Let's finally check what we're going to use for school, bags, laptop, water. Shoes have been put on too.");
            System.out.println("You are all set! Ready to head out on the road to school?");
        } else if (!model.isArrivedSchool()) {
            System.out.println("You can already see the campus faintly.");
            System.out.println("Almost there! Just a bit more walking to reach school.");
        }
    }

    // display available actions to user
    public void displayAvailableActions(){
        System.out.println("What would you like to do next? You can:");
        System.out.println("****************************Menu Bar********************************");
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

    // Display guidance message
    public void displayGuidance(String guidance) {
        System.out.println(guidance);
    }

    // Display exit message and exit
    public void exitMessage() {
        System.out.println("Goodbye! Adventure Exited.");
        goodbyeImage();
        System.exit(0);
    }

    // Display ASCII art “welcome”
    public void welcomeImage(){
        String[] w = {
                "*       * ",
                "*       * ",
                "*   *   * ",
                "*  * *  * ",
                "* *   * * ",
                "**     ** "
        };
        String[] e = {
                "******* ",
                "*       ",
                "*****   ",
                "*       ",
                "*       ",
                "******* "
        };
        String[] l = {
                "*       ",
                "*       ",
                "*       ",
                "*       ",
                "*       ",
                "******* "
        };
        String[] c = {
                "  *****  ",
                " *       ",
                "*        ",
                "*        ",
                " *       ",
                "  *****  "
        };
        String[] o = {
                "  *****  ",
                " *     * ",
                "*       *",
                "*       *",
                " *     * ",
                "  *****  "
        };
        String[] m = {
                "*     * ",
                "**   ** ",
                "* * * * ",
                "*  *  * ",
                "*     * ",
                "*     * "
        };
        String[] exclamation = {
                "  *  ",
                "  *  ",
                "  *  ",
                "  *  ",
                "     ",
                "  *  "
        };

        for(int i = 0; i < 6; i ++){
            System.out.print(w[i] + " ");
            System.out.print(e[i] + " ");
            System.out.print(l[i] + " ");
            System.out.print(c[i] + " ");
            System.out.print(o[i] + " ");
            System.out.print(m[i] + " ");
            System.out.print(e[i] + " ");
            System.out.print(exclamation[i]);
            System.out.println();
        }
    }
    // Display ASCII art “goodbye”
    public void goodbyeImage(){
        String[] g = {
                "  *****  ",
                " *       ",
                "*        ",
                "*   ***  ",
                " *    *  ",
                "  *****  "
        };

        String[] o = {
                "  *****  ",
                " *     * ",
                "*       *",
                "*       *",
                " *     * ",
                "  *****  "
        };

        String[] d = {
                "*****   ",
                "*    *  ",
                "*     * ",
                "*     * ",
                "*    *  ",
                "*****   "
        };

        String[] b = {
                "*****   ",
                "*    *  ",
                "*    *  ",
                "*****   ",
                "*    *  ",
                "*****   "
        };

        String[] y = {
                "*     * ",
                " *   *  ",
                "  * *   ",
                "   *    ",
                "   *    ",
                "   *    "
        };

        String[] e = {
                "******* ",
                "*       ",
                "*****   ",
                "*       ",
                "*       ",
                "******* "
        };

        String[] exclamation = {
                "  *  ",
                "  *  ",
                "  *  ",
                "  *  ",
                "     ",
                "  *  "
        };

        for(int i = 0; i < 6; i ++){
            System.out.print(g[i] + " ");
            System.out.print(o[i] + " ");
            System.out.print(o[i] + " ");
            System.out.print(d[i] + " ");
            System.out.print(b[i] + " ");
            System.out.print(y[i] + " ");
            System.out.print(e[i] + " ");
            System.out.print(exclamation[i]);
            System.out.println();
        }
    }
    // Display ASCII art “congrats”
    public void congratsImage(){
        String[] c = {
                "  *****  ",
                " *     * ",
                "*        ",
                "*        ",
                "*        ",
                " *     * ",
                "  *****  "
        };

        String[] o = {
                "  *****  ",
                " *     * ",
                "*       *",
                "*       *",
                "*       *",
                " *     * ",
                "  *****  "
        };

        String[] n = {
                "*     * ",
                "**    * ",
                "* *   * ",
                "*  *  * ",
                "*   * * ",
                "*    ** ",
                "*     * "
        };

        String[] g = {
                "  *****  ",
                " *     * ",
                "*        ",
                "*   **** ",
                "*      * ",
                " *     * ",
                "  *****  "
        };

        String[] r = {
                "*****   ",
                "*    *  ",
                "*    *  ",
                "*****   ",
                "*   *   ",
                "*    *  ",
                "*     * "
        };

        String[] a = {
                "   *    ",
                "  * *   ",
                " *   *  ",
                "******* ",
                "*     * ",
                "*     * ",
                "*     * "
        };

        String[] t = {
                "*******",
                "   *   ",
                "   *   ",
                "   *   ",
                "   *   ",
                "   *   ",
                "   *   "
        };

        String[] s = {
                " ***** ",
                "*      ",
                "*      ",
                " ***** ",
                "      *",
                "      *",
                " ***** "
        };

        String[] exclamation = {
                "  *  ",
                "  *  ",
                "  *  ",
                "  *  ",
                "  *  ",
                "     ",
                "  *  "
        };

        for(int i = 0; i < 7; i ++){
            System.out.print(c[i] + " ");
            System.out.print(o[i] + " ");
            System.out.print(n[i] + " ");
            System.out.print(g[i] + " ");
            System.out.print(r[i] + " ");
            System.out.print(a[i] + " ");
            System.out.print(t[i] + " ");
            System.out.print(s[i] + " ");
            System.out.print(exclamation[i]);
            System.out.println();
        }
    }
}
