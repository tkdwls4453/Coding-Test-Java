package Programmers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 미로 탈출 명령어
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/150365
 */
public class MazeEscapeCommand {
    public static void main(String[] args) {
        System.out.println(solution(3, 3, 1, 2, 3, 3, 4));
    }
    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        ArrayList<String> answerList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        int[][] map = new int[n][m];
        map[r-1][c-1] = 1; // 도착지 표시

        dfs(x-1, y-1, r-1, c-1, 0, k, answerList, sb, map);

        Collections.sort(answerList);

        if (answerList.size() == 0) {
            return "impossible";
        }
        return answerList.get(0);
    }

    public static void dfs(int x, int y, int r, int c, int depth, int k, ArrayList<String> answerList, StringBuffer sb, int[][] map) {
        if (map[x][y] == 1 && depth == k) {
            answerList.add(sb.toString());
            return;
        }


        if (Math.abs(x - r) + Math.abs(y - c) > k - depth) {
            return;
        }

        int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] move : moves) {
            int nextX = x + move[0];
            int nextY = y + move[1];

            if (nextX >= 0 && nextX < map.length && nextY >= 0 && nextY < map[0].length) {
                if (move[0] == 1 && move[1] == 0) {
                    sb.append("d");
                }else if(move[0] == -1 && move[1] == 0) {
                    sb.append("u");
                }else if(move[0] == 0 && move[1] == 1) {
                    sb.append("r");
                }else if(move[0] == 0 && move[1] == -1) {
                    sb.append("l");
                }

                dfs(nextX, nextY, r, c, depth + 1, k, answerList, sb, map);

                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
