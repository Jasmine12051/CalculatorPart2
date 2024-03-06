package edu.jsu.mcis.cs408.calculator;

import android.util.Log;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculatorModel extends AbstractModel {

    private static final int MAX_DISPLAY_LENGTH = 27;
    private BigDecimal leftOperand;
    private BigDecimal rightOperand;

    private BigDecimal result;

    private static CalculatorState calculatorState;
    private static OperatorEnum currentOperator;

    private boolean decimalEntered = false;
    private boolean decimalNext = false;


    public CalculatorModel() {
        // Initialize default values
        leftOperand = BigDecimal.ZERO;
        rightOperand = BigDecimal.ZERO;
        calculatorState = CalculatorState.CLEAR;
        currentOperator = OperatorEnum.ADDITION;
        result = BigDecimal.ZERO;
        decimalEntered = decimalNext = false;
    }



    public void setLeftOperand(BigDecimal value) {

        String oldValue = leftOperand.toString();

        leftOperand = value;
        String newValue = leftOperand.toString();

        firePropertyChange(CalculatorController.ELEMENT_NEWKEY_PROPERTY, oldValue, newValue);

    }

    public BigDecimal getLeftOperand() {
        return leftOperand;
    }

    public void setRightOperand(BigDecimal value) {

        String oldValue = rightOperand.toString();

        rightOperand = value;
        String newValue = rightOperand.toString();

        firePropertyChange(CalculatorController.ELEMENT_NEWKEY_PROPERTY, oldValue, newValue);

    }

    public BigDecimal getRightOperand() {
        return rightOperand;
    }

    public OperatorEnum getCurrentOperator(){
        return currentOperator;
    }

    public void setCurrentOperator(OperatorEnum newOperator){
        currentOperator = newOperator;
    }


    public void setCalculatorState(CalculatorState state) {

        calculatorState = state;

        decimalEntered = decimalNext = false;

    }

    public CalculatorState getCalculatorState() {
        return calculatorState;
    }

    public void setKey(String buttonTag) {

        switch (getCalculatorState()) {
            case CLEAR:
                setCalculatorState(CalculatorState.LHS);
                break;
            case OP_SELECTED:
                if (currentOperator.getSymbol().matches(OperatorEnum.SQUAREROOT.getSymbol())) {
                    setCalculatorState(CalculatorState.RESULT);
                } else {
                    setRightOperand(BigDecimal.ZERO);
                    setCalculatorState(CalculatorState.RHS);
                }
                break;
            case ERROR:
                String message = "ERROR";
                setError(message);
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
                if(getCalculatorState() == CalculatorState.RHS || getCalculatorState() == CalculatorState.RESULT){
                    setCalculatorState(CalculatorState.RESULT);
                    handleEqualClick();
                    setLeftOperand(result);
                    setCalculatorState(CalculatorState.OP_SELECTED);
                }
                setCalculatorState(CalculatorState.OP_SELECTED);
                handleOperatorButtonClick(buttonTag);
                break;
            case "%":
                setCalculatorState(CalculatorState.RESULT);
                handlePercentClick();
                break;
            case ".":
                handleDigitClick(buttonTag);
                break;
            case "±":
                handleSignClick();
            default:
                if (buttonTag.matches("[0-9]")) {
                    handleDigitClick(buttonTag);
                }
                break;
        }
    }

    public void handleDigitClick(String digit) {
        // Handle the click of a digit button
        if (getCalculatorState() == CalculatorState.LHS) {
            // Use StringBuilder to handle appending digits
            StringBuilder lhsStringBuilder = new StringBuilder(getLeftOperand().toString());

            // Check if the digit is a decimal point and if one is already present
            if (digit.equals(".") && (!decimalEntered) ) {
                Log.d("Test3", "Decimal Entered!");
                lhsStringBuilder.append(digit);
                decimalEntered = decimalNext = true;
            }
            else if (!digit.equals(".")) {
                if (decimalNext) {
                    lhsStringBuilder.append(".");
                }
                lhsStringBuilder.append(digit);
                decimalNext = false;
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
            if (digit.equals(".") && (!decimalEntered) ) {
                Log.d("Test3", "Decimal Entered!");
                rhsStringBuilder.append(digit);
                decimalEntered = decimalNext = true;
            } else if (!digit.equals(".")) {
                if (decimalNext) {
                    rhsStringBuilder.append(".");
                }
                rhsStringBuilder.append(digit);
                decimalNext = false;
            }

            // Limit the length of the operand based on the display size
            if (rhsStringBuilder.length() <= MAX_DISPLAY_LENGTH) {
                BigDecimal newOperand = new BigDecimal(rhsStringBuilder.toString());
                setRightOperand(newOperand);
                Log.d("Test4", "RightHandOperand : " + getRightOperand());
            }
        }
    }

    public void handleOperatorButtonClick(String operator) {
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

    public void handleEqualClick(){

        OperatorEnum currentOperator = getCurrentOperator();
        BigDecimal leftOperand = getLeftOperand();
        BigDecimal rightOperand = getRightOperand();

        if (getCalculatorState() == CalculatorState.RESULT) {
            Log.d("Test6", "Getting result ...");
            if (currentOperator == OperatorEnum.SQUAREROOT) {
                double doubleResult = Math.sqrt(leftOperand.doubleValue());
                setResult(BigDecimal.valueOf(doubleResult));
                Log.d("Test6", "Here is the result: " + result);
            }
                if (currentOperator == OperatorEnum.SUBTRACTION) {
                    setResult(leftOperand.subtract(rightOperand));
                } else if (currentOperator == OperatorEnum.ADDITION) {
                    setResult(leftOperand.add(rightOperand));
                } else if (currentOperator == OperatorEnum.MULTIPLICATION) {
                    setResult(leftOperand.multiply(rightOperand));
                } else if (currentOperator == OperatorEnum.DIVISION) {
                    if(rightOperand.equals(BigDecimal.ZERO)){
                        setCalculatorState(CalculatorState.ERROR);
                    }
                    else {
                        setResult(leftOperand.divide(rightOperand, new MathContext(16, RoundingMode.HALF_UP)));
                    }
                }
                Log.d("Test6", "Here is the result: " + result);
            }
        }

    private void setResult(BigDecimal newResult) {

        String oldValue = result.toString();

        this.result = newResult;
        String newValue = result.toString();

        firePropertyChange(CalculatorController.ELEMENT_NEWKEY_PROPERTY, oldValue, newValue);

    }

    private void setError(String errorMessage) {

        String oldValue = result.toString();
        String newValue = errorMessage;

        firePropertyChange(CalculatorController.ELEMENT_NEWKEY_PROPERTY, oldValue, newValue);
    }

    public void handleClearClick() {
        // Handle the click of the clear button
        resetCalculator();
    }


    private void resetCalculator() {

        // Reset the calculator to its initial state

        setLeftOperand(BigDecimal.ZERO);
        rightOperand = BigDecimal.ZERO;
        decimalEntered = decimalNext = false;

        setCalculatorState(CalculatorState.CLEAR);

    }

    private void handlePercentClick(){
        BigDecimal leftOperand = getLeftOperand();
        BigDecimal rightOperand = getRightOperand();
        if(getCalculatorState() == CalculatorState.RESULT) {

            BigDecimal value = rightOperand.divide(BigDecimal.valueOf(100));
            BigDecimal result = leftOperand.multiply(value);

            setRightOperand(result);
        }
    }

    private void handleSignClick(){
        leftOperand = getLeftOperand();
        rightOperand = getRightOperand();
        if(getCalculatorState() == CalculatorState.LHS){
            setLeftOperand(leftOperand.negate());
            Log.d("TESTING", "this is the state : " + getCalculatorState());
        }

        if(getCalculatorState() == CalculatorState.RHS){
            setRightOperand(rightOperand.negate());
        }
    }
}
