package com.example.radixsort;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("Program.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 728);
            scene.getStylesheets().add("/style.css");
            stage.setTitle("Radix sort!");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}