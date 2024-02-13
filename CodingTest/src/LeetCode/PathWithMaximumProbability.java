package LeetCode;

import java.util.*;

/**
 * 1514. Path with Maximum Probability
 * link : https://leetcode.com/problems/path-with-maximum-probability/description/
 */
public class PathWithMaximumProbability {
    public static void main(String[] args) {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb = {0.5, 0.5, 0.2};
        int start = 0;
        int end = 2;

        System.out.println(maxProbability(n, edges, succProb, start, end));
    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node){
        Map<Integer, List<Entry>> graph = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new Entry(edge[1], -1, succProb[i]));

            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(new Entry(edge[0],-1, succProb[i]));
        }

        double[] costs = new double[n];
        Arrays.fill(costs, -1);

        Queue<Entry> queue = new LinkedList<>();
        queue.offer(new Entry(start_node, -1,1));

        while (!queue.isEmpty()) {
            Entry cur = queue.poll();

            if(graph.containsKey(cur.node)){
                for (Entry entry : graph.get(cur.node)) {
                    int nextNode = entry.node;
                    double nextCost = entry.cost * cur.cost;

                    if(nextCost > costs[nextNode]){
                        if(nextNode != cur.beforeNode){
                            queue.add(new Entry(nextNode, cur.node, nextCost));
                            costs[nextNode] = nextCost;
                        }
                    }
                }
            }
        }

        if (costs[end_node] == -1) {
            return 0.0;
        }
        return costs[end_node];
    }

    static class Entry{
        int node;
        int beforeNode;
        double cost;

        public Entry(int node, int beforeNode, double cost) {
            this.node = node;
            this.beforeNode = beforeNode;
            this.cost = cost;
        }
    }
}
