package com.example.radixsort.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class ProgramController {

    @FXML
    private Button chooseFileBtn;

    @FXML
    private TextField elementsTF;

    @FXML
    private Button generateBtn;

    @FXML
    private RadioButton generateNumbersRadioBtn;

    @FXML
    private Button loadFileBtn;

    @FXML
    private TextField maxTF;

    @FXML
    private TextField minTF;

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
        }
    }

    @FXML
    void loadFileClick(ActionEvent event) {
        System.out.println("Load button clicked");
        String text = RadixSort.readTextFile(f, arrayToSort);
        arrayToSort = text;
        textArea.setText(RadixSort.formatString(arrayToSort));
    }

    @FXML
    void radixSortClick(ActionEvent event) {
        System.out.println(arrayToSort);
        int arr[] = RadixSort.StringtoArray(arrayToSort);

        RadixSort.radixSort(arr, arr.length);
        String output = RadixSort.arrayToString(arr);
        RadixSort.saveTextToFile(output, "output.txt");
        String finalOutput = String.format("UNSORTED ARRAY\n" +
                "%s\nArray Sorted using radix sort\n%s", RadixSort.formatString(arrayToSort), RadixSort.formatString(output));
        System.out.println(finalOutput);
        textArea.setText(finalOutput);
    }

    @FXML
    void onGenerateClick(ActionEvent event) {
        System.out.println("Generate click");
        if(elementsTF != null && minTF != null && maxTF != null) {
            int length = Integer.parseInt(elementsTF.getText());
            int min = Integer.parseInt(minTF.getText());
            int max = Integer.parseInt(maxTF.getText());

            String randomString = RadixSort.randomString(length, min, max);
            arrayToSort = randomString;
            textArea.setText(RadixSort.formatString(arrayToSort));
        }

    }

    @FXML
    void onSaveToInputClick(ActionEvent event) {
        System.out.println("On Save click");
        RadixSort.saveTextToFile(arrayToSort, "input.txt");
    }

}