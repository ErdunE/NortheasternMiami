/**
 * @author Erdun E
 * Date:Sep 7th, 2024
 * Course: CS5010 Programming Design Paradigm
 * Program: Assignment 1
 *
 * Model for Assignment 1,
 * include storage of Verb and Noun, get user input, determine validation of verb and noun and if they are matched which input from user
 *
 *
 */

enum Verb{
    walk,unlock,talk,grab,take;
}
enum Noun{
    road,field,bag,iphone,friend,subway,uber;
}
public class AdventureModel {

    private String userName;
    private String inputVerb;
    private String inputNoun;

    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getInputVerb(){
        return this.inputVerb;
    }
    public void setInputVerb(String inputVerb){
        this.inputVerb = inputVerb;
    }
    public String getInputNoun(){
        return this.inputNoun;
    }
    public void setInputNoun(String inputNoun){
        this.inputNoun = inputNoun;
    }

    public void ifVerbIsValid(){
        boolean verbIsValid = false;
        w1:while(!verbIsValid){
            String inputVerb = AdventureController.in.nextLine();
            setInputVerb(inputVerb);
            int count = 0;
            int size = Verb.values().length;
            // System.out.println(size);
            for(Verb allverb : Verb.values()){
                if(inputVerb.equals("stop")) {
                    break w1;
                } else if (!inputVerb.equals(allverb.name())) {
                    // System.out.println("Please enter a valid verb or 'stop' to stop the adventure.");
                    count++;
                    // System.out.println(count);
                    if(count == size){
                        System.out.println("Please enter a valid verb or 'stop' to stop the adventure.");
                    }
                }else{
                    verbIsValid = true;
                }
            }
        }
        System.out.println("You decided to " + inputVerb + ", and then choose the noun:");
    }

    public void ifNounIsValid(){
        boolean nounIsValid = false;
        w1:while(!nounIsValid) {
            String inputNoun = AdventureController.in.nextLine();
            setInputNoun(inputNoun);
            int count = 0;
            int size = Noun.values().length;
            // System.out.println(size);
            for (Noun allnoun : Noun.values()) {
                if (inputNoun.equals("stop")) {
                    break w1;
                } else if (!inputNoun.equals(allnoun.name())) {
                    count++;
                    // System.out.println(count);
                    if (count == size) {
                        System.out.println("Please enter a valid noun or 'stop' to stop the adventure.");
                    }
                } else {
                    boolean userInputNoun = ifVerbNoneMatched();
                    if(userInputNoun){
                        nounIsValid = true;
                    }else{
                        continue w1;
                    }
                }
            }
        }
    }

    public boolean ifVerbNoneMatched(){
        boolean verbNoneMatched = true;
        if(inputVerb.equals("walk") && inputNoun.equals("road")) {
            System.out.println(userName + ", you walked on the road, the weather is good today!");
        }else if(inputVerb.equals("walk") && inputNoun.equals("field")) {
            System.out.println(userName + ", you entered into a field and not familiar with this place.");
        }else if(inputVerb.equals("unlock") && inputNoun.equals("bag")) {
            System.out.println(userName + ", you opened your backpack and checked your belongings.");
        }else if(inputVerb.equals("unlock") && inputNoun.equals("iphone")) {
            System.out.println(userName + ", you unlocked iphone, checked your today's schedule.");
        }else if(inputVerb.equals("talk") && inputNoun.equals("friend")) {
            System.out.println(userName + ", you contacted your friends and confirmed the weekend party.");
        }else if(inputVerb.equals("talk") && inputNoun.equals("iphone")) {
            System.out.println(userName + ", you called your classmates to confirm that you have class today.");
        }else if(inputVerb.equals("grab") && inputNoun.equals("bag")) {
            System.out.println(userName + ", you grabbed your bag and put it on your back.");
        }else if(inputVerb.equals("grab") && inputNoun.equals("iphone")) {
            System.out.println(userName + ", you grabbed your iPhone and put it in your pocket.");
        }else if(inputVerb.equals("take") && inputNoun.equals("subway")) {
            System.out.println(userName + ", you plan to take the subway to campus today.");
        }else if(inputVerb.equals("take") && inputNoun.equals("uber")) {
            System.out.println(userName + ", you plan to take the uber to campus today.");
        }else{
            verbNoneMatched = false;
            System.out.println(inputVerb + " is not matched the noun " + inputNoun + ", please try again.");
        }
        return verbNoneMatched;
    }


}
