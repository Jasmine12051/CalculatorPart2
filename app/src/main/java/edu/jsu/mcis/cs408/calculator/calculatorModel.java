package edu.jsu.mcis.cs408.calculator;

import android.util.Log;

import java.math.BigDecimal;


public class calculatorModel {
    private BigDecimal leftOperand;
    private BigDecimal rightOperand;
    private BigDecimal currentOperand;
    private CalculatorState calculatorState;

    public calculatorModel() {
        // Initialize default values
        leftOperand = BigDecimal.ZERO;
        rightOperand = BigDecimal.ZERO;
        currentOperand = BigDecimal.ZERO;
        calculatorState = CalculatorState.CLEAR;
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


    public void setCalculatorState(CalculatorState state) {
        this.calculatorState = state;
    }

    public CalculatorState getCalculatorState() {
        return calculatorState;
    }

    public void handleDigitClick(String digit) {
        // Handle the click of a digit button
        BigDecimal currentOperand = getCurrentOperand();
        String updatedOperand = currentOperand.toString() + digit;
        BigDecimal newOperand = new BigDecimal(updatedOperand);
        setCurrentOperand(newOperand);
        Log.d("THIS WORKS", "NEW Operand " + currentOperand);
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
