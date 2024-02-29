package Programmers;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 공원산책
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/172928
 */
public class WalkInThePark {
    public static void main(String[] args) {
        String[] parks = {"OSO","OOO","OXO","OOO"};
        String[] routs = {"E 2","S 3","W 1"};

        System.out.println(Arrays.toString(solution(parks, routs)));

    }

    public static int[] solution(String[] park, String[] routes) {
        int[] cur = new int[2];

        String[][] map = new String[park.length][park[0].length()];

        HashMap<String, int[]> directionMap = new HashMap<>();
        directionMap.put("E", new int[]{0, 1});
        directionMap.put("W", new int[]{0, -1});
        directionMap.put("S", new int[]{1, 0});
        directionMap.put("N", new int[]{-1, 0});

        for(int i=0; i<map.length; i++){
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = park[i].charAt(j) + "";
                if (map[i][j].equals("S")) {
                    cur[0] = i;
                    cur[1] = j;
                }
            }
        }

        for (String route : routes) {
            String[] routeArr = route.split(" ");

            String dir = routeArr[0]; // 방향
            int dist = Integer.parseInt(routeArr[1]); // 거리

            int nextR = cur[0];
            int nextC = cur[1];

            int cnt = dist;
            while (cnt > 0) {
                nextR += directionMap.get(dir)[0];
                nextC += directionMap.get(dir)[1];
                if (!(nextR >= 0 && nextR < map.length && nextC >= 0 && nextC < map[0].length && !map[nextR][nextC].equals("X"))) {
                    break;
                }
                cnt--;
            }

            if (cnt == 0) {
                cur[0] = nextR;
                cur[1] = nextC;
            }
        }

        return cur;
    }
}
