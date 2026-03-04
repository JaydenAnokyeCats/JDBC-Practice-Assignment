package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(getClass());
        System.out.println(getClass().getResource("/demo/hello-view.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/demo/hello-view.fxml"));

        stage.setTitle("Hello");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}