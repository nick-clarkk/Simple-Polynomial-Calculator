package edu.miracostacollege.cs112.finalproject.view;

import edu.miracostacollege.cs112.finalproject.controller.Controller;
import edu.miracostacollege.cs112.finalproject.model.Function;
import edu.miracostacollege.cs112.finalproject.model.Integral;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class IntegralScene extends Scene {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;

    private Scene previousScene;
    //Set text fields
    private TextField baseTF = new TextField();

    private TextField exponentTF = new TextField();
    private TextField scalarTF = new TextField();
    private TextField lowerTF = new TextField();
    private TextField upperTF = new TextField();

    private Button calculateButton = new Button("Calculate");
    private Button closeButton = new Button("Close");

    //error labels
    private Label exponentErrorLabel = new Label("Exponent is required.");
    private Label baseErrorLabel = new Label("Base must be one and only one letter.");
    private Label scalarErrorLabel = new Label("Scalar is required.");
    private Label lowerErrorLabel = new Label("Lower bound is required.");
    private Label upperErrorLabel = new Label("Upper bound is required.");

    private Label integralAnswer = new Label("Integral over the interval:");
    private TextField integral = new TextField();

    private Controller mController = Controller.getInstance();

    public IntegralScene(Scene previousScene) {
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(15);
        pane.setPadding(new Insets(5));
        pane.setAlignment(Pos.CENTER);

        //Input and Error labels
        pane.add(new Label("Base:"), 0, 0);
        pane.add(baseErrorLabel, 2, 0);
        baseErrorLabel.setTextFill(Color.RED);
        baseErrorLabel.setVisible(false);

        pane.add(new Label("Exponent:"), 0, 1);
        pane.add(exponentErrorLabel, 2, 1);
        exponentErrorLabel.setTextFill(Color.RED);
        exponentErrorLabel.setVisible(false);

        pane.add(new Label("Scalar:"), 0, 2);
        pane.add(scalarErrorLabel, 2, 2);
        scalarErrorLabel.setTextFill(Color.RED);
        scalarErrorLabel.setVisible(false);

        pane.add(new Label("Lower Bound:"), 0 , 3);
        pane.add(lowerErrorLabel, 2, 3);
        lowerErrorLabel.setTextFill(Color.RED);
        lowerErrorLabel.setVisible(false);

        pane.add(new Label("Upper Bound:"), 0, 4);
        pane.add(upperErrorLabel, 2, 4);
        upperErrorLabel.setTextFill(Color.RED);
        upperErrorLabel.setVisible(false);

        pane.add(baseTF, 1, 0);
        pane.add(exponentTF, 1, 1);
        pane.add(scalarTF, 1, 2);
        pane.add(lowerTF, 1, 3);
        pane.add(upperTF, 1, 4);

        //Save and cancel button
        pane.add(calculateButton, 1, 5);
        pane.add(closeButton, 2, 5);

        pane.add(integralAnswer, 1, 6);
        pane.add(integral, 2, 6);
        integral.setVisible(true);

        calculateButton.setOnAction(e -> compute());
        closeButton.setOnAction(e -> goBackToPrevScene());

        this.setRoot(pane);

    }
    private void compute() {
        System.out.println("ENTERED COMPUTE METHOD");
        String base = "";
        double exponent = 0, scalar = 0, lower = 0, upper = 0;


        base = baseTF.getText();
        baseErrorLabel.setVisible((base.length() != 1));

        try {
            exponent = Double.parseDouble(exponentTF.getText());
            exponentErrorLabel.setVisible(exponentTF.getText().isEmpty());
        } catch (NumberFormatException e) {
            exponentErrorLabel.setVisible(true);
        }

        try {
            scalar = Double.parseDouble(scalarTF.getText());
            scalarErrorLabel.setVisible(scalarTF.getText().isEmpty());
        } catch( NumberFormatException e ) {
            scalarErrorLabel.setVisible(true);
        }

        try {
            lower = Double.parseDouble(lowerTF.getText());
            lowerErrorLabel.setVisible(lowerErrorLabel.getText().isEmpty());
        } catch(NumberFormatException e) {
            lowerErrorLabel.setVisible(true);
        }

        try {
            upper = Double.parseDouble(upperTF.getText());
            upperErrorLabel.setVisible(upperErrorLabel.getText().isEmpty());
        } catch(NumberFormatException e) {
            upperErrorLabel.setVisible(true);
        }

        if (baseErrorLabel.isVisible() || exponentErrorLabel.isVisible() || scalarErrorLabel.isVisible()
                || lowerErrorLabel.isVisible() || upperErrorLabel.isVisible())
            return;


        if(exponent == -1) {
            base = "ln(x)";
            exponent = 1;
            integral.setText("ln(x)");
        }
        else if(scalar == 0) {
            base = "C";
            exponent = 1;
            scalar = 1;
            integral.setText("C");
        }
        else {
            scalar /= (exponent + 1);
            exponent += 1;
            integral.setText(String.valueOf(scalar*(Math.pow(upper, exponent) - Math.pow(lower, exponent))));
        }


        Controller.getInstance().addIntegral(new Integral(new Function(base, exponent, scalar), lower, upper));

    }



    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Calculus Calculator", previousScene);
    }
}
