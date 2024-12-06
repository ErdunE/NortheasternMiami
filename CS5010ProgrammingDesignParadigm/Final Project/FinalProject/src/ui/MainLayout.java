package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainLayout {

    private final VBox mainLayout;

    public MainLayout(Stage primaryStage) {
        Text titleText = TitleComponent.createGradientTitle();

        MenuBarComponent menuBarComponent = new MenuBarComponent(primaryStage);

        ScrollPane recommendationScrollPane = new ScrollPane();
        recommendationScrollPane.setFitToWidth(true);
        recommendationScrollPane.setStyle("-fx-background: transparent;");

        RecommendationGrid recommendationGrid = new RecommendationGrid("popular", null);
        recommendationScrollPane.setContent(recommendationGrid.getGrid());

        mainLayout = new VBox(20, titleText, menuBarComponent.getMenuBar(), recommendationScrollPane);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getStyleClass().add("root");
    }

    public VBox getMainLayout() {
        return mainLayout;
    }
}