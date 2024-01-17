package LeetCode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Trapping Rain Water
 * link: https://leetcode.com/problems/trapping-rain-water/description/
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = {4,2,0,3,2,5};
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int start = getStart(height);
        int end = getEnd(height);
        int result = 0;
        while (height.length > 1 && start != -1 && end != -1) {
            height = Arrays.copyOfRange(height, start, end+1);
            for(int i=0; i<height.length; i++){
                if(height[i] == 0){
                    height[i]++;
                    result++;

                }
                height[i]--;
            }
            start = getStart(height);
            end = getEnd(height);
        }

        return result;
    }

    public static int getStart(int[] arr){
        int start = -1;
        for(int i=0; i<arr.length; i++){
            if(arr[i] != 0){
                return i;
            }
        }
        return start;
    }

    public static int getEnd(int[] arr){
        int end = -1;
        for(int i=arr.length-1; i>=0; i--){
            if(arr[i] != 0){
                return i;
            }
        }
        return end;
    }
}
