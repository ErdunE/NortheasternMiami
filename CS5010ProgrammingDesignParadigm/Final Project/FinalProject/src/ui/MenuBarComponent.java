package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import log.LogHelper;

import java.util.logging.Logger;

/**
 * Represents the menu bar component of the Entertainment Recommendation System.
 * The menu bar includes buttons for navigating between different movie categories,
 * applying filters, and a search box for searching movies.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class MenuBarComponent {

    private static final Logger logger = LogHelper.getLogger(MenuBarComponent.class);

    private final HBox menuBar;
    private final MainLayout mainLayout;

    /**
     * Initializes the MenuBarComponent with navigation buttons and a search box.
     *
     * @param primaryStage The primary stage of the application.
     * @param mainLayout   The main layout to update based on user interactions.
     */
    public MenuBarComponent(Stage primaryStage, MainLayout mainLayout) {
        this.mainLayout = mainLayout;

        Button popularButton = createButton("Popular Movies", () -> switchTab("popular", null));
        Button genreButton = createButton("Genre Movies (Deprecated)", () -> switchTab("genre", null));
        Button ratingButton = createButton("Rating Movies", () -> switchTab("rating", null));
        Button filterButton = createButton("Filter", () -> showFilterDialog(primaryStage));

        HBox searchBox = createSearchBox();

        menuBar = new HBox(15, popularButton, genreButton, ratingButton, filterButton, searchBox);
        menuBar.setAlignment(Pos.CENTER);
        menuBar.getStyleClass().add("menu-bar");
        menuBar.setPadding(new Insets(10, 20, 10, 20));
    }

    /**
     * Creates a button with specified text and an action to execute when clicked.
     *
     * @param text   The text displayed on the button.
     * @param action The action to execute when the button is clicked.
     * @return The created Button instance.
     */
    private Button createButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(e -> {
            logger.info("Button clicked: " + text);
            action.run();
        });
        return button;
    }

    /**
     * Creates the search box containing a text field and a search button.
     *
     * @return The HBox containing the search components.
     */
    private HBox createSearchBox() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search movies...");
        searchField.setPrefWidth(200);

        Button searchButton = new Button("🔍");
        Runnable searchAction = () -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                logger.info("Executing search for query: " + query);
                mainLayout.performSearch(query);
            }
        };

        searchButton.setOnAction(e -> searchAction.run());
        searchField.setOnAction(e -> searchAction.run());

        HBox searchBox = new HBox(5, searchField, searchButton);
        searchBox.setAlignment(Pos.CENTER);
        return searchBox;
    }

    /**
     * Switches the current recommendation grid based on the specified type.
     *
     * @param type            The type of movies to display (e.g., "popular", "rating").
     * @param additionalParam Additional parameter for the recommendation type, if any.
     */
    private void switchTab(String type, String additionalParam) {
        logger.info("Switching tab to: " + type);
        RecommendationGrid newGrid = new RecommendationGrid(type, additionalParam);
        mainLayout.updateRecommendationGrid(newGrid);
    }

    /**
     * Displays the filter dialog for applying filters to movie recommendations.
     *
     * @param primaryStage The primary stage used to display the dialog.
     */
    private void showFilterDialog(Stage primaryStage) {
        logger.info("Opening Filter Dialog");
        FilterDialog filterDialog = new FilterDialog();
        filterDialog.show(primaryStage, mainLayout);
    }

    /**
     * Gets the menu bar containing all the buttons and search box.
     *
     * @return The HBox representing the menu bar.
     */
    public HBox getMenuBar() {
        return menuBar;
    }
}