package Programmers;

import java.util.*;

public class Delivery {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int n = 6;
        int[][] road = {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
        int k = 4;
        System.out.println(solution(n, road, k));
    }

    public static int solution(int N, int[][] road, int K){
        int answer = 0;
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for(int[] info : road){
            graph.putIfAbsent(info[0], new ArrayList<int[]>());
            graph.get(info[0]).add(new int[]{info[1], info[2]});

            graph.putIfAbsent(info[1], new ArrayList<int[]>());
            graph.get(info[1]).add(new int[]{info[0], info[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);

        pq.add(new int[]{1, 0});
        distance[1] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(distance[cur[0]] < cur[1]) continue;

            for (int[] next : graph.get(cur[0])) {
                int nextNode = next[0];
                int nextDist = next[1] + cur[1];

                if (nextDist < distance[nextNode]) {
                    pq.add(new int[]{nextNode, nextDist});
                    distance[nextNode] = nextDist;
                }
            }
        }

        for (int n : distance) {
            if (n <= K) {
                answer++;
            }
        }
        return answer;
    }
}
