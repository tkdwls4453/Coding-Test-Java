package Programmers;

import java.util.*;

/**
 * [1차] 캐시
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/17680
 */
public class Cache {
    public static void main(String[] args){
        int cacheSize = 0;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(cacheSize, cities));
    }

    public static int solution(int cacheSize, String[] cities) {
        List<String> cache = new ArrayList<>();
        Map<String, Boolean> contents = new HashMap<>();
        int time = 0;

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        for (String city : cities) {
            if (contents.containsKey(city.toUpperCase())) {
                cache.remove(city.toUpperCase());
                cache.add(city.toUpperCase());
                time += 1;
            }else{
                if (cache.size() == cacheSize) {
                    String removeItem = cache.get(0);
                    cache.remove(0);
                    contents.remove(removeItem);
                }
                cache.add(city.toUpperCase());
                contents.put(city.toUpperCase(),true);
                time += 5;
            }
        }
        return time;
    }
}
