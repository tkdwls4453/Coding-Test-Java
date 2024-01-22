package Backjoon;

import java.util.*;

/**
 * 연구소
 * https://www.acmicpc.net/problem/14502
 */
public class Laboratory {

    static final int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int maxEmptyArea = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] map = new int[n][m];
        List<int[]> emptyList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) {
                    emptyList.add(new int[]{i, j});
                }
            }
        }

        bruteForce(0,3, map, emptyList, new ArrayList<>());

        System.out.println(maxEmptyArea);

    }

    public static void bruteForce(int start, int k, int[][] map, List<int[]> emptyList, List<Integer> cur){
        int length = emptyList.size();
        int count = 0;
        if (cur.size() == k) {
            int curEmptyArea = 0;

            Map<int[], Boolean> wallMap = new HashMap<>();
            for (int idx : cur) {
                int[] wall = emptyList.get(idx);
                wallMap.put(wall, true);
            }

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 1) {
                        wallMap.put(new int[]{i, j}, true);
                    }
                }
            }

            // bfs 코드
            Queue<int[]> queue = new LinkedList<>();
            Map<int[], Boolean> visited = new HashMap<>();

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 2) {
                        queue.offer(new int[]{i, j});
                        visited.put(new int[]{i, j}, true);
                        count++;
                    }
                }
            }

            while(!queue.isEmpty()){
                int[] now = queue.poll();

                for (int[] move : moves) {
                    int nextR = now[0] + move[0];
                    int nextC = now[1] + move[1];

                    if (nextR >= 0 && nextR < map.length && nextC >= 0 && nextC < map[0].length) {
                        if (!visited.containsKey(new int[]{nextR, nextC}) && !wallMap.containsKey(new int[]{nextR, nextC})) {
                            queue.offer(new int[]{nextR, nextC});
                            visited.put(new int[]{nextR, nextC}, true);
                            count++;
                        }
                    }
                }
            }
            curEmptyArea = map.length * map[0].length - count - wallMap.size();
            maxEmptyArea = Math.max(curEmptyArea, maxEmptyArea);
            return;
        }

        for (int i = start; i < length; i++) {
            cur.add(i);
            bruteForce(start + 1, k, map, emptyList, cur);
            cur.remove(cur.size() - 1);
        }

    }
}
