package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 연구소 3
 * link : https://www.acmicpc.net/problem/17142
 */
public class Laboratory3 {
    static List<int[]> virusList = new ArrayList<>();
    static int minTime = Integer.MAX_VALUE;
    static int wallCnt = 0;
    static int virusCnt = 0;
    final static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(arr[j]);
                if (map[i][j] == 2) {
                    virusList.add(new int[]{i, j});
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 1) {
                    wallCnt++;
                } else if (map[i][j] == 2) {
                    virusCnt++;
                }
            }
        }

        backtracking(0, m, virusList, new ArrayList<>(), map);

        if (minTime == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else{
            System.out.println(minTime);
        }

    }

    public static void backtracking(int start, int k, List<int[]> virusList, List<Integer> cur, int[][] map) {
        if (cur.size() == k) {
            int curTime = 0;
            Queue<Vertex> queue = new LinkedList<>();
            Map<Vertex, Integer> visited = new HashMap<>();

            for (int activeIdx : cur) {
                int[] activeVirus = virusList.get(activeIdx);
                queue.offer(new Vertex(activeVirus[0], activeVirus[1]));
                visited.put(new Vertex(activeVirus[0], activeVirus[1]), 0);
            }

            while (!queue.isEmpty()) {
                Vertex now = queue.poll();

                for (int[] move : moves) {
                    int nextRow = now.row + move[0];
                    int nextCol = now.col + move[1];
                    int time = visited.get(now);
                    if (nextRow >= 0 && nextRow < map.length && nextCol >= 0 && nextCol < map.length) {
                        if (!visited.containsKey(new Vertex(nextRow, nextCol)) && map[nextRow][nextCol] != 1) {
                            queue.offer(new Vertex(nextRow, nextCol));
                            visited.put(new Vertex(nextRow, nextCol), time + 1);
                            curTime = Math.max(curTime, time + 1);
                        }
                    }
                }
            }

            System.out.println(map.length * map.length - wallCnt - visited.size());
            if (map.length * map.length - wallCnt - visited.size()  == 0) {
                minTime = Math.min(curTime, minTime);
            }
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            if (!cur.contains(i)) {
                cur.add(i);
                backtracking(i + 1, k, virusList, cur, map);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public static class Vertex {
        int row;
        int col;

        public Vertex(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return row == vertex.row && col == vertex.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}