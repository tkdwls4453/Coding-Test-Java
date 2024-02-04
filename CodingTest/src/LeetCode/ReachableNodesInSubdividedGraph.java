package LeetCode;

import java.util.*;

/**
 * Reachable Nodes In Subdivided Graph
 * link : https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/description/
 */
public class ReachableNodesInSubdividedGraph {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 10}, {0, 2, 1}, {1, 2, 2}};
        int maxMoves = 6;
        int n = 3;

        System.out.println(reachableNodes(edges, maxMoves, n));

    }

    public static int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});

            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        int[] distance = new int[n];
        Arrays.fill(distance, INF);

        Queue<int[]> pq = new PriorityQueue<>((e1, e2) -> {
            return e1[1] - e2[1];
        });

        pq.add(new int[]{0, 0});
        distance[0] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.remove();

            if (distance[cur[0]] < cur[1]) continue;
            if (graph.containsKey(cur[0])) {
                for (int[] next : graph.get(cur[0])) {
                    int nextNode = next[0];
                    int nextDist = cur[1] + next[1] + 1;

                    if(nextDist <= distance[nextNode]){
                        distance[nextNode] = nextDist;
                        pq.add(new int[]{nextNode, nextDist});
                    }
                }
            }

        }

        int reachableNodeCnt = 0, reachableSubNodeCnt = 0;

        for (int dist : distance) {
            if (dist <= maxMoves) {
                reachableNodeCnt++;
            }
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int subNodeCnt = edge[2];

            int uCnt= 0, vCnt = 0;

            if (distance[u] <= maxMoves) {
                uCnt = Math.min(maxMoves - distance[u], subNodeCnt);
            }

            if (distance[v] <= maxMoves) {
                vCnt = Math.min(maxMoves - distance[v], subNodeCnt);
            }

            reachableNodeCnt += Math.min(uCnt + vCnt, subNodeCnt);
        }
        return reachableNodeCnt + reachableSubNodeCnt;
    }
}
