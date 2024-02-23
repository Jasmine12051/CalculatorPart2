package edu.jsu.mcis.cs408.calculator;


public class calculatorController extends AbstractController {

    private calculatorModel calculatorModel;
    private MainActivity mainActivity;

    public calculatorController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        calculatorModel = new calculatorModel();
    }

}
