package Programmers;

import java.util.*;

/**
 * 양과 늑대
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/92343
 */
public class SheepAndWolf {
    public static void main(String[] args){
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        System.out.println(solution(info, edges));
    }
    public static int solution(int[] info, int[][] edges){
        int answer = 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();


        for(int[] edge : edges){
            if(graph.containsKey(edge[0])){
                graph.get(edge[0]).add(edge[1]);
            }else{
                graph.put(edge[0], new ArrayList<>(Arrays.asList(edge[1])));
            }

            if(graph.containsKey(edge[1])){
                graph.get(edge[1]).add(edge[0]);
            }else{
                graph.put(edge[1], new ArrayList<>(Arrays.asList(edge[0])));
            }
        }

        for(int i=1; i<info.length; i++){
            Map<Integer, Boolean> visited = new HashMap<>();
            answer = Math.max(dfs(0,  i,0, 0, 0, visited, graph, info), answer);
            System.out.println(answer);
        }
        return answer;
    }

    public static int dfs(int start, int end, int sheep, int wolf, int maxSheep, Map<Integer, Boolean> visited, Map<Integer, List<Integer>> graph, int[] info){
        visited.put(start, true);
        if (info[start] == 0) {
            sheep++;
        }else{
            wolf++;
        }

        if(sheep > wolf){
            maxSheep = Math.max(sheep, maxSheep);
        }

        if (start == end) {

        }
        for(int next : graph.get(start)){
            if(!visited.containsKey(next)){
                dfs(next, end, sheep, wolf, maxSheep, visited, graph, info);
            }


        }
        return maxSheep;
    }
}
