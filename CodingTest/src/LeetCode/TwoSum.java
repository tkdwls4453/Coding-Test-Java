package LeetCode;

import java.util.*;

/**
 * Two Sum
 * link : https://leetcode.com/problems/two-sum/
 */

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum1(nums, target)));
        System.out.println(Arrays.toString(twoSum2(nums, target)));
        System.out.println(Arrays.toString(twoSum3(nums, target)));
        System.out.println(Arrays.toString(twoSum4(nums, target)));
    }

    /**
     * 반복문을 이용한 풀이
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 재귀를 이용한 풀이
     */
    public static int[] twoSum2(int[] nums, int target){
        return backtracking(nums, target, 0, new ArrayList<Integer>());
    }
    public static int[] backtracking(int[] nums, int target, int start, ArrayList<Integer> answer){
        if (answer.size() == 2) {
            if (nums[answer.get(0)] + nums[answer.get(1)] == target) {
                return new int[]{answer.get(0), answer.get(1)};
            }
            return null;
        }

        for (int i = start; i < nums.length; i++) {
            answer.add(i);
            int[] backtracking = backtracking(nums, target, i + 1, answer);
            if (backtracking != null) {
                return backtracking;
            }
            answer.remove(answer.size() - 1);
        }
        return null;
    }

    /**
     * 투포인터를 활용한 풀이
     */
    public static int[] twoSum3(int[] nums, int target) {
        int[][] arr = new int[nums.length][2];
        int left = 0;
        int right = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        while (left < right) {
            if (arr[left][0] + arr[right][0] < target) {
                left++;
            } else if (arr[left][0] + arr[right][0] > target) {
                right--;
            }else{
                return new int[]{arr[left][1], arr[right][1]};
            }
        }
        return new int[]{-1, -1};
    }


    /**
     * HashMap 이용한 성능 최적화
     */
    public static int[] twoSum4(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int needed = target - nums[i];
            if(map.containsKey(needed)){
                return new int[]{map.get(needed), i};
            }else{
                map.put(nums[i], i);
            }
        }

        return new int[]{-1, -1};
    }
}
