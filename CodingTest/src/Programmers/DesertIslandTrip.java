package Programmers;

import java.util.*;

/**
 * 무인도 여행
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/154540
 */
public class DesertIslandTrip {
    static final int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) {
        String[] maps = {"X591X", "X1X5X", "X231X", "1XXX1"};

        System.out.println(Arrays.toString(solution(maps)));
    }

    // bfs
    public static int[] solution(String[] maps) {
        int[][] map = new int[maps.length][maps[0].length()];
        boolean[][] visited = new boolean[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (maps[i].charAt(j) == 'X') {
                    map[i][j] = -1;
                }else{
                    map[i][j] = Integer.parseInt(maps[i].charAt(j) + "");
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (!visited[i][j] && map[i][j] != -1) {
                    bfs(i, j, map, visited);
                }
            }
        }

        if (resultList.size() == 0) {
            return new int[]{-1};
        }

        Collections.sort(resultList);

        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void bfs(int r, int c, int[][] map, boolean[][] visited){
        int sum = 0;
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            sum += map[cur[0]][cur[1]];

            for (int[] move : moves) {
                int nextR = cur[0] + move[0];
                int nextC = cur[1] + move[1];

                if (nextR >= 0 && nextR < map.length && nextC >= 0 && nextC < map[0].length) {
                    if (map[nextR][nextC] != -1 && !visited[nextR][nextC]) {
                        queue.offer(new int[]{nextR, nextC});
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }
        resultList.add(sum);
    }
}
