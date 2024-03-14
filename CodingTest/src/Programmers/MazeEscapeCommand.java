package Programmers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 미로 탈출 명령어
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/150365
 */
public class MazeEscapeCommand {
    // 사전순 (d l r u)
    final static int[][] moves = new int[][]{{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    static String result = "impossible";
    public static void main(String[] args) {
        System.out.println(solution(3, 4, 2, 3, 3, 1, 5));
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        List<String> cur = new ArrayList<>();
        dfs(n, m, x, y, r, c, k, cur);

        return result;
    }

    public static void dfs(int n, int m, int curR, int curC, int r, int c, int k, List<String> cur) {
        if (curR == r && curC == c && k==0) {
            result = cur.stream().collect(Collectors.joining());
            return;
        }

        int distance = calculateDistance(curR, curC, r, c);

        if (distance > k || Math.abs(k - distance) % 2 != 0) {
            return;
        }

        for (int[] move : moves) {
            int moveR = move[0], moveC = move[1];
            int nextR = curR + moveR, nextC = curC + moveC;

            if (nextR >= 1 && nextR <= n && nextC >= 1 && nextC <= m) {
                if (moveR == 1 && moveC == 0)       cur.add("d");
                else if (moveR == 0 && moveC == -1) cur.add("l");
                else if (moveR == 0 && moveC == 1)  cur.add("r");
                else if (moveR == -1 && moveC == 0) cur.add("u");

                dfs(n, m, nextR, nextC, r, c, k - 1, cur);

                cur.remove(cur.size() - 1);

                if (!result.equals("impossible")) {
                    return;
                }
            }
        }
    }

    public static int calculateDistance(int curR, int curC, int r, int c) {
        return Math.abs(r - curR) + Math.abs(c - curC);
    }

}
