package com.kaifei.algo.Utils;

import java.util.Objects;

public class PrintUtils {



    public static void printIntArray(int[] arr){
        if (arr == null) {
            System.out.println("array is empty.");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


    public static void printArray(Object[] arr){
        if (Objects.isNull(arr)) {
            System.out.print("array is empty.");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
