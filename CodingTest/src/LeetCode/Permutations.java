package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutations
 * link : https://leetcode.com/problems/permutations/
 */
public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>(new ArrayList<>());
        backtack(nums, new ArrayList<Integer>(), answer);
        return answer;
    }

    public static void backtack(int[] nums, List<Integer> cur, List<List<Integer>> answer) {
        if (cur.size() == nums.length) {
            answer.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!cur.contains(nums[i])) {
                cur.add(nums[i]);
                backtack(nums, cur, answer);
                cur.remove(cur.size()-1);
            }
        }
    }
}
