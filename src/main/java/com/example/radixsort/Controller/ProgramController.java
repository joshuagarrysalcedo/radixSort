package com.example.radixsort;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProgramController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}