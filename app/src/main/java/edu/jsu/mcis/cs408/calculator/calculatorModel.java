package edu.jsu.mcis.cs408.calculator;

import android.util.Log;

import java.math.BigDecimal;



public class calculatorModel {
    private static BigDecimal leftOperand;
    private static BigDecimal rightOperand;
    private BigDecimal currentOperand;
    private static CalculatorState calculatorState;

    private static OperatorEnum currentOperator;

    public calculatorModel() {
        // Initialize default values
        leftOperand = BigDecimal.ZERO;
        rightOperand = BigDecimal.ZERO;
        currentOperand = BigDecimal.ZERO;
        calculatorState = CalculatorState.CLEAR;
        currentOperator = OperatorEnum.ADDITION;
    }

    // Getter and setter methods for leftOperand, rightOperand, currentOperator, calculatorState

    public static void setLeftOperand(BigDecimal value) {
        leftOperand = value;
    }

    public static BigDecimal getLeftOperand() {
        return leftOperand;
    }

    public static void setRightOperand(BigDecimal value) {
        rightOperand = value;
    }

    public static BigDecimal getRightOperand() {
        return rightOperand;
    }

    public BigDecimal getCurrentOperand() {
        return currentOperand;
    }

    public void setCurrentOperand(BigDecimal newOperand) {
        this.currentOperand = newOperand;
    }

    public static OperatorEnum getCurrentOperator(){
        return currentOperator;
    }

    public static void setCurrentOperator(OperatorEnum newOperator){
        currentOperator = newOperator;
    }


    public static void setCalculatorState(CalculatorState state) {
        calculatorState = state;
    }

    public static CalculatorState getCalculatorState() {
        return calculatorState;
    }

    public static void handleButtonClick(String buttonTag) {
        switch (getCalculatorState()) {
            case CLEAR:
                setCalculatorState(CalculatorState.LHS);
                break;
            case OP_SELECTED:
                if(currentOperator.getSymbol().matches(OperatorEnum.SQUAREROOT.getSymbol())){
                    setCalculatorState(CalculatorState.RESULT);
                }
                else{
                    setCalculatorState(CalculatorState.RHS);
                }
                break;
        }

        switch (buttonTag) {
            case "C":
                setCalculatorState(CalculatorState.CLEAR);
                handleClearClick();
                break;
            case "=":
                setCalculatorState(CalculatorState.RESULT);
                handleEqualClick();
                break;
            case "+":
            case "-":
            case "×":
            case "÷":
            case "√":
                setCalculatorState(CalculatorState.OP_SELECTED);
                handleOperatorButtonClick(buttonTag);
                break;
            default:
                if (buttonTag.matches("[0-9]")) {
                    handleDigitClick(buttonTag);
                }
                break;
        }
    }

    public static void handleDigitClick(String digit) {
        // Handle the click of a digit button
        if (getCalculatorState() == CalculatorState.LHS) {
            String updatedOperand = getLeftOperand().toString() + digit;
            BigDecimal newOperand = new BigDecimal(updatedOperand);
            setLeftOperand(newOperand);
            Log.d("Test3", "LeftHandOperand : " + getLeftOperand());
        } else if (getCalculatorState() == CalculatorState.RHS) {
            String updatedOperand = getRightOperand().toString() + digit;
            BigDecimal newOperand = new BigDecimal(updatedOperand);
            setRightOperand(newOperand);
            Log.d("Test4", "RightHandOperand : " + getRightOperand());
        }
    }

    public static void handleOperatorButtonClick(String operator) {
        if (operator.matches(OperatorEnum.ADDITION.getSymbol())) {
            OperatorEnum newOperator = OperatorEnum.ADDITION;
            setCurrentOperator(newOperator);
            Log.d("Test5", "This works. New Operator = " + newOperator.getSymbol());
        } else if (operator.matches(OperatorEnum.SUBTRACTION.getSymbol())) {
            OperatorEnum newOperator = OperatorEnum.SUBTRACTION;
            setCurrentOperator(newOperator);
            Log.d("Test5", "This works. New Operator = " + newOperator.getSymbol());
        } else if (operator.matches(OperatorEnum.MULTIPLICATION.getSymbol())) {
            OperatorEnum newOperator = OperatorEnum.MULTIPLICATION;
            setCurrentOperator(newOperator);
            Log.d("Test5", "This works. New Operator = " + newOperator.getSymbol());
        } else if (operator.matches(OperatorEnum.DIVISION.getSymbol())) {
            OperatorEnum newOperator = OperatorEnum.DIVISION;
            setCurrentOperator(newOperator);
            Log.d("Test5", "This works. New Operator = " + newOperator.getSymbol());
        }
        else if (operator.matches(OperatorEnum.SQUAREROOT.getSymbol())) {
            OperatorEnum newOperator = OperatorEnum.SQUAREROOT;
            setCurrentOperator(newOperator);
            Log.d("Test5", "This works. New Operator = " + newOperator.getSymbol());
        }
    }

    public static void handleEqualClick(){

        OperatorEnum currentOperator = getCurrentOperator();
        BigDecimal leftOperand = getLeftOperand();
        BigDecimal rightOperand = getRightOperand();

        if(getCalculatorState() == CalculatorState.RESULT){
            if(currentOperator == OperatorEnum.SQUAREROOT){
                double doubleResult = Math.sqrt(leftOperand.doubleValue());
                BigDecimal result = BigDecimal.valueOf(doubleResult);
                Log.d("Test6", "Here is the result: " + result);
            }
            else if(currentOperator == OperatorEnum.SUBTRACTION){
                BigDecimal result = leftOperand.subtract(rightOperand);
                Log.d("Test6", "Here is the result: " + result);
            }
            else if(currentOperator == OperatorEnum.ADDITION){
                BigDecimal result = leftOperand.add(rightOperand);
                Log.d("Test6", "Here is the result: " + result);
            }
            else if(currentOperator == OperatorEnum.MULTIPLICATION){
                BigDecimal result = leftOperand.multiply(rightOperand);
                Log.d("Test6", "Here is the result: " + result);
            }
            else if(currentOperator == OperatorEnum.DIVISION){
                BigDecimal result = leftOperand.divide(rightOperand);
                Log.d("Test6", "Here is the result: " + result);
            }

        }

    }

    public static void handleClearClick() {
        // Handle the click of the clear button
        resetCalculator();
    }


    private static void resetCalculator() {
        // Reset the calculator to its initial state
        leftOperand = BigDecimal.ZERO;
        rightOperand = BigDecimal.ZERO;
        setCalculatorState(CalculatorState.CLEAR);
    }

}
