package Basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 그래프에서 BFS 기반 코드
 */
public class GraphBfs {
    static boolean[][] visited;
    static int[][] grid;
    static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};
    static int rowLength;
    static int colLength;

    public static boolean isValid(int r, int c) {
        return (r >= 0 && r < rowLength && c >= 0 && c < colLength && grid[r][c] == 1);
    }

    public static void main(String[] args){
        grid = new int[][]{{1, 1, 1, 1}, {0, 1, 0, 1}, {0, 1, 0, 1}, {1, 0, 1, 1}};
        rowLength = grid.length;
        colLength = grid[0].length;
        visited = new boolean[rowLength][colLength];
        bfs(0, 0);
    }

    public static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];

            for (int i = 0; i < 8; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];

                if (isValid(nextRow, nextCol)) {
                    if (!visited[nextRow][nextCol]) {
                        queue.offer(new int[]{nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
    }
}
