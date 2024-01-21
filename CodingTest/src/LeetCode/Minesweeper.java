package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Minesweeper
 * link : https://leetcode.com/problems/minesweeper/description/
 */
public class Minesweeper {
    public static void main(String[] args){
        char[][] board = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
        int[] click = {3, 0};

        char[][] result = updateBoard(board, click);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char[][] updateBoard(char[][] board, int[] click) {

        Queue<int[]> queue = new LinkedList<>();

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        if (board[click[0]][click[1]] != 'E') {
            return board;
        }

        queue.offer(click);

        int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            check(board, cur);
            for (int[] move : moves) {
                int curX = cur[0] + move[0];
                int curY = cur[1] + move[1];

                if (curX >= 0 && curX < board.length && curY >= 0 && curY < board[0].length) {
                    if (board[curX][curY] == 'E' && board[cur[0]][cur[1]] == 'B') {
                        queue.offer(new int[]{curX, curY});
                    }
                }
            }
        }

        return board;
    }

    public static void check(char[][] board, int[] click) {

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int cnt = 0;

        for (int[] dir : directions) {
            int curX = click[0] + dir[0];
            int curY = click[1] + dir[1];

            if (curX >= 0 && curX < board.length && curY >= 0 && curY < board[0].length) {
                if (board[curX][curY] == 'M') {
                    cnt++;
                }
            }
        }

        if (cnt == 0) {
            board[click[0]][click[1]] = 'B';
        }else{
            board[click[0]][click[1]] = Integer.toString(cnt).charAt(0);
        }
    }
}
