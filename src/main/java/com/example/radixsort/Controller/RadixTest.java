package com.example.radixsort.Controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RadixTest {

    @Test
    public void test1(){
        String result = RadixSort.formatString("1 2 3 5 7 ");
        String expected = "{ 1, 2, 3, 5, 7 }";
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        assertEquals(expected, result);
    }

}
