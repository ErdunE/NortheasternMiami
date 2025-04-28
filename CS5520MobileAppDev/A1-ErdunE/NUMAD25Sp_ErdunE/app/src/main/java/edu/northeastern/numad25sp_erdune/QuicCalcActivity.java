package edu.northeastern.numad25sp_erdune;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class QuicCalcActivity extends AppCompatActivity {

    private TextView calcDisplay;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quic_calc);
        calcDisplay = findViewById(R.id.calcDisplay);
        initializeButtons();
    }

    private void initializeButtons() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonPlus, R.id.buttonMinus,
                R.id.buttonDel, R.id.buttonEquals
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String currentText = calcDisplay.getText().toString();

        switch (buttonText) {
            case "del":
                if (!currentText.isEmpty() && !currentText.equals("CALC")) {
                    calcDisplay.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;

            case "=":
                try {
                    double result = evaluateExpression(currentText);
                    if (result == (long) result) {
                        calcDisplay.setText(String.valueOf((long) result));
                    } else {
                        calcDisplay.setText(String.valueOf(result));
                    }
                } catch (Exception e) {
                    calcDisplay.setText("Error");
                }
                isNewInput = true; // Reset for next input
                break;

            default:
                if (isNewInput || currentText.equals("CALC") || currentText.equals("Error")) {
                    calcDisplay.setText(buttonText);
                } else {
                    calcDisplay.append(buttonText);
                }
                isNewInput = false;
                break;
        }
    }

    private double evaluateExpression(String expression) throws Exception {
        if (expression.isEmpty()) {
            throw new Exception("Empty expression");
        }

        double result = 0;
        String[] parts = expression.split("(?=[+-])");
        for (String part : parts) {
            if (part.startsWith("+")) {
                result += Double.parseDouble(part.substring(1));
            } else if (part.startsWith("-")) {
                result -= Double.parseDouble(part.substring(1));
            } else {
                result += Double.parseDouble(part);
            }
        }
        return result;
    }
}