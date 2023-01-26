package com.example.taas.utils;

public class TestingUtils {

    private static int count = 1;

    public static void printCaption(String content) {
        System.out.printf("\n@@@@@@@@@@@@@@@ TEST #%d - %s", count++, content + "@@@@@@@@@@@@@@@\n");
    }


}
