package Backjoon;

import java.util.*;

/**
 * 연구소
 * https://www.acmicpc.net/problem/14502
 */
public class Laboratory {
    final static int WALL_CNT = 3;
    final static int[][] moves = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static List<List<Integer>> wallList = new ArrayList<>();
    static List<Vertex> virusList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] map = new int[n][m];
        int maxEmptySpace = 0;
        List<int[]> emptySpaces = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scanner.nextInt();

                if (map[i][j] == 0) {
                    emptySpaces.add(new int[]{i, j});
                }else if(map[i][j] == 2){
                    virusList.add(new Vertex(i, j));
                }
            }
        }

        backtracking(0, emptySpaces, new ArrayList<>());

        for (List<Integer> walls : wallList) {
            int[][] newMap = createNewMap(map, walls, emptySpaces);

            // BFS 코드
            Queue<Vertex> queue = new LinkedList<>();

            for (Vertex virus : virusList) {
                queue.offer(virus);
                newMap[virus.r][virus.c] = 2;
            }

            while (!queue.isEmpty()) {
                Vertex cur = queue.poll();

                for (int[] move : moves) {
                    int nextR = cur.r + move[0];
                    int nextC = cur.c + move[1];

                    if (nextR >= 0 && nextR < map.length && nextC >= 0 && nextC < map[0].length) {
                        Vertex nextVertex = new Vertex(nextR, nextC);
                        if (newMap[nextR][nextC] == 0) {
                            queue.offer(nextVertex);
                            newMap[nextR][nextC] = 2;
                        }
                    }
                }
            }

            int emptySpaceCnt = countEmptySpace(newMap);
            maxEmptySpace = Math.max(maxEmptySpace, emptySpaceCnt);
        }

        System.out.println(maxEmptySpace);
    }

    static class Vertex{
        private int r;
        private int c;

        public Vertex(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int countEmptySpace(int[][] map){
        int count = 0;

        for (int i=0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    static int[][] createNewMap(int[][] map, List<Integer> walls, List<int[]> emptySpaces) {
        int[][] newMap = new int[map.length][map[0].length];

        for (int i=0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        for (int wallIdx : walls) {
            int[] wall = emptySpaces.get(wallIdx);
            newMap[wall[0]][wall[1]] = 1;
        }

        return newMap;
    }
    static void backtracking(int start, List<int[]> emptySpaces, List<Integer> cur){
        if (cur.size() == WALL_CNT) {
            wallList.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i < emptySpaces.size(); i++) {
            if (!cur.contains(i)) {
                cur.add(i);
                backtracking(i + 1, emptySpaces, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
