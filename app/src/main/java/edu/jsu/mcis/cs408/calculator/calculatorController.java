package edu.jsu.mcis.cs408.calculator;

import android.util.Log;

public class calculatorController extends AbstractController {

    private calculatorModel calculatorModel;
    private MainActivity mainActivity;

    public calculatorController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        calculatorModel = new calculatorModel();
        handleClearClick();
    }

    public void handleButtonClick(String buttonTag) {
        if(calculatorModel.getCalculatorState() == CalculatorState.CLEAR) {
            calculatorModel.setCalculatorState(CalculatorState.LHS);
        }
        else if (calculatorModel.getCalculatorState() == CalculatorState.OP_SELECTED) {
            calculatorModel.setCalculatorState(CalculatorState.RHS);
        }

        if (buttonTag.matches("[0-9]")) {
                handleDigitButtonClick(buttonTag);
            }
        else if(buttonTag.matches("[+-×÷]")){
            calculatorModel.setCalculatorState(CalculatorState.OP_SELECTED);
            handleOperatorButtonClick(buttonTag);
        }
        else if(buttonTag.matches("C")){
            calculatorModel.setCalculatorState(CalculatorState.CLEAR);
            handleClearClick();
        }
        else if(buttonTag.matches("=")){
            Log.d("TEST !", "THIS PART WORKS");
            calculatorModel.setCalculatorState(CalculatorState.RESULT);
            handleEqualClick();
        }

    }

    private void handleEqualClick() {
        calculatorModel.handleEqualClick();
    }

    private void handleOperatorButtonClick(String operator) {
        calculatorModel.handleOperatorButtonClick(operator);
    }

    public void handleDigitButtonClick(String digit) {
        calculatorModel.handleDigitClick(digit);
    }

    public void handleClearClick() {
        // Handle the click of the clear button
        calculatorModel.handleClearClick();
    }
}
