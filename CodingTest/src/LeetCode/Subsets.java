package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsets
 * link : https://leetcode.com/problems/subsets/
 */

public class Subsets {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>(new ArrayList<>());
        backtrack(nums, 0, new ArrayList<Integer>(), answer);
        return answer;
    }

    public static void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> answer){
        answer.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1, path, answer);
            path.remove(path.size() - 1);
        }
    }
}
