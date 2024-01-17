package LeetCode;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Partition Array
 * link: https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
 */
public class PartitionArray {
    public static int min;
    public static void main(String[] args) {
        int[] nums = {-68,55,-23,13,-20,-14};
        System.out.println(minimumDifference(nums));
    }

    public static int minimumDifference(int[] nums) {
        min = Integer.MAX_VALUE;
        backtrack(nums, new ArrayList<Integer>());
        return min;
    }

    public static void backtrack(int[] nums, ArrayList<Integer> cur) {
        int n = nums.length / 2;
        int allSum = Arrays.stream(nums).sum();
        if (cur.size() == n) {
            System.out.println(nums[cur.get(0)] + " " + nums[cur.get(1)] + " " + nums[cur.get(2)]);
            int part1Sum = cur.stream().mapToInt(i -> nums[i]).sum();
            int part2Sum = allSum - part1Sum;
            min = Math.min(min, Math.abs(part1Sum - part2Sum));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!cur.contains(i) && cur.stream().mapToInt(x -> nums[x]).sum() + nums[i] <= allSum / 2 ) {
                cur.add(i);
                backtrack(nums, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }

}
