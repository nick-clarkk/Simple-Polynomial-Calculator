package edu.miracostacollege.cs112.finalproject.view;

import edu.miracostacollege.cs112.finalproject.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class View extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Calculus Calculator", new MainScene());
    }

    @Override
    public void stop() throws Exception {
        Controller.getInstance().saveData();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

//DONE