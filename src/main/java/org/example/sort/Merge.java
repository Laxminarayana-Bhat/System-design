package org.example.sort;

public class Merge {
    int left;
    int right;
    int[] arr;

    public Merge(int left, int right, int[] arr) {
        this.left = left;
        this.right = right;
        this.arr = arr;
    }

    public static void mergeSort(int left, int right, int[] arr) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(0, mid, arr);
            mergeSort(mid + 1, right, arr);

            merge(arr, left, right);
        }
    }

    public static void merge(int[] arr, int left, int right) {
        int[] temp = new int[left + right + 1];
        int mid = (left + right) / 2;
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        for (int idx = 0; idx < temp.length; idx++) {
            arr[idx + left] = temp[idx];
        }
    }
}
