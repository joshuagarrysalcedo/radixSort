package com.example.radixsort.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RadixSort {
    private RadixSort() {}

    public static String readTextFile(File file , String textFile) {
        String text = "";
        try{
            file = new File(textFile);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                text = sc.nextLine();
            }//end of while
            sc.close();

        }catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        return text;
    }//end of method


    public static void radixSort(int arr[], int n)
    {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    public static int []StringtoArray(String text){
        System.out.println(text);
        String arrOfStr[] = text.split(" ");
        int arrofInt[] = new int[arrOfStr.length];
        try {
            //convert array string to int
            for(int i = 0; i < arrOfStr.length; i++) {
                arrofInt[i] = Integer.parseInt(arrOfStr[i]);
            }
        }catch (NumberFormatException e) {
            System.out.println("Error is here!");
            e.printStackTrace();
        }finally {
            return arrofInt;
        }



    }//end of method
    public static String arrayToString(int arr[]) {
        String text = "";
        for(int i = 0; i < arr.length; i++) {
            text += Integer.toString(arr[i]) + " ";
        }
        return text;
    }//end of method

    public static void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current
        // digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    public static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    public static void saveTextToFile(String text, String fileName) {
        Path path = Paths.get(fileName);

        try {
            Files.writeString(path, text, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Invalid Path!");
        }
    }

    public static String randomString(int length, int min, int max) {
        String text = "";
        Random r = new Random();
        int max1 = (max - min) + 1;


        for(int i = 0; i < length; i++) {
            text += Integer.toString(r.nextInt(max1) + min) + " ";
        }

        return text;
    }

    public static String formatString(String text) {
        String regex = " ";
        String formattedString = text.trim().replaceAll(regex, ", ");
        formattedString = "{ " + formattedString +" }";

        return  formattedString;
    }
}
