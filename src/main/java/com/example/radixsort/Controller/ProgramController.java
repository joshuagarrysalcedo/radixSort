package com.example.radixsort.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProgramController  implements Initializable {

    @FXML
    private Button chooseFileBtn;

    @FXML
    private Spinner<Integer> elementsTF;


    @FXML
    private Button generateBtn;

    @FXML
    private RadioButton generateNumbersRadioBtn;

    @FXML
    private Button loadFileBtn;

    @FXML
    private Spinner<Integer> maxTF;

    @FXML
    private Spinner<Integer> minTF;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button radixSortBtn;

    @FXML
    private Button saveToInputBtn;

    @FXML
    private TextArea textArea;

    private static File f;

    private static String arrayToSort;

    private  final String currentDirectory = System.getProperty("user.dir");

    @FXML
    void chooseFileClicked(ActionEvent event) {
        System.out.println("Button clicked");
        System.out.println(currentDirectory);
        FileChooser fc = new FileChooser();

        f = new File(currentDirectory);
        fc.setInitialDirectory(f);
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        f = fc.showOpenDialog(null);
        if(f != null) {
             arrayToSort = String.format("%s", f.getAbsoluteFile());
            f = new File(arrayToSort);
            textArea.setText("Selected File: " + arrayToSort);
            showAlert("File selection complete!!", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void loadFileClick(ActionEvent event) {
        System.out.println("Load button clicked");
        String text = RadixSort.readTextFile(f, arrayToSort);
        arrayToSort = text;
        textArea.setText(RadixSort.formatString(arrayToSort));
        showAlert("Load successful!", Alert.AlertType.INFORMATION);
    }

    @FXML
    void radixSortClick(ActionEvent event) {

        try{
            System.out.println(arrayToSort);
            int arr[] = RadixSort.StringtoArray(arrayToSort);

            RadixSort.radixSort(arr, arr.length);
            String output = RadixSort.arrayToString(arr);
            RadixSort.saveTextToFile(output, "output.txt");
            String finalOutput = String.format("UNSORTED ARRAY\n" +
                    "%s\nArray Sorted using radix sort\n%s", RadixSort.formatString(arrayToSort), RadixSort.formatString(output));
            System.out.println(finalOutput);
            textArea.setText(finalOutput);
            showAlert("Sort successful!", Alert.AlertType.INFORMATION);
        }catch (NullPointerException e) {
            showAlert("No file to sort is selected!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    @FXML
    void onGenerateClick(ActionEvent event) {
        System.out.println("Generate click");
        if(elementsTF != null && minTF != null && maxTF != null) {
            int length = elementsTF.getValue();
            int min = minTF.getValue();
            int max = maxTF.getValue();

            String randomString = RadixSort.randomString(length, min, max);
            arrayToSort = randomString;
            textArea.setText(RadixSort.formatString(arrayToSort));
            showAlert("Random numbers generated!", Alert.AlertType.INFORMATION);
        }

    }

    @FXML
    void onSaveToInputClick(ActionEvent event) {
        System.out.println("On Save click");
        RadixSort.saveTextToFile(arrayToSort, "input.txt");
        showAlert("Saved successful!", Alert.AlertType.INFORMATION);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            SpinnerValueFactory<Integer> valueOfElements = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
            valueOfElements.setValue(10);
            elementsTF.setValueFactory(valueOfElements);

            SpinnerValueFactory<Integer> valueOfMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
            valueOfMin.setValue(1);
            minTF.setValueFactory(valueOfMin);


            SpinnerValueFactory<Integer> valueOfMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(51,100);
            valueOfMax.setValue(100);
            maxTF.setValueFactory(valueOfMax);

            elementsTF.setEditable(true);
            minTF.setEditable(true);
            maxTF.setEditable(true);



            generateBtn.disableProperty().bind(generateNumbersRadioBtn.selectedProperty().not());
            elementsTF.disableProperty().bind(generateNumbersRadioBtn.selectedProperty().not());
            minTF.disableProperty().bind(generateNumbersRadioBtn.selectedProperty().not());
            maxTF.disableProperty().bind(generateNumbersRadioBtn.selectedProperty().not());
            saveToInputBtn.disableProperty().bind(generateNumbersRadioBtn.selectedProperty().not());


    }

    public void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type, message);
        alert.showAndWait();
    }
}