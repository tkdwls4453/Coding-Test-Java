package LeetCode;

import java.util.Arrays;
import java.util.List;

/**
 * Word Search
 * link : https://leetcode.com/problems/word-search/
 */
public class WordSearch {
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCB";

        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] != word.charAt(0)) {
                    continue;
                }
                char buffer = board[r][c];
                board[r][c] = '.';
                if (backtrack(board, r, c, word, 0)) {
                    return true;
                }
                board[r][c] = buffer;
            }
        }
        return false;
    }

    public static boolean backtrack(char[][] board, int r, int c, String word, int count) {
        int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        if (count + 1 == word.length()) {
            return true;
        }
        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
                if (board[nr][nc] == word.charAt(count + 1)) {
                    char buffer = board[nr][nc];
                    board[nr][nc] = '.';
                    if (backtrack(board, nr, nc, word, count + 1)) {
                        return true;
                    }
                    board[nr][nc] = buffer;
                }
            }
        }
        return false;
    }

}
