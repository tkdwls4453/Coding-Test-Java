package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Number of Islands
 * link : https://leetcode.com/problems/number-of-islands/description/
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        boolean[][] visited = new boolean[rowLength][colLength];
        int answer = 0;

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (visited[i][j] == false && grid[i][j] == '1') {
                    dfs(i, j, grid, visited);
                    //bfs(i, j, grid, visited);
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void bfs(int r, int c, char[][] grid, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        int rowLength = grid.length;
        int colLength = grid[0].length;
        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        int[][] moves = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] move : moves) {
                int nextRow = cur[0] + move[0];
                int nextCol = cur[1] + move[1];

                if (nextRow >= 0 && nextRow < rowLength && nextCol >= 0 && nextCol < colLength && grid[nextRow][nextCol] == '1') {
                    if (!visited[nextRow][nextCol]) {
                        queue.offer(new int[]{nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
    }

    public static void dfs(int r, int c, char[][] grid, boolean[][] visited) {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        int[][] moves = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        visited[r][c] = true;

        for (int[] move : moves) {
            int nextRow = r + move[0];
            int nextCol = c + move[1];

            if (nextRow >= 0 && nextRow < rowLength && nextCol >= 0 && nextCol < colLength && grid[nextRow][nextCol] == '1') {
                if (!visited[nextRow][nextCol]) {
                    dfs(nextRow, nextCol, grid, visited);
                }
            }
        }

    }
}
