package LeetCode;

import java.util.*;

/**
 * Is Graph Bipartite?
 * link : https://leetcode.com/problems/is-graph-bipartite/description/
 */
public class IsGraphBipartite {
    public static void main(String[] args){
//        int[][] graph = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        int[][] graph = {{4,1},{0,2},{1,3},{2,4},{3,0}};
        System.out.println(isBipartite(graph));
    }

    public static boolean isBipartite(int[][] graph){
        int[] visited = new int[graph.length];

        for(int i=0; i<graph.length; i++){
            if (visited[i] != 0) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();

            queue.offer(i);
            visited[i] = 1;

            while(!queue.isEmpty()){
                int now = queue.poll();

                for(int next : graph[now]){
                    if(visited[next] == 0){
                        queue.offer(next);
                        visited[next] = visited[now] * -1;
                    }

                    for(int near : graph[next]){
                        if(visited[near] == visited[next]){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
