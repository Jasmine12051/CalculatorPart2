package edu.jsu.mcis.cs408.calculator;

public enum OperatorEnum {
    ADDITION("\\+"),
    SUBTRACTION("-"),
    MULTIPLICATION("×"),
    DIVISION("÷");

    private String symbol;

    OperatorEnum(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return symbol;
    }

}
