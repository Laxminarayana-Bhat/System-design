package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 0, 0};
        //op 6
        System.out.println(maxSubArrayZero(nums));

    }

    public static int maxSubArrayZero(int[] nums) {
        int max = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//required for all the subarrays starting with 0 index, as sum will be 0, we need to calculate i- (-1) so it will give correct value

        for (int i = 0; i < nums.length; i++) { 
            sum += (nums[i] == 0) ? -1 : 1;
            if (map.containsKey(sum)) {
                max = Math.max(max, i - map.get(sum));
            }
            if (map.containsKey(sum - 2)) {
                max = Math.max(max, i - map.get(sum - 2));
            }
            if (map.containsKey(sum + 2)) {
                max = Math.max(max, i - map.get(sum + 2));
                System.out.println(max + "--max");
            }
            map.putIfAbsent(sum, i);
        }
        return max;
    }

    //nums = [1, 0, 1, 1, 0, 0]
    //
    //Mapping:
    //1 → '('
    //0 → ')'
    //
    //Sequence:
    //() (())


}