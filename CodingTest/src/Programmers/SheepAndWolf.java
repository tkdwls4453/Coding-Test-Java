package Programmers;

import java.util.*;

/**
 * 양과 늑대
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/92343
 */
public class SheepAndWolf {
    static int maxSheep = 1;
    public static void main(String[] args){
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        System.out.println(solution(info, edges));
    }
    public static int solution(int[] info, int[][] edges){
        int answer = 0;
        boolean[] visited = new boolean[info.length];

        visited[0] = true;
        dfs(info, edges,  visited, 1, 0);
        return maxSheep;
    }

    public static void dfs(int[] info, int[][] edges, boolean[] visited, int sheep, int wolf) {

        if (sheep == wolf) {
            return;
        }

        maxSheep = Math.max(sheep, maxSheep);

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            if (visited[parent] && !visited[child]) {
                visited[child] = true;
                if(info[child] == 0){
                    dfs(info, edges, visited, sheep + 1, wolf);
                }else{
                    dfs(info, edges, visited, sheep , wolf+1);
                }
                visited[child] = false;
            }
        }
    }
}
