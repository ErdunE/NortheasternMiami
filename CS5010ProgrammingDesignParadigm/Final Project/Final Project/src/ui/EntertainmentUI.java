package ui;

import context.RecommendationContext;
import factory.RecommendationFactory;
import strategy.*;
import java.util.List;
import java.util.Scanner;

/**
 * Main class to interact with the entertainment recommendation system
 *
 * @author Erdun E
 * @version 1.2
 * @since 2024-12-04
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 */
public class EntertainmentUI {

    public static void main(String[] args) {
        // Initialize a scanner to capture user input from the console.
        Scanner scanner = new Scanner(System.in);
        // Initialize the RecommendationContext, which will manage the current recommendation strategy.
        RecommendationContext context = new RecommendationContext();

        // Display a welcome message and provide the user with options to choose a recommendation strategy.
        System.out.println("Welcome to the Entertainment Recommendation System");
        System.out.println("Please select a movie recommendation strategy:");
        System.out.println("1. Popular Recommendations");
        System.out.println("2. Genre-Based Recommendations");
        System.out.println("3. Rating-Based Recommendations");

        // Capture the user's choice (1 or 2) from the input.
        int choice = scanner.nextInt();
        scanner.nextLine();

        RecommendationStrategy strategy = null;

        // Based on the user's choice, set the corresponding recommendation strategy.
        switch (choice) {
            case 1:
                strategy = RecommendationFactory.createStrategy("popular", null);
                break;
            case 2:
                System.out.println("Enter genre (e.g., Action, Drama):");
                String genre = scanner.nextLine();
                strategy = RecommendationFactory.createStrategy("genre", genre);
                break;
            case 3:
                strategy = RecommendationFactory.createStrategy("rating", null);
                break;
            default:
                System.out.println("Invalid choice, exiting.");
                return;
        }

        if (strategy != null) {
            context.setRecommendationStrategy(strategy);
            List<String> recommendations = context.getRecommendations();

            System.out.println("Recommendations:");
            for (String recommendation : recommendations) {
                System.out.println("- " + recommendation);
            }
        } else {
            System.out.println("No valid strategy found, exiting.");
        }

        scanner.close();
    }
}