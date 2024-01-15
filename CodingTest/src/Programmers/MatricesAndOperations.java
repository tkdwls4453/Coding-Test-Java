package Programmers;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 행렬과 연산
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/118670
 */

public class MatricesAndOperations {
    public static void main(String[] args){
        int[][] rc = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] operations = {"Rotate", "ShiftRow"};
        int[][] newRc = solution(rc, operations);
        for(int[] row : newRc){
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] solution(int[][] rc, String[] operations){
        int[][] answer = rc;

        int row = rc.length;
        int col = rc[0].length;

        for (String operation : operations) {
            if(operation.equals("ShiftRow")){
                LinkedList<int[]> list = new LinkedList<>();
                for(int[] r : answer){
                  list.addLast(r);
                }

                int[] remove = list.removeLast();
                list.addFirst(remove);

                for(int i=0; i<answer.length; i++){
                    answer[i] = list.get(i);
                }
            } else if (operation.equals("Rotate")) {
                LinkedList<Integer> list = new LinkedList<>();

                // 위쪽
                for(int i=0; i<col; i++){
                    list.addLast(answer[0][i]);
                }

                // 오른쪽
                for(int i=1; i<row; i++){
                    list.addLast(answer[i][col - 1]);
                }

                // 아래쪽
                for(int i=col-2; i>=0 ; i--){
                    list.addLast(answer[row - 1][i]);
                }

                // 왼쪽
                for(int i=row-2; i>=1; i--){
                    list.addLast(answer[i][0]);
                }

                int remove = list.removeLast();
                list.addFirst(remove);

                // 위쪽
                for(int i=0; i<col; i++){
                    answer[0][i] = list.removeFirst();
                }

                // 오른쪽
                for(int i=1; i<row; i++){
                   answer[i][col - 1] = list.removeFirst();
                }

                // 아래쪽
                for(int i=col-2; i>=0 ; i--){
                    answer[row - 1][i] = list.removeFirst();
                }

                // 왼쪽
                for(int i=row-2; i>=1; i--){
                    answer[i][0] = list.removeFirst();
                }
            }
        }
        return answer;
    }
}