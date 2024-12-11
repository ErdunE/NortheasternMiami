package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.TMDBGenreMapper;
import service.TMDBLanguageMapper;
import log.LogHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FilterDialog {

    private static final Logger logger = LogHelper.getLogger(FilterDialog.class);

    private final List<String> selectedGenres = new ArrayList<>();
    private final List<String> selectedRatings = new ArrayList<>();
    private final List<String> selectedLanguages = new ArrayList<>();
    private final List<String> selectedYears = new ArrayList<>();
    private final List<String> selectedDurations = new ArrayList<>();
    private final List<String> selectedRatingLevels = new ArrayList<>();

    private List<ToggleButton> allButtons = new ArrayList<>();

    public void show(Stage parentStage, MainLayout mainLayout) {
        Stage filterStage = initializeStage();

        VBox root = createFilterLayout(mainLayout, filterStage);

        // Search Button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            filterStage.close();
            applyFilters(mainLayout);
        });

        // Create and show the scene
        Scene scene = new Scene(root, 800, 800);
        filterStage.setScene(scene);
        filterStage.showAndWait();
    }

    private Stage initializeStage() {
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL);
        filterStage.setTitle("Filter Movies");
        return filterStage;
    }

    private VBox createFilterLayout(MainLayout mainLayout, Stage filterStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                createSection("Genre:", createButtonGroup(new String[]{
                        "Action", "Adventure", "Animation", "Biography", "Comedy", "Crime",
                        "Documentary", "Drama", "Family", "Fantasy", "History", "Horror",
                        "Mystery", "Romance", "Sci-Fi", "Short", "Sport", "War", "Western"
                }, selectedGenres)),
                createSection("Rating Range:", createButtonGroup(new String[]{
                        "6+", "6.5+", "7+", "7.5+", "8+", "8.5+", "9+"
                }, selectedRatings)),
                createSection("Release Year:", createButtonGroup(new String[]{
                        "This Year", "Last Year", "Earlier"
                }, selectedYears)),
                createSection("Language:", createButtonGroup(new String[]{
                        "English", "Mandarin", "Cantonese", "Korean", "Japanese",
                        "Spanish", "French", "German", "Italian", "Other"
                }, selectedLanguages)),
                createSection("Duration:", createButtonGroup(new String[]{
                        "0-90 mins", "90-120 mins", "120+ mins"
                }, selectedDurations)),
                createSection("Rating Level:", createButtonGroup(new String[]{
                        "G", "PG", "PG-13", "R", "NC-17"
                }, selectedRatingLevels)),
                createButtonBox(mainLayout, filterStage)
        );

        return root;
    }

    private VBox createSection(String labelText, FlowPane buttonGroup) {
        VBox section = new VBox(10);
        Label label = new Label(labelText);
        section.getChildren().addAll(label, buttonGroup);
        return section;
    }

    private HBox createButtonBox(MainLayout mainLayout, Stage filterStage) {
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            filterStage.close();
            applyFilters(mainLayout);
        });

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> resetFilters());

        HBox buttonBox = new HBox(20, searchButton, resetButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    private FlowPane createButtonGroup(String[] options, List<String> selectedItems) {
        FlowPane box = new FlowPane(10, 10);
        box.setPadding(new Insets(5));

        for (String option : options) {
            ToggleButton button = createToggleButton(option, selectedItems);
            allButtons.add(button);
            box.getChildren().add(button);
        }
        return box;
    }

    private ToggleButton createToggleButton(String option, List<String> selectedItems) {
        ToggleButton button = new ToggleButton(option);
        button.setOnAction(e -> {
            if (button.isSelected()) {
                selectedItems.add(option);
            } else {
                selectedItems.remove(option);
            }
        });
        return button;
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

        logger.info("All filters have been reset.");
    }

    // Apply filters and update the RecommendationGrid in MainLayout
    private void applyFilters(MainLayout mainLayout) {
        logger.info("Applying filters with the following selections:");
        logger.info("Genres: " + selectedGenres);
        logger.info("Ratings: " + selectedRatings);
        logger.info("Years: " + selectedYears);
        logger.info("Languages: " + selectedLanguages);
        logger.info("Durations: " + selectedDurations);
        logger.info("Rating Levels: " + selectedRatingLevels);

        TMDBGenreMapper genreMapper = new TMDBGenreMapper();
        TMDBLanguageMapper languageMapper = new TMDBLanguageMapper();
        List<Integer> genreIds = new ArrayList<>();

        for (String genre : selectedGenres) {
            int genreId = genreMapper.getIdByGenreName(genre);
            if (genreId != -1) {
                genreIds.add(genreId);
            }
        }

        String genreIdsParam = genreIds.isEmpty() ? null : String.join(",", genreIds.stream()
                .map(String::valueOf)
                .toArray(String[]::new));

        String minRating = selectedRatings.isEmpty() ? null : selectedRatings.get(0).replace("+", "");

        String year = getYearFromSelection(selectedYears.isEmpty() ? null : selectedYears.get(0));
        String releaseDateLte = null;
        if (selectedYears.contains("Earlier")) {
            releaseDateLte = (java.time.Year.now().getValue() - 2) + "-12-31";
        }

        String language = selectedLanguages.isEmpty() ? null : languageMapper.getCodeByLanguageName(selectedLanguages.get(0));
        String minRuntime = selectedDurations.isEmpty() ? null : getMinRuntime(selectedDurations.get(0));
        String maxRuntime = selectedDurations.isEmpty() ? null : getMaxRuntime(selectedDurations.get(0));
        String certification = selectedRatingLevels.isEmpty() ? null : selectedRatingLevels.get(0);

        RecommendationGrid newGrid = new RecommendationGrid(genreIdsParam, minRating, null, language, minRuntime, maxRuntime, year, releaseDateLte, certification);
        mainLayout.updateRecommendationGrid(newGrid);
    }

    // Helper methods to convert duration strings to runtime ranges
    private String getMinRuntime(String duration) {
        switch (duration) {
            case "0-90 mins":
                return "0";
            case "90-120 mins":
                return "90";
            case "120+ mins":
                return "120";
            default:
                return null;
        }
    }

    private String getMaxRuntime(String duration) {
        switch (duration) {
            case "0-90 mins":
                return "90";
            case "90-120 mins":
                return "120";
            case "120+ mins":
                return null;
            default:
                return null;
        }
    }

    // Helper method to map year selection to specific year
    private String getYearFromSelection(String selection) {
        if (selection == null) return null;
        int currentYear = java.time.Year.now().getValue();
        switch (selection) {
            case "This Year":
                return String.valueOf(currentYear);
            case "Last Year":
                return String.valueOf(currentYear - 1);
            case "Earlier":
                return null;
            default:
                return null;
        }
    }
}