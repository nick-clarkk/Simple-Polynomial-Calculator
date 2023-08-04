package edu.miracostacollege.cs112.finalproject.view;

import edu.miracostacollege.cs112.finalproject.controller.Controller;
import edu.miracostacollege.cs112.finalproject.model.Function;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class MainScene extends Scene {


    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;
    private Button addDerivative = new Button("Compute Derivative");
    private Button addIntegral = new Button("Compute Integral");
    private Button removeButton = new Button("Remove Function");
    private ListView<Function> functionsLV = new ListView<>();
    private Controller mController = Controller.getInstance();
    private ObservableList<Function> functionList;
    private Function selectedFunction;

    public MainScene() {
        super(new GridPane(), WIDTH, HEIGHT);

        //Creating pane and setting gaps for cleaner spaces
        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(15);
        pane.setPadding(new Insets(5));

        //Buttons
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(addDerivative);
        hBox.getChildren().add(addIntegral);
        //Not centered
        pane.add(addDerivative, 2, 2);
        pane.add(addIntegral, 3, 2);
        pane.add(removeButton, 4, 2);

        addDerivative.setOnAction(e -> NewDerivative());
        addIntegral.setOnAction(e -> NewIntegral());
        removeButton.setOnAction(e -> removeFunction());

        //List view
        pane.add(functionsLV, 0, 4, 6, 1);
        functionList = mController.getAllFunctions();
        functionsLV.setItems(functionList);
        functionsLV.setPrefWidth(WIDTH);
        functionsLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelectedFunction(newVal));

        //Set MainScene as default pane
        this.setRoot(pane);
    }

    private void updateSelectedFunction(Function newVal) {
        selectedFunction = newVal;
        removeButton.setDisable(selectedFunction == null);
    }

    private void removeFunction() {
        if (selectedFunction == null)
            return;
        functionList.remove(selectedFunction);
        functionsLV.getSelectionModel().select(-1);
    }

    private void NewDerivative() {
        ViewNavigator.loadScene("Derivative Scene", new DerivativeScene(this));
    }


    private void NewIntegral() {
        ViewNavigator.loadScene("Integral Calculator", new IntegralScene(this));
    }
}
