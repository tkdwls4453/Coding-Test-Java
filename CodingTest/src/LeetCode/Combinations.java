package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Combinations
 * link : https://leetcode.com/problems/combinations/
 */

public class Combinations {
    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        System.out.println(combine(n, 2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>(new ArrayList<>());
        backtrack(1, n, k, new ArrayList<Integer>(), answer);
        return answer;
    }

    public static void backtrack(int start, int n, int k, List<Integer> cur, List<List<Integer>> answer) {
        if (cur.size() == k) {
            answer.add(new ArrayList<>(cur));
        }

        for (int i = start; i <= n; i++) {
            if (!cur.contains(i)) {
                cur.add(i);
                backtrack(i+1, n, k, cur, answer);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
