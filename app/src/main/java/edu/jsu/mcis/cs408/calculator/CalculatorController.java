package edu.jsu.mcis.cs408.calculator;


public class CalculatorController extends AbstractController {

    public CalculatorController() {
        super();
    }

    public static final String ELEMENT_TEXTVIEW1_PROPERTY = "0";
    public static final String ELEMENT_NEWKEY_PROPERTY = "Key";

    public void changeElementTextview1(String newText) {
        setModelProperty(ELEMENT_TEXTVIEW1_PROPERTY, newText);
    }

    public void changeElementNewKey(String newKey) {
        setModelProperty(ELEMENT_NEWKEY_PROPERTY, newKey);
    }

}
