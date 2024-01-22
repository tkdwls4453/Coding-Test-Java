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
        if (cur.size() == k) {
            int curEmptyArea = 0;
            int count = 0;
            Map<Point, Boolean> wallMap = new HashMap<>();
            for (int idx : cur) {
                int[] wall = emptyList.get(idx);
                wallMap.put(new Point(wall[0], wall[1]), true);
            }

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 1) {
                        wallMap.put(new Point(i, j), true);
                    }
                }
            }
            // bfs 코드
            Queue<Point> queue = new LinkedList<>();
            Map<Point, Boolean> visited = new HashMap<>();

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 2) {
                        queue.offer(new Point(i, j));
                        visited.put(new Point(i, j), true);
                        count++;
                    }
                }
            }

            while(!queue.isEmpty()){
                Point now = queue.poll();
                for (int[] move : moves) {
                    int nextR = now.row + move[0];
                    int nextC = now.col + move[1];
                    if (nextR >= 0 && nextR < map.length && nextC >= 0 && nextC < map[0].length) {

                        if (!visited.containsKey(new Point(nextR, nextC)) && !wallMap.containsKey(new Point(nextR, nextC))) {
                            queue.offer(new Point(nextR, nextC));
                            visited.put(new Point(nextR, nextC), true);
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
            if (!cur.contains(i)) {
                cur.add(i);
                bruteForce(start + 1, k, map, emptyList, cur);
                cur.remove(cur.size() - 1);
            }

        }
    }

    public static class Point{
        int row;
        int col;

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row && col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }
}
