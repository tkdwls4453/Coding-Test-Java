package Programmers;

import java.util.Arrays;
import java.util.List;

/**
 * 광물 캐기
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/172927
 */
public class MiningForMinerals {
    static int total;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(solution(picks, minerals));
    }

    public static int solution(int[] picks, String[] minerals){
        total = Arrays.stream(picks).sum();
        int[] visited = new int[picks.length];

        for (int i = 0; i < picks.length; i++) {
            if (visited[i] < picks[i]) {
                visited[i]++;
                dfs(i,1, 0, 0, picks, visited, minerals);
                visited[i]--;
            }
        }

        return result;
    }

    public static void dfs(int tool, int cnt, int mineralsIdx, int sum, int[] picks, int[] visited, String[] minerals){
        int r = 5;
        while (r > 0) {
            if (mineralsIdx >= minerals.length) {
                break;
            }

            if (tool == 0) { // 다이아몬드
                sum += 1;
            }else if(tool == 1){ // 철
                if(minerals[mineralsIdx].equals("diamond")) sum += 5;
                else sum += 1;
            }else{ // 돌
                if(minerals[mineralsIdx].equals("diamond")) sum += 25;
                else if(minerals[mineralsIdx].equals("iron")) sum += 5;
                else sum += 1;
            }

            mineralsIdx++;
            r--;
        }

        if (cnt == total || mineralsIdx == minerals.length) {
            result = Math.min(result, sum);
            return;
        }

        for (int i = 0; i < picks.length; i++) {
            if (visited[i] < picks[i]) {
                visited[i]++;
                dfs(i, cnt+1, mineralsIdx, sum, picks, visited, minerals);
                visited[i]--;
            }
        }
    }
}

