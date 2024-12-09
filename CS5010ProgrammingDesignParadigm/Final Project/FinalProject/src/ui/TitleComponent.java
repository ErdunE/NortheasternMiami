package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TitleComponent {

    public static Text createGradientTitle() {
        Text titleText = new Text("Entertainment Recommendation System");
        titleText.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 40));

        // Linear gradient for title color
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(229, 9, 20)), // Start color
                new Stop(1, Color.rgb(184, 29, 36)) // End color
        );
        titleText.setFill(gradient);

        // Drop shadow for better visibility
        titleText.setEffect(new DropShadow(4, Color.BLACK));

        return titleText;
    }
}