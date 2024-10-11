package ui;

import context.RecommendationContext;
import strategy.PopularRecommendation;
import strategy.GenreRecommendation;

import java.util.List;
import java.util.Scanner;

/**
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-01
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 *
 * Main class to interact with the recommendation system
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

        // Capture the user's choice (1 or 2) from the input.
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Based on the user's choice, set the corresponding recommendation strategy.
        if (choice == 1) {
            // User selected "Popular Recommendations", set strategy to PopularRecommendation.
            context.setRecommendationStrategy(new PopularRecommendation());
        } else if (choice == 2) {
            // User selected "Genre-Based Recommendations", prompt for the desired genre.
            System.out.println("Enter genre (e.g., Action, Drama):");
            String genre = scanner.nextLine();
            // Set strategy to GenreRecommendation, passing the user-specified genre.
            context.setRecommendationStrategy(new GenreRecommendation(genre));
        } else {
            // If the user enters an invalid option, exit the program.
            System.out.println("Invalid choice, exiting.");
            return;
        }

        // Get the recommendations from the currently set strategy.
        List<String> recommendations = context.getRecommendations();

        // Display the recommended movies to the user.
        System.out.println("Recommendations:");
        for (String recommendation : recommendations) {
            System.out.println("- " + recommendation);
        }

        // Close the scanner to release system resources.
        scanner.close();
    }
}