package Programmers;

import java.util.*;

/**
 * 거리두기 확인하기
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/81302
 */
public class CheckYourDistance {
    static int[][] map;
    static List<int[]> people;
    final static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                             {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                             {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                             {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                             {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        System.out.println(Arrays.toString(solution(places)));

    }

    public static int[] solution(String[][] places){
        List<Integer> answer = new ArrayList<>();

        for (String[] place : places) {
            initMap(place);
            boolean flag = true;
            for(int[] person : people){
                if (!bfs(person[0], person[1])) {
                    flag = false;
                    break;
                }
            }

            if(flag){
                answer.add(1);
            }else{
                answer.add(0);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static boolean bfs(int r, int c){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        queue.offer(new int[]{r, c, 0});
        visited[r][c] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for (int[] move : moves) {
                int nextR = cur[0] + move[0];
                int nextC = cur[1] + move[1];
                int nextDepth = cur[2] + 1;

                if (nextR >= 0 && nextR < map.length && nextC >= 0 && nextC < map[0].length) {
                    if (!visited[nextR][nextC] && map[nextR][nextC] != 2 && nextDepth <= 2) {
                        if (map[nextR][nextC] == 1) {
                            return false;
                        }
                        queue.offer(new int[]{nextR, nextC, nextDepth});
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }
        return true;
    }
    private static void initMap(String[] place) {
        map = new int[5][5];
        people = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                char c = place[i].charAt(j);
                if(c == 'P'){
                    map[i][j] = 1;
                    people.add(new int[]{i, j});
                }else if(c == 'X'){
                    map[i][j] = 2;
                }
            }
        }
    }
}
