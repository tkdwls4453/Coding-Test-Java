package LeetCode;

import java.util.*;

/**
 * 743. Network Delay Time
 * link : https://leetcode.com/problems/network-delay-time/description/
 */
public class NetworkDelayTime {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args){
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int k = 2;

        System.out.println(networkDelayTime(times, n, k));
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] time : times) {
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[]{time[1], time[2]});

        }

        int[] distance = new int[n+1];
        Arrays.fill(distance, INF);

        Queue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        pq.add(new int[]{k, 0});
        distance[k] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.remove();

            if(distance[cur[0]] < cur[1]) continue;

            if(graph.containsKey(cur[0])){
                for(int[] next : graph.get(cur[0])){
                    int nextNode = next[0];
                    int nextDist = cur[1] + next[1];

                    if(nextDist < distance[nextNode]){
                        pq.add(new int[]{nextNode, nextDist});
                        distance[nextNode] = nextDist;
                    }
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if(distance[i] == INF){
                return -1;
            }
            max = Math.max(max, distance[i]);
        }

        return max;
    }

}
