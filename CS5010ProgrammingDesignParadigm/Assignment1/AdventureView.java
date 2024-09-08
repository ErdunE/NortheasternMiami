/**
 * @author Erdun E
 * Date:Sep 7th, 2024
 * Course: CS5010 Programming Design Paradigm
 * Program: Assignment 1
 *
 * View for Assignment 1,
 * include the menu of verb and noun and all outputs of the situations.
 *
 *
 */

public class AdventureView {
    public AdventureView() {

    }
    public void verbMenu(){
        for(Verb allVerbs : Verb.values()){
            System.out.println(allVerbs);
        }
    }
    public void nounMenu(){
        for(Noun allNouns : Noun.values()){
            System.out.println(allNouns);
        }
    }
    public void showUserName(String userName) {
        System.out.println("Hi " + userName);
    }
    public void display() {

    }
    public void welcomeMessage(){
        System.out.println("Welcome to the adventure of going to school.");
        System.out.println("Please enter your name: ");
    }
    public void backgroundMessage(){
        System.out.println("You are a new student of NU Miami, and today is your first day to go to the campus.");
    }
    public void situation_1(){
        System.out.println("Now is 7:00am, you wake up and plan to go to the campus, then you: ");
    }
    public void situation_2(){
        System.out.println("Now is 7:30am, you're almost at the subway station, then you: ");
    }
    public void situation_3(){
        System.out.println("Now is 8:00am, on the way you realize that it’s going to rain heavily soon, then you: ");
    }
    public void situation_4(){
        System.out.println("Now is 8:30am, you arrived around the campus and bought a cup of coffee, then you: ");
    }

    public void endMessage(){
        System.out.println("In the end, you reach to campus and start the CS 5010 on time :) .");
        System.out.print("Congrats:P!!!");
    }
}
