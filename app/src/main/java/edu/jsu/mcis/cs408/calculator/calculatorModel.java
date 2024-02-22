package edu.jsu.mcis.cs408.calculator;

import android.util.Log;

import java.math.BigDecimal;


public class calculatorModel {
    private BigDecimal leftOperand;
    private BigDecimal rightOperand;
    private BigDecimal currentOperand;
    private CalculatorState calculatorState;

    private OperatorEnum currentOperator;

    public calculatorModel() {
        // Initialize default values
        leftOperand = BigDecimal.ZERO;
        rightOperand = BigDecimal.ZERO;
        currentOperand = BigDecimal.ZERO;
        calculatorState = CalculatorState.CLEAR;
        currentOperator = OperatorEnum.ADDITION;
    }

    // Getter and setter methods for leftOperand, rightOperand, currentOperator, calculatorState

    public void setLeftOperand(BigDecimal value) {
        this.leftOperand = value;
    }

    public BigDecimal getLeftOperand() {
        return leftOperand;
    }

    public void setRightOperand(BigDecimal value) {
        this.rightOperand = value;
    }

    public BigDecimal getRightOperand() {
        return rightOperand;
    }

    public BigDecimal getCurrentOperand() {
        return currentOperand;
    }

    public void setCurrentOperand(BigDecimal newOperand) {
        this.currentOperand = newOperand;
    }

    public OperatorEnum getCurrentOperator(){
        return currentOperator;
    }

    public void setCurrentOperator(OperatorEnum newOperator){
        this.currentOperator = newOperator;
    }


    public void setCalculatorState(CalculatorState state) {
        this.calculatorState = state;
    }

    public CalculatorState getCalculatorState() {
        return calculatorState;
    }

    public void handleDigitClick(String digit) {
        // Handle the click of a digit button
        if(getCalculatorState() == CalculatorState.LHS){
            BigDecimal leftOperand = getLeftOperand();
            String updatedOperand = leftOperand.toString() + digit;
            BigDecimal newOperand = new BigDecimal(updatedOperand);
            setLeftOperand(newOperand);
            Log.d("Test3", "LeftHandOperand : " + getLeftOperand());
        }
        else if(getCalculatorState() == CalculatorState.RHS){
            BigDecimal rightOperand = getRightOperand();
            String updatedOperand = rightOperand.toString() + digit;
            BigDecimal newOperand = new BigDecimal(updatedOperand);
            setRightOperand(newOperand);
            Log.d("Test4", "RightHandOperand : " + getRightOperand());
        }
    }

    public void handleOperatorButtonClick(String operator){
        if(operator.matches(OperatorEnum.SUBTRACTION.getSymbol())){
            OperatorEnum newOperator = OperatorEnum.SUBTRACTION;
            setCurrentOperator(newOperator);
            Log.d("Test5", "This works. New Operator = " + newOperator.getSymbol());
        }
        else if(operator.matches(OperatorEnum.MULTIPLICATION.getSymbol())){
            OperatorEnum newOperator = OperatorEnum.MULTIPLICATION;
            setCurrentOperator(newOperator);
            Log.d("Test5", "This works. New Operator = " + newOperator.getSymbol());
        }
        else if(operator.matches(OperatorEnum.MULTIPLICATION.getSymbol())){
            OperatorEnum newOperator = OperatorEnum.DIVISION;
            setCurrentOperator(newOperator);
            Log.d("Test5", "This works. New Operator = " + newOperator.getSymbol());
        }
        else{
            getCurrentOperator();
        }
    }

    public void handleEqualClick(){
        if(getCalculatorState() == CalculatorState.RESULT){

            Log.d("Test6", "THIS RUNS");

        }

    }

    public void handleClearClick() {
        // Handle the click of the clear button
        resetCalculator();
    }


    private void resetCalculator() {
        // Reset the calculator to its initial state
        leftOperand = BigDecimal.ZERO;
        rightOperand = BigDecimal.ZERO;
        setCalculatorState(CalculatorState.CLEAR);
    }

}
