package com.kaifei.algo.sort;


public class QuickSort {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 3, 9, 4, 5, 7};
        System.out.println("before:");
        printArr(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("\nAfter:");
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;


        // step1: choose pivot
        int pivot = high;

        int leftPointer = low;
        int rightPointer = high;

        // step2: partition
        while (leftPointer < rightPointer) {
            // leftPointer往右侧移动，直到它位置元素比pivot位置大
            while (arr[leftPointer] <= arr[pivot] && leftPointer < rightPointer) {
                leftPointer++;
            }


            // rightPointer往左侧移动，直到它位置元素比pivot位置小
            while (arr[rightPointer] >= arr[pivot] && leftPointer < rightPointer) {
                rightPointer--;
            }

            // 交换lp和rp
            swap(arr, leftPointer, rightPointer);

        }
        // 将lp与pivot进行交换
        swap(arr, leftPointer, pivot);

        quickSort(arr, low, leftPointer - 1);
        quickSort(arr, leftPointer + 1, high);

    }

    private static void swap(int[] arr, int leftPointer, int rightPointer) {
        int temp = arr[rightPointer];
        arr[rightPointer] = arr[leftPointer];
        arr[leftPointer] = temp;
    }
}
