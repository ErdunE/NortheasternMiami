package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuBarComponent {

    private final HBox menuBar;
    private final MainLayout mainLayout;

    public MenuBarComponent(Stage primaryStage, MainLayout mainLayout) {
        this.mainLayout = mainLayout;

        Button popularButton = new Button("Popular Movies");
        Button genreButton = new Button("Genre Movies(Deprecated)");
        Button ratingButton = new Button("Rating Movies");
        Button filterButton = new Button("Filter");

        popularButton.setOnAction(e -> switchTab(primaryStage, "popular", null));
        genreButton.setOnAction(e -> switchTab(primaryStage, "genre", null));
        ratingButton.setOnAction(e -> switchTab(primaryStage, "rating", null));
        filterButton.setOnAction(e -> showFilterDialog(primaryStage));

        TextField searchField = new TextField();
        searchField.setPromptText("Search movies...");
        searchField.setPrefWidth(200);
        Button searchButton = new Button("🔍");

        Runnable searchAction = () -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                mainLayout.performSearch(query);
            }
        };

        searchButton.setOnAction(e -> searchAction.run());
        searchField.setOnAction(e -> searchAction.run());

        HBox searchBox = new HBox(5, searchField, searchButton);
        searchBox.setAlignment(Pos.CENTER);

        menuBar = new HBox(15, popularButton, genreButton, ratingButton, filterButton, searchBox);
        menuBar.setAlignment(Pos.CENTER);
        menuBar.getStyleClass().add("menu-bar");
        menuBar.setPadding(new Insets(10, 20, 10, 20));
    }

    private void switchTab(Stage primaryStage, String type, String additionalParam) {
        RecommendationGrid newGrid = new RecommendationGrid(type, additionalParam);
        mainLayout.updateRecommendationGrid(newGrid);
    }

    private void showFilterDialog(Stage primaryStage) {
        FilterDialog filterDialog = new FilterDialog();
        filterDialog.show(primaryStage, mainLayout);
    }

    public HBox getMenuBar() {
        return menuBar;
    }
}