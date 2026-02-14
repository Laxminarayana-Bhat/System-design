package org.example.sort;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Bubble {

    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] <= arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        Arrays.stream(arr).boxed().forEach(System.out::println);
    }
}
