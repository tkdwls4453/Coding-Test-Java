package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Shortest Path in Binary Matrix
 * link : https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
 */
public class ShortestPathInBinaryMatrix {
    public static void main(String[] args){
        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(shortestPathBinaryMatrix(grid));
    }

    public static int shortestPathBinaryMatrix(int[][] grid){
        int rowLength = grid.length;
        int colLength = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowLength][colLength];
        int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

        if(grid[0][0] == 0){
            queue.offer(new int[]{0, 0, 1});
            visited[0][0] = true;
        }else{
            return -1;
        }


        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == rowLength - 1 && cur[1] == colLength - 1) {
                return cur[2];
            }
            for (int[] move : moves) {
                int nextRow = cur[0] + move[0];
                int nextCol = cur[1] + move[1];
                int nextDepth = cur[2] + 1;

                if (nextRow >= 0 && nextRow < rowLength && nextCol >= 0 && nextCol < colLength && grid[nextRow][nextCol] == 0) {
                    if (!visited[nextRow][nextCol]) {
                        queue.offer(new int[]{nextRow, nextCol, nextDepth});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        return -1;
    }
}
