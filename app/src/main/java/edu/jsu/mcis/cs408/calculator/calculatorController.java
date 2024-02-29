package edu.jsu.mcis.cs408.calculator;


public class calculatorController extends AbstractController {

    private calculatorModel calculatorModel;
    private MainActivity mainActivity;

    public calculatorController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        calculatorModel = new calculatorModel();
    }

    public static final String ELEMENT_TEXTVIEW1_PROPERTY = "0";

    public void changeElementTextview1(String newText) {
        setModelProperty(ELEMENT_TEXTVIEW1_PROPERTY, newText);
    }

}
