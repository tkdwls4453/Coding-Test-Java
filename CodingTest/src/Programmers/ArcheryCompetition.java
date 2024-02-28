package Programmers;

import java.util.Arrays;
import java.util.List;

/**
 * 양궁대회
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/92342
 */
public class ArcheryCompetition {
    static int max = 0;
    static int[] result = new int[11];

    static int[] cur = new int[11];
    public static void main(String[] args) {
        int n = 5;
        int[] info = new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(solution(n, info)));
    }

    public static int[] solution(int n, int[] info) {
        backtracking(0, n, info);
        if (max <= 0) {
            return new int[]{-1};
        }
        return result;
    }

    public static void backtracking(int depth, int n, int[] info){
        if (depth == n) {
            int scoreDiff = calculateScoreDiff(info);

            if (max <= scoreDiff) {
                max = scoreDiff;
                result = cur.clone();
            }
            return;
        }

        for (int i = 0; i < cur.length; i++) {
            if(info[i] < cur[i]) break;
            cur[i]++;
            backtracking(depth + 1, n, info);
            cur[i]--;
        }
    }

    public static int calculateScoreDiff(int[] info) {
        int score= 0; // 어피치 스코어

        for (int i = 0; i < info.length; i++) {
            if (info[i] == 0 && cur[i] == 0) {}
            else if(info[i] < cur[i]){
                score += (10 - i);
            }else{
                score -= (10 - i);
            }
        }
        return score;
    }
}

