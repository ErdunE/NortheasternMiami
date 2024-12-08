//package ui;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.control.Alert;
//
//public class MenuBarComponent {
//
//    private final HBox menuBar;
//
//    public MenuBarComponent(Stage primaryStage) {
//        Button popularButton = new Button("Popular");
//        Button genreButton = new Button("By Genre");
//        Button ratingButton = new Button("By Rating");
//        Button filterButton = new Button("Filter");
//
//        popularButton.setOnAction(e -> switchTab(primaryStage, "popular", null));
//        genreButton.setOnAction(e -> switchTab(primaryStage, "genre", null));
//        ratingButton.setOnAction(e -> switchTab(primaryStage, "rating", null));
//        filterButton.setOnAction(e -> showFilterDialog());
//
//        menuBar = new HBox(15, popularButton, genreButton, ratingButton, filterButton);
//        menuBar.setAlignment(Pos.CENTER);
//        menuBar.getStyleClass().add("menu-bar");
//        menuBar.setPadding(new Insets(10, 20, 10, 20));
//    }
//
//    private void switchTab(Stage primaryStage, String type, String additionalParam) {
//        RecommendationGrid recommendationGrid = new RecommendationGrid(type, additionalParam);
//        ScrollPane recommendationScrollPane = (ScrollPane) ((VBox) primaryStage.getScene().getRoot()).getChildren().get(2);
//        recommendationScrollPane.setContent(recommendationGrid.getGrid());
//    }
//
//    private void showFilterDialog() {
//        FilterDialog filterDialog = new FilterDialog();
//        filterDialog.show(new Stage());
//    }
//
//    public void updateGridWithFilters(Stage primaryStage, String genreIds, String minRating, String maxRating, String language, String minRuntime, String maxRuntime, String year) {
//        RecommendationGrid recommendationGrid = new RecommendationGrid(genreIds, minRating, maxRating, language, minRuntime, maxRuntime, year);
//        ScrollPane recommendationScrollPane = (ScrollPane) ((VBox) primaryStage.getScene().getRoot()).getChildren().get(2);
//        recommendationScrollPane.setContent(recommendationGrid.getGrid());
//    }
//
//    public HBox getMenuBar() {
//        return menuBar;
//    }
//}

package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuBarComponent {

    private final HBox menuBar;
    private final MainLayout mainLayout;

    public MenuBarComponent(Stage primaryStage, MainLayout mainLayout) {
        this.mainLayout = mainLayout;

        Button popularButton = new Button("Popular");
        Button genreButton = new Button("By Genre");
        Button ratingButton = new Button("By Rating");
        Button filterButton = new Button("Filter");

        popularButton.setOnAction(e -> switchTab(primaryStage, "popular", null));
        genreButton.setOnAction(e -> switchTab(primaryStage, "genre", null));
        ratingButton.setOnAction(e -> switchTab(primaryStage, "rating", null));
        filterButton.setOnAction(e -> showFilterDialog(primaryStage));

        menuBar = new HBox(15, popularButton, genreButton, ratingButton, filterButton);
        menuBar.setAlignment(Pos.CENTER);
        menuBar.getStyleClass().add("menu-bar");
        menuBar.setPadding(new Insets(10, 20, 10, 20));
    }

    // 切换推荐网格页面，并更新 MainLayout 的 RecommendationGrid 实例
    private void switchTab(Stage primaryStage, String type, String additionalParam) {
        RecommendationGrid newGrid = new RecommendationGrid(type, additionalParam);
        mainLayout.updateRecommendationGrid(newGrid);
    }

    // 显示筛选对话框，并在应用筛选后更新 RecommendationGrid
    private void showFilterDialog(Stage primaryStage) {
        FilterDialog filterDialog = new FilterDialog();
        filterDialog.show(primaryStage, mainLayout);
    }

    // 获取菜单栏组件
    public HBox getMenuBar() {
        return menuBar;
    }
}