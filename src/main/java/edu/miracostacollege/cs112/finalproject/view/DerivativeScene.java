package edu.miracostacollege.cs112.finalproject.view;

import edu.miracostacollege.cs112.finalproject.controller.Controller;
import edu.miracostacollege.cs112.finalproject.model.Derivative;
import edu.miracostacollege.cs112.finalproject.model.Function;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextArea;

public class DerivativeScene extends Scene {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;

    private Scene previousScene;

    //Set text fields
    private TextField baseTF = new TextField();

    private TextField exponentTF = new TextField();

    private TextField scalarTF = new TextField();

    private TextField pointTF = new TextField();

    //error labels
    private Label exponentErrorLabel = new Label("Exponent is required.");
    private Label baseErrorLabel = new Label("Base must be one and only one letter.");
    private Label scalarErrorLabel = new Label("Scalar is required.");
    private Label pointErrorLabel = new Label("Point is required.");

    private Label derivativeAnswer = new Label("Derivative at the point: ");

    private Button calculateButton = new Button("Calculate");
    private Button cancelButton = new Button("Cancel");

    private TextField derivative = new TextField();

    private Controller mController = Controller.getInstance();

    public DerivativeScene(Scene previousScene) {
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

        pane.add(new Label("Point:"), 0, 3);
        pane.add(pointErrorLabel, 2, 3);
        pointErrorLabel.setTextFill(Color.RED);
        pointErrorLabel.setVisible(false);

        pane.add(baseTF, 1, 0);
        pane.add(exponentTF, 1, 1);
        pane.add(scalarTF, 1, 2);
        pane.add(pointTF, 1, 3);

        //Buttons
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().add(calculateButton);
        hBox.getChildren().add(cancelButton);
        pane.add(calculateButton, 1, 4);
        pane.add(cancelButton, 2, 4);

        pane.add(derivativeAnswer, 1, 5);
        pane.add(derivative, 2, 5);
        derivative.setVisible(true);

        calculateButton.setOnAction(e -> save());
        cancelButton.setOnAction(e -> goBackToPrevScene());

        this.setRoot(pane);
    }

    private void save() {
        String base = "";
        double exponent = 0.0, scalar = 0.0, point = 0.0;


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
            if (scalar == 0.0) {
                base = "";
                exponent = 0;
                scalar = 0;
            }
            scalarErrorLabel.setVisible(scalarTF.getText().isEmpty());
        } catch( NumberFormatException e ) {
            scalarErrorLabel.setVisible(true);
        }
        try {
            point = Double.parseDouble(pointTF.getText());
        } catch(NumberFormatException e) {
            pointErrorLabel.setVisible(true);
        }

        if (baseErrorLabel.isVisible() || exponentErrorLabel.isVisible() || scalarErrorLabel.isVisible())
            return;
        else
            derivative.setVisible(true);

        if(exponent == 0.0) {
            base = "";
            exponent = 1;
            scalar = 0;
            derivative.setText("0.0");
        }
        else if(scalar == 0) {
            base = "";
            exponent = 1;
            scalar = 0;
            derivative.setText("0.0");
        }
        else {
            scalar *= exponent;
            exponent -= 1;
            derivative.setText(String.valueOf(scalar*Math.pow(point, exponent)));
        }

        Controller.getInstance().addDerivative(new Derivative(new Function(base, exponent, scalar), point));

    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Calculus Calculator", previousScene);
    }

}
