package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hanoi {
    public static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) {
        for (int[] arr : solution(2)){
            System.out.println(Arrays.toString(arr));
        }
    }

    public static int[][] solution(int n){
        hanoi(1, 2, 3, n);
        int[][] answer = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    public static void hanoi(int start, int mid, int target, int n){
        if(n==1){
            list.add(new int[]{start, target});
            return;
        }

        hanoi(start, target, mid, n - 1);
        hanoi(start, mid, target, 1);
        hanoi(mid, start, target, n - 1);
    }
}

