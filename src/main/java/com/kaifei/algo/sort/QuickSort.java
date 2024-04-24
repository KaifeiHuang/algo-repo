package com.kaifei.algo.sort;


/**
 * 思想： 采用了二分的思想
 *
 * 时间复杂度:  O(NlogN)
 * 空间复杂度： O(1)
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 8, -3, 9, 4, 5, 7};
        System.out.println("before:");
        printArr(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("\nAfter:");
        printArr(arr);
    }


    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;

        // 找基准元素
        int pivot = arr[high];

        // 分区
        int lp = partition(arr, low, high, pivot);

        // 递归排序
        quickSort(arr, low, lp - 1);
        quickSort(arr, lp + 1, high);
    }

    private static int partition(int[] arr, int low, int high, int pivot) {
        int lp = low;
        int rp = high;

        while (lp < rp) {
            // lp往右移动
            while (arr[lp] <= pivot && lp < rp) {
                lp++;
            }
            //rp往左移动
            while (arr[rp] >= pivot && lp < rp) {
                rp--;
            }
            // lp比pivot大，rp比pivot小，交换
            swap(arr, lp, rp);
        }
        // lp到rp位置，lp与pivot交换
        swap(arr, lp, high);
        return lp;
    }


    private static void swap(int[] arr, int leftPointer, int rightPointer) {
        int temp = arr[rightPointer];
        arr[rightPointer] = arr[leftPointer];
        arr[leftPointer] = temp;
    }


    private static void printArr(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}
