package Basic;

import java.util.*;

/**
 * 위상정렬 템플릿 코드
 */
public class TopologicalSort {
    public static void main(String[] args) {

    }

    public int[] topologicalSort(int nodeNum, int[][] edges){
        // 주어진 입력을 사용하기 편한 방향 그래프로 변경하기
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[nodeNum];

        for (int[] edge : edges) {
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(edge[0]);
            inDegree[edge[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[nodeNum];
        int[] order = new int[nodeNum];
        int count = 0;

        for (int i = 0; i < nodeNum; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                visited[i] = true;
                order[count++] = i;
            }
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();

            if(graph.containsKey(cur)){
                for(int next : graph.get(cur)){
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                        visited[next] = true;
                        order[count++] = next;
                    }
                }
            }
        }
        return order;
    }
}
