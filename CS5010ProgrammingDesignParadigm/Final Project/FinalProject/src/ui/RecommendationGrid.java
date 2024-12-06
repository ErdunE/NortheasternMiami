package ui;

import context.RecommendationContext;
import factory.RecommendationFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import model.Movie;

import java.util.List;

public class RecommendationGrid {

    private final GridPane gridPane;

    public RecommendationGrid(String type, String additionalParam) {
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));

        RecommendationContext context = new RecommendationContext();
        context.setRecommendationStrategy(RecommendationFactory.createStrategy(type, additionalParam));
        List<Movie> recommendations = context.getRecommendationsWithDetails();

        int col = 0, row = 0;
        for (Movie movie : recommendations) {
            MovieCard movieCard = new MovieCard(movie);
            gridPane.add(movieCard.getMovieCard(), col++, row);

            if (col == 4) {
                col = 0;
                row++;
            }
        }
    }

    public GridPane getGrid() {
        return gridPane;
    }
}