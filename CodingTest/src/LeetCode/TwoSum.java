package LeetCode;

import java.util.*;

/**
 * Two Sum
 * link : https://leetcode.com/problems/two-sum/
 */

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum1(nums, target)));
        System.out.println(Arrays.toString(twoSum2(nums, target)));
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


}
