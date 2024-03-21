package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 산 모양 타일링
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/258705
 */
public class MountainShapeTiling {
    static int result = 0;
    public static void main(String[] args) {
        int n = 10;
        int[] tops = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(solution(n, tops));

    }

    public static int solution(int n, int[] tops){

        int e = 0;
        int cnt=0;

        // edge 구성
        List<int[]> edges = new ArrayList<>();
        for (int i = n + 1; i < 2 * n + 1; i++) {
            edges.add(new int[]{i, e});
            e++;
            edges.add(new int[]{i, e});
            if (tops[cnt] == 1) {
                edges.add(new int[]{2 * n + 1 + cnt, i});
            }
            cnt++;
        }

        boolean[] visited = new boolean[3 * n + 1];

        int maxDepth = visited.length / 2;

        for (int i = 0; i <= 10; i++) {
            dfs(0, visited, edges, 0, i);
        }

        return result % 10007;
    }

    public static void dfs(int start, boolean[] visited, List<int[]> edges, int depth, int maxDepth){
        if (depth == maxDepth) {
            result++;
            return;
        }

        for (int i = start; i < edges.size(); i++) {
            int[] edge = edges.get(i);
            if (!visited[edge[0]] && !visited[edge[1]]) {
                visited[edge[0]] = true;
                visited[edge[1]] = true;
                dfs(i + 1, visited, edges, depth + 1, maxDepth);
                visited[edge[0]] = false;
                visited[edge[1]] = false;
            }
        }
    }
}
