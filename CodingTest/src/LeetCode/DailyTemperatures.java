package LeetCode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * Daily Temperatures
 * link : https://leetcode.com/problems/daily-temperatures/
 */

public class DailyTemperatures {
    public static void main(String args[]){
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for(int i=0; i<temperatures.length; i++){
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int day = stack.pop();
                result[day] = i - day;
            }
            stack.push(i);
        }

        return result;
    }
}
