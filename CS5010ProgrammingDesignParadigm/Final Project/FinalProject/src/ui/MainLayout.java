package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainLayout {

    private final VBox mainLayout;
    private RecommendationGrid recommendationGrid;
    private final ScrollPane recommendationScrollPane;
    private final HBox sortButtons;

    public MainLayout(Stage primaryStage) {
        Text titleText = TitleComponent.createGradientTitle();

        MenuBarComponent menuBarComponent = new MenuBarComponent(primaryStage, this);

        recommendationScrollPane = new ScrollPane();
        recommendationScrollPane.setFitToWidth(true);
        recommendationScrollPane.setStyle("-fx-background: transparent;");

        recommendationGrid = new RecommendationGrid("popular", null);
        recommendationScrollPane.setContent(recommendationGrid.getGrid());

        sortButtons = createSortButtons();

        mainLayout = new VBox(20, titleText, menuBarComponent.getMenuBar(), sortButtons, recommendationScrollPane);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getStyleClass().add("root");
    }

    public VBox getMainLayout() {
        return mainLayout;
    }

    private HBox createSortButtons() {
        Button sortByDateButton = new Button("Sort by Release Date");
        Button sortByRatingButton = new Button("Sort by Rating");
        Button sortByPopularityButton = new Button("Sort by Popularity");

        sortByDateButton.setOnAction(e -> recommendationGrid.sortMovies("date"));
        sortByRatingButton.setOnAction(e -> recommendationGrid.sortMovies("rating"));
        sortByPopularityButton.setOnAction(e -> recommendationGrid.sortMovies("popularity"));

        HBox sortButtons = new HBox(10, sortByDateButton, sortByRatingButton, sortByPopularityButton);
        sortButtons.setAlignment(Pos.CENTER);
        sortButtons.setPadding(new Insets(10));

        return sortButtons;
    }

    public void updateRecommendationGrid(RecommendationGrid newGrid) {
        this.recommendationGrid = newGrid;
        recommendationScrollPane.setContent(newGrid.getGrid());
    }
}