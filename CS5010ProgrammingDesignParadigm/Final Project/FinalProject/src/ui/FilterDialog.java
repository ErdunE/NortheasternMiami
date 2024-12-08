package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.TMDBGenreMapper;

import java.util.ArrayList;
import java.util.List;

public class FilterDialog {

    private final List<String> selectedGenres = new ArrayList<>();
    private final List<String> selectedRatings = new ArrayList<>();
    private final List<String> selectedLanguages = new ArrayList<>();
    private final List<String> selectedYears = new ArrayList<>();
    private final List<String> selectedDurations = new ArrayList<>();
    private final List<String> selectedRatingLevels = new ArrayList<>();

    private List<ToggleButton> allButtons = new ArrayList<>();

    public void show(Stage parentStage, MainLayout mainLayout) {
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL);
        filterStage.setTitle("Filter Movies");

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        // Genre Filter
        Label genreLabel = new Label("Genre:");
        FlowPane genreBox = createButtonGroup(new String[]{
                "Action", "Adventure", "Animation", "Biography", "Comedy", "Crime",
                "Documentary", "Drama", "Family", "Fantasy", "History", "Horror",
                "Mystery", "Romance", "Sci-Fi", "Short", "Sport", "War", "Western"
        }, selectedGenres);

        // Rating Range Filter
        Label ratingLabel = new Label("Rating Range:");
        FlowPane ratingBox = createButtonGroup(new String[]{
                "6+", "6.5+", "7+", "7.5+", "8+", "8.5+", "9+"
        }, selectedRatings);

        // Release Year Filter
        Label yearLabel = new Label("Release Year:");
        FlowPane yearBox = createButtonGroup(new String[]{
                "This Year", "Last Year", "Earlier"
        }, selectedYears);

        // Language Filter
        Label languageLabel = new Label("Language:");
        FlowPane languageBox = createButtonGroup(new String[]{
                "English", "Mandarin", "Cantonese", "Korean", "Japanese",
                "Spanish", "French", "German", "Italian", "Other"
        }, selectedLanguages);

        // Duration Filter
        Label durationLabel = new Label("Duration:");
        FlowPane durationBox = createButtonGroup(new String[]{
                "0-90 mins", "90-120 mins", "120+ mins"
        }, selectedDurations);

        // Rating Level Filter
        Label ratingLevelLabel = new Label("Rating Level:");
        FlowPane ratingLevelBox = createButtonGroup(new String[]{
                "G", "PG", "PG-13", "R", "NC-17"
        }, selectedRatingLevels);

        // Search Button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            filterStage.close();
            applyFilters(mainLayout);
        });

        // Reset Button
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> resetFilters());

        // Button Box
        HBox buttonBox = new HBox(20, searchButton, resetButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Adding all filters to the layout
        root.getChildren().addAll(
                genreLabel, genreBox,
                ratingLabel, ratingBox,
                yearLabel, yearBox,
                languageLabel, languageBox,
                durationLabel, durationBox,
                ratingLevelLabel, ratingLevelBox,
                buttonBox
        );

        // Create and show the scene
        Scene scene = new Scene(root, 800, 800);
        filterStage.setScene(scene);
        filterStage.showAndWait();
    }

    // Helper method to create button groups for each filter category
    private FlowPane createButtonGroup(String[] options, List<String> selectedItems) {
        FlowPane box = new FlowPane();
        box.setHgap(10);
        box.setVgap(10);
        box.setPadding(new Insets(5));

        for (String option : options) {
            ToggleButton button = new ToggleButton(option);
            button.setOnAction(e -> {
                if (button.isSelected()) {
                    selectedItems.add(option);
                } else {
                    selectedItems.remove(option);
                }
            });
            allButtons.add(button);
            box.getChildren().add(button);
        }
        return box;
    }

    // Method to reset all selected filters
    private void resetFilters() {
        selectedGenres.clear();
        selectedRatings.clear();
        selectedLanguages.clear();
        selectedYears.clear();
        selectedDurations.clear();
        selectedRatingLevels.clear();

        for (ToggleButton button : allButtons) {
            button.setSelected(false);
        }

        System.out.println("All filters have been reset.");
    }

    // Apply filters and update the RecommendationGrid in MainLayout
    private void applyFilters(MainLayout mainLayout) {
        System.out.println("Filters applied:");
        System.out.println("Genres: " + selectedGenres);
        System.out.println("Ratings: " + selectedRatings);
        System.out.println("Years: " + selectedYears);
        System.out.println("Languages: " + selectedLanguages);
        System.out.println("Durations: " + selectedDurations);
        System.out.println("Rating Levels: " + selectedRatingLevels);

        TMDBGenreMapper genreMapper = new TMDBGenreMapper();
        List<Integer> genreIds = new ArrayList<>();

        for (String genre : selectedGenres) {
            int genreId = genreMapper.getGenreIdByName(genre);
            if (genreId != -1) {
                genreIds.add(genreId);
            }
        }

        String genreIdsParam = genreIds.isEmpty() ? null : String.join(",", genreIds.stream()
                .map(String::valueOf)
                .toArray(String[]::new));

        String minRating = selectedRatings.isEmpty() ? null : selectedRatings.get(0).replace("+", "");
        String year = selectedYears.isEmpty() ? null : selectedYears.get(0);
        String language = selectedLanguages.isEmpty() ? null : selectedLanguages.get(0);
        String minRuntime = selectedDurations.isEmpty() ? null : getMinRuntime(selectedDurations.get(0));
        String maxRuntime = selectedDurations.isEmpty() ? null : getMaxRuntime(selectedDurations.get(0));

        // Create a new RecommendationGrid based on the filters
        RecommendationGrid newGrid = new RecommendationGrid(genreIdsParam, minRating, null, language, minRuntime, maxRuntime, year);
        mainLayout.updateRecommendationGrid(newGrid);
    }

    // Helper methods to convert duration strings to runtime ranges
    private String getMinRuntime(String duration) {
        if (duration.equals("0-90 mins")) return "0";
        if (duration.equals("90-120 mins")) return "90";
        if (duration.equals("120+ mins")) return "120";
        return null;
    }

    private String getMaxRuntime(String duration) {
        if (duration.equals("0-90 mins")) return "90";
        if (duration.equals("90-120 mins")) return "120";
        return null;
    }
}