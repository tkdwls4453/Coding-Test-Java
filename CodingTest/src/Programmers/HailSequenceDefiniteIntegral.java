package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 우박수열 정적분
 * https://school.programmers.co.kr/learn/courses/30/lessons/134239
 */
public class HailSequenceDefiniteIntegral {
    public static void main(String[] args) {
        int k = 5;
        int[][] ranges = {{0, 0}, {0, -1}, {2, -3}, {3, -3}};

        System.out.println(Arrays.toString(solution(k, ranges)));
    }

    public static double[] solution(int k, int[][] ranges) {
        List<Integer> ubackNumList = createUbackNumList(k);
        System.out.println(ubackNumList);
        List<Double> answerList = new ArrayList<>();
        int n = ubackNumList.size()-1;
        for (int[] range : ranges) {
            double total = 0;
            if(range[0] > range[1] + n){
                answerList.add(-1.0);
                continue;
            }

            for (int i = range[0]; i < range[1] + n; i++) {
                total += (ubackNumList.get(i) + ubackNumList.get(i + 1)) / 2.0;
            }
            answerList.add(total);
        }
        return answerList.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static List<Integer> createUbackNumList(int num){
        List<Integer> result = new ArrayList<>();

        while (num != 1) {
            result.add(num);
            if (num % 2 == 0) {
                num /= 2;
            }else{
                num = num * 3 + 1;
            }
        }

        result.add(1);

        return result;
    }


}
