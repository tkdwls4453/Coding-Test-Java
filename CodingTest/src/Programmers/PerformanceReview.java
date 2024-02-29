package Programmers;

import java.util.Arrays;

/**
 * 인사고과
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/152995
 */
public class PerformanceReview {
    public static void main(String[] args) {
        int[][] arrays = {{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}};
        System.out.println(solution(arrays));
    }

    public static int solution(int[][] scores){
        int[] myScore = scores[0];
        Arrays.sort(scores, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        int rank = 1;
        for (int[] arr : scores) {
            System.out.println(Arrays.toString(arr));
        }

        int[] beforeScore = scores[0];

        if (beforeScore[0] + beforeScore[1] > myScore[0] + myScore[1]) {
            rank++;
        }

        for (int i = 1; i < scores.length; i++) {

            int[] curScore = scores[i];

            if (beforeScore[0] > curScore[0] && beforeScore[1] > curScore[1]) {
                if (myScore == curScore) {
                    return -1;
                }
                continue;
            }

            beforeScore = curScore;

            if (curScore[0] + curScore[1] > myScore[0] + myScore[1]) {
                rank++;
            }
        }

        return rank;
    }
}
