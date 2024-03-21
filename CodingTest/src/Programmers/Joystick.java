package Programmers;

import java.util.Arrays;

/**
 * 조이스틱
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/42860
 */
public class Joystick {
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) {
        String name = "JAN";
    }

    public static int solution(String name){


        char[] nameArr = name.toCharArray();
        int[] distanceArr = new int[nameArr.length];

        for(int i=0; i<distanceArr.length; i++){
            distanceArr[i] = nameArr[i] - 'A' < 26 - (nameArr[i] - 'A') ? nameArr[i] - 'A' : 26 - (nameArr[i] - 'A');
        }
        dfs(Arrays.stream(distanceArr).sum(), 0, 0, distanceArr, 0);
        System.out.println(Arrays.toString(distanceArr));

        return answer;
    }

    public static void dfs(int sum, int cost, int cur, int[] distanceArr, int depth){
        if(sum == 0){
            answer = Math.min(answer, cost);
            return;
        }

        if (depth == distanceArr.length) {
            return;
        }

        if (depth == 0) {
            cost += distanceArr[cur];
            sum -= distanceArr[cur];
            distanceArr[cur] = 0;
        }

        int front = cur + 1 < distanceArr.length ? cur +1 : 0;
        int back = cur - 1 >= 0 ? cur - 1 : distanceArr.length - 1;

        // 앞으로 이동
        int frontCost = distanceArr[front];
        distanceArr[front] = 0;
        dfs(sum - frontCost, cost + 1 + frontCost, front, distanceArr, depth+1);
        distanceArr[front] = frontCost;

        // 뒤로 이동
        int backCost = distanceArr[back];
        distanceArr[back] = 0;
        dfs(sum - backCost, cost + 1 + backCost, back, distanceArr, depth+1);
        distanceArr[back] = backCost;
    }
}
