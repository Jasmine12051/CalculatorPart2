package edu.jsu.mcis.cs408.calculator;

import android.util.Log;
import java.math.BigDecimal;

public class calculatorModel extends AbstractModel {

    private static final int MAX_DISPLAY_LENGTH = 10;
    private String text1;
    private static BigDecimal leftOperand;
    private static BigDecimal rightOperand;
    private static CalculatorState calculatorState;
    private static OperatorEnum currentOperator;

    public calculatorModel() {
        // Initialize default values
        leftOperand = BigDecimal.ZERO;
        rightOperand = BigDecimal.ZERO;
        calculatorState = CalculatorState.CLEAR;
        currentOperator = OperatorEnum.ADDITION;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String newText) {
        String oldText = this.text1;
        this.text1 = newText;

        Log.d("TAG", "Text1 Change: From " + oldText + " to " + newText);

        firePropertyChange(calculatorController.ELEMENT_TEXTVIEW1_PROPERTY, oldText, newText);
    }

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
            case "%":
                setCalculatorState(CalculatorState.RESULT);
                handlePercentClick();
                break;
            case ".":
                handleDigitClick(buttonTag);
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
            // Use StringBuilder to handle appending digits
            StringBuilder lhsStringBuilder = new StringBuilder(getLeftOperand().toString());

            // Check if the digit is a decimal point and if one is already present
            if (digit.equals(".") && lhsStringBuilder.indexOf(".") == -1) {
                lhsStringBuilder.append(digit);
            }
            else if (!digit.equals(".")) {
                lhsStringBuilder.append(digit);
            }

            // Limit the length of the operand based on the display size
            if (lhsStringBuilder.length() <= MAX_DISPLAY_LENGTH) {
                BigDecimal newOperand = new BigDecimal(lhsStringBuilder.toString());
                setLeftOperand(newOperand);
                Log.d("Test3", "LeftHandOperand : " + getLeftOperand());
            }
        } else if (getCalculatorState() == CalculatorState.RHS) {
            // Use StringBuilder to handle appending digits
            StringBuilder rhsStringBuilder = new StringBuilder(getRightOperand().toString());

            // Check if the digit is a decimal point and if one is already present
            if (digit.equals(".") && rhsStringBuilder.indexOf(".") == -1) {
                rhsStringBuilder.append(digit);
            } else if (!digit.equals(".")) {
                rhsStringBuilder.append(digit);
            }

            // Limit the length of the operand based on the display size
            if (rhsStringBuilder.length() <= MAX_DISPLAY_LENGTH) {
                BigDecimal newOperand = new BigDecimal(rhsStringBuilder.toString());
                setRightOperand(newOperand);
                Log.d("Test4", "RightHandOperand : " + getRightOperand());
            }
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

        if (getCalculatorState() == CalculatorState.RESULT) {
            if (currentOperator == OperatorEnum.SQUAREROOT) {
                double doubleResult = Math.sqrt(leftOperand.doubleValue());
                BigDecimal result = BigDecimal.valueOf(doubleResult);
                Log.d("Test6", "Here is the result: " + result);
            }
            else {
                if (rightOperand.equals(BigDecimal.ZERO)) {
                    rightOperand = leftOperand;
                }

                BigDecimal result = BigDecimal.valueOf(0);
                if (currentOperator == OperatorEnum.SUBTRACTION) {
                    result = leftOperand.subtract(rightOperand);
                } else if (currentOperator == OperatorEnum.ADDITION) {
                    result = leftOperand.add(rightOperand);
                } else if (currentOperator == OperatorEnum.MULTIPLICATION) {
                    result = leftOperand.multiply(rightOperand);
                } else if (currentOperator == OperatorEnum.DIVISION) {
                    result = leftOperand.divide(rightOperand);
                }
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

    private static void handlePercentClick(){
        BigDecimal leftOperand = getLeftOperand();
        BigDecimal rightOperand = getRightOperand();
        if(getCalculatorState() == CalculatorState.RESULT) {
            BigDecimal value = rightOperand.divide(BigDecimal.valueOf(100));
            BigDecimal result = leftOperand.multiply(value);
            setRightOperand(result);
        }
    }
}
