package edu.jsu.mcis.cs408.calculator;

import android.util.Log;

public class calculatorController extends AbstractController {

    private calculatorModel calculatorModel;
    private MainActivity mainActivity;

    public calculatorController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        calculatorModel = new calculatorModel();
    }

    public void handleButtonClick(String buttonTag) {
        if (buttonTag.matches("[0-9]")) {
            handleDigitButtonClick(buttonTag);
            Log.d("THIS ONE", "IT works");
        }
    }

    public void handleDigitButtonClick(String digit) {
        calculatorModel.handleDigitClick(digit);
    }

    public void handleClearClick() {
        // Handle the click of the clear button
        calculatorModel.handleClearClick();
    }
}
