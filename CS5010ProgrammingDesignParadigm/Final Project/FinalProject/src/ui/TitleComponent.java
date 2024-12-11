package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Utility class for creating a stylized title with gradient and shadow effects.
 * The title is designed for use in the Movie Hub application.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class TitleComponent {

    private static final String DEFAULT_FONT_FAMILY = "Bebas Neue";
    private static final int DEFAULT_FONT_SIZE = 40;
    private static final Stop[] DEFAULT_GRADIENT_STOPS = {
            new Stop(0, Color.rgb(229, 9, 20)),
            new Stop(1, Color.rgb(184, 29, 36))
    };
    private static final DropShadow DEFAULT_DROP_SHADOW = new DropShadow(4, Color.BLACK);

    /**
     * Creates a gradient-styled title with a drop shadow effect.
     *
     * @return A {@link Text} object representing the stylized title "Movie Hub".
     */
    public static Text createGradientTitle() {
        String title = "Movie Hub";
        Text titleText = new Text(title);
        titleText.setFont(Font.font(DEFAULT_FONT_FAMILY, FontWeight.BOLD, DEFAULT_FONT_SIZE));

        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE, DEFAULT_GRADIENT_STOPS
        );
        titleText.setFill(gradient);
        titleText.setEffect(DEFAULT_DROP_SHADOW);

        return titleText;
    }
}