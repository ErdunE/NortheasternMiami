package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import log.LogHelper;

import java.util.logging.Logger;

public class MenuBarComponent {

    private static final Logger logger = LogHelper.getLogger(MenuBarComponent.class);

    private final HBox menuBar;
    private final MainLayout mainLayout;

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

    private Button createButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(e -> {
            logger.info("Button clicked: " + text);
            action.run();
        });
        return button;
    }

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

    private void switchTab(String type, String additionalParam) {
        logger.info("Switching tab to: " + type);
        RecommendationGrid newGrid = new RecommendationGrid(type, additionalParam);
        mainLayout.updateRecommendationGrid(newGrid);
    }

    private void showFilterDialog(Stage primaryStage) {
        logger.info("Opening Filter Dialog");
        FilterDialog filterDialog = new FilterDialog();
        filterDialog.show(primaryStage, mainLayout);
    }

    public HBox getMenuBar() {
        return menuBar;
    }
}