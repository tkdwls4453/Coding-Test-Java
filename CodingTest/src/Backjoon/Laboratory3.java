package Backjoon;

import java.io.*;
import java.util.*;

/**
 * 연구소 3
 * link : https://www.acmicpc.net/problem/17142
 */
public class Laboratory3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        final int N = Integer.parseInt(line[0]);
        final int M = Integer.parseInt(line[1]);

        char[][] map = new char[N][N];
        List<Point> poison = new ArrayList<>();

        // 감염시켜야 하는 칸의 총 갯수를 구한다.
        int target = 0;
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = st.nextToken().charAt(0);
                switch (map[r][c]) {
                    case '0': target++; break;
                    case '2': poison.add(new Point(r, c));
                }
            }
        }

        // 처음 활성화시킬 바이러스의 조합을 모두 생성한다.
        List<Set<Integer>> comb = combination(poison.size(), M);
//        System.out.println("comb = " + comb);

        int result = Integer.MAX_VALUE;
        int[] dr = { -1,  0,  1,  0 };
        int[] dc = {  0, -1,  0,  1 };
        // 각 활성화 조합에 대해 탐색을 시작한다.
        for (Set<Integer> active : comb) {
            // 원본을 유지하기 위해 맵을 카피
            char[][] copy = copyMap(map);
            int infacted = 0;
            int time = 0;
            // 모든 활성화 바이러스의 위치를 큐에 넣는다.
            Queue<Entry> queue = new ArrayDeque<>();
            for (Integer i : active) {
                Point p = poison.get(i);
                queue.add(new Entry(p, 0));
                copy[p.r][p.c] = '1';
            }

            while (!queue.isEmpty()) {
                Entry cur = queue.remove();
//                if (time < cur.t) {
//                    System.out.println("time: " + cur.t);
//                    printMap(copy);
//                }

                for (int i = 0; i < 4; i++) {
                    Point next = new Point(cur.p.r + dr[i], cur.p.c + dc[i]);
                    if (next.inRange(N, N) && copy[next.r][next.c] != '1') {
                        if (copy[next.r][next.c] == '0') {
                            infacted++;
                            time = cur.t + 1;
                        }
                        // 이미 방문한 곳은 '1'으로 표시한다.
                        copy[next.r][next.c] = '1';
                        queue.add(new Entry(next, cur.t + 1));
                    }
                }

                // 모두 감염시켰다면 빠져나온다.
                if (infacted == target) break;
            }

//            System.out.println(active + ": " + time);
            if (infacted == target)
                result = Math.min(result, time);
        }

        System.out.println((result == Integer.MAX_VALUE) ? -1 : result);
    }

    // 디버깅용 메소드
    static void printMap(char[][] map) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (char[] row : map) {
            for (char c : row) {
                writer.write(c);
            }
            writer.newLine();
        }
        writer.flush();
    }

    static char[][] copyMap(char[][] map) {
        char[][] result = new char[map.length][map[0].length];
        for (int r = 0; r < map.length; r++) {
            System.arraycopy(map[r], 0, result[r], 0, map[r].length);
        }
        return result;
    }

    static List<Set<Integer>> combination(int n, int k) {
        List<Set<Integer>> result = new ArrayList<>();
        Set<Integer> temp = new HashSet<>();
        for (int i = 0; i < n; i++) {
            temp.add(i);
            recursion(n, k, result, temp, i);
            temp.remove(i);
        }
        return result;
    }

    static void recursion(int n, int k, List<Set<Integer>> result, Set<Integer> temp, int index) {
        if (temp.size() == k) {
            result.add(new HashSet<>(temp));
            return;
        }

        for (int i = index + 1; i < n; i++) {
            temp.add(i);
            recursion(n, k, result, temp, i);
            temp.remove(i);
        }
    }

    static class Entry {
        Point p;
        int t;

        public Entry(Point p, int t) {
            this.p = p;
            this.t = t;
        }
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean inRange(int n, int m) {
            return r >= 0 && r < n && c >= 0 && c < m;
        }

        @Override
        public String toString() {
            return "(" + r + "," + c + ")";
        }
    }
}