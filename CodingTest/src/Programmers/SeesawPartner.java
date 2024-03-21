package Programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 시소 짝궁
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/152996
 */
public class SeesawPartner {
    static final double[] partners = {1.0, 2.0 / 3, 2.0 / 4, 3.0 / 4};
    public static void main(String[] args) {
        int[] weights = {100, 180, 360, 100, 270};
        System.out.println(solution(weights));
    }
    public static long solution(int[] weights){
        long answer =  0;

        Map<Double, Integer> map = new HashMap<>();

        Arrays.sort(weights);

        for(int weight : weights){
            for (double partner : partners) {
                partner *= weight;

                if(map.containsKey(partner)){
                    answer+=map.get(partner);
                }
            }
            map.put((double) weight, map.getOrDefault((double) weight, 0) + 1);
        }

        System.out.println(map);
        return answer;
    }
}
