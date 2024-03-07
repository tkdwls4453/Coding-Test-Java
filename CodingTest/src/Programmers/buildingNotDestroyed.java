package Programmers;

/**
 * 파괴되지 않은 건물
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/92344
 */
public class buildingNotDestroyed {
    public static void main(String[] args){
        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        System.out.println(solution(board, skill));
    }

    public static int solution(int[][] board, int[][] skill) {
        int result = 0;
        int[][] actBoard = new int[board.length+1][board[0].length+1];

        for (int[] skillInfo : skill) {
            int type = skillInfo[0];
            int r1 = skillInfo[1], c1 = skillInfo[2];
            int r2 = skillInfo[3], c2 = skillInfo[4];
            int degree = skillInfo[5];

            if(type==1) degree *= -1;

            actBoard[r1][c1] += degree;
            actBoard[r2 + 1][c2 + 1] += degree;
            actBoard[r2 + 1][c1] += -degree;
            actBoard[r1][c2 + 1] += -degree;
        }

        // 행 방향으로 누적합
        for (int r = 1; r < actBoard.length - 1; r++) {
            for (int c = 0; c < actBoard[0].length - 1; c++) {
                actBoard[r][c] += actBoard[r - 1][c];
            }
        }

        // 열 방향으로 누적합
        for (int r = 0; r < actBoard.length - 1; r++) {
            for (int c = 1; c < actBoard[0].length - 1; c++) {
                actBoard[r][c] += actBoard[r][c - 1];
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] + actBoard[r][c] > 0) {
                    result++;
                }
            }
        }
        return result;
    }
}
