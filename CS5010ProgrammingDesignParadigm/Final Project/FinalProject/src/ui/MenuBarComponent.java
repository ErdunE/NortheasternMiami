package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class MenuBarComponent {

    private final HBox menuBar;

    public MenuBarComponent(Stage primaryStage) {
        Button popularButton = new Button("Popular");
        Button genreButton = new Button("By Genre");
        Button ratingButton = new Button("By Rating");
        Button filterButton = new Button("Filter");

        popularButton.setOnAction(e -> switchTab(primaryStage, "popular", null));
        genreButton.setOnAction(e -> switchTab(primaryStage, "genre", null));
        ratingButton.setOnAction(e -> switchTab(primaryStage, "rating", null));
        filterButton.setOnAction(e -> showFilterDialog());

        menuBar = new HBox(15, popularButton, genreButton, ratingButton, filterButton);
        menuBar.setAlignment(Pos.CENTER);
        menuBar.getStyleClass().add("menu-bar");
        menuBar.setPadding(new Insets(10, 20, 10, 20));
    }

    private void switchTab(Stage primaryStage, String type, String additionalParam) {
        RecommendationGrid recommendationGrid = new RecommendationGrid(type, additionalParam);
        ScrollPane recommendationScrollPane = (ScrollPane) ((VBox) primaryStage.getScene().getRoot()).getChildren().get(2);
        recommendationScrollPane.setContent(recommendationGrid.getGrid());
    }

    private void showFilterDialog() {
        Alert filterDialog = new Alert(Alert.AlertType.INFORMATION);
        filterDialog.setTitle("Filter");
        filterDialog.setHeaderText("Filter Movies");
        filterDialog.setContentText("Filter options will be implemented.");
        filterDialog.showAndWait();
    }

    public HBox getMenuBar() {
        return menuBar;
    }
}