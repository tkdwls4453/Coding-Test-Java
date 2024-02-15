package Programmers;

import java.util.*;

/**
 * 미로 탈출 명령어
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/150365
 */
public class MazeEscapeCommand {
    static int[][] map;
    static List<String> resultList;
    static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Map<Integer[], Boolean> visitedDepth = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(solution(2, 2, 1, 1, 2, 2, 2));
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        map = new int[n][m];
        resultList = new ArrayList<>();

        dfs(x - 1, y - 1, r - 1, c - 1, k, 0, new StringBuffer());

        if (resultList.size() == 0) {
            return "impossible";
        }

        int shortDistance = Math.abs(x - r) + Math.abs(y - c);

        if (shortDistance % 2 == 0) {
            if (k % 2 != 0) {
                return "impossible";
            }
        } else {
            if (k % 2 == 0) {
                return "impossible";
            }
        }

        Collections.sort(resultList);
        return resultList.get(0);
    }

    public static void dfs(int x, int y, int r, int c, int k, int depth, StringBuffer sb) {
        if (x == r && y == c && depth == k) {
            resultList.add(sb.toString());
            return;
        }
        if (visitedDepth.containsKey(new Integer[]{x, y})) {
            return;
        }else{
            visitedDepth.put(new Integer[]{x, y}, true);
        }

        int shortDistance = Math.abs(x - r) + Math.abs(y - c);
        int hasStep = k - depth;

        if (shortDistance > hasStep) {
            return;
        }



        for (int[] move : moves) {
            int nextX = x + move[0];
            int nextY = y + move[1];

            if (nextX >= 0 && nextX < map.length && nextY >= 0 && nextY < map[0].length) {
                if (move[0] == 1 && move[1] == 0) {
                    sb.append("d");
                } else if (move[0] == -1 && move[1] == 0) {
                    sb.append("u");
                } else if (move[0] == 0 && move[1] == 1) {
                    sb.append("r");
                } else if (move[0] == 0 && move[1] == -1) {
                    sb.append("l");
                }
                dfs(nextX, nextY, r, c, k, depth + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

}
