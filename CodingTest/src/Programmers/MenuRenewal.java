package Programmers;

import java.util.*;

/**
 * 2021 KAKAO BLIND RECRUITMENT - 메뉴 리뉴얼
 * linke : https://school.programmers.co.kr/learn/courses/30/lessons/72411?language=java
 */
public class MenuRenewal {
    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2,3,4};
        System.out.println(Arrays.toString(solution(orders, course)));

    }

    public static String[] solution(String[] orders, int[] course) {

        HashMap<String, Integer> map = new HashMap<>();
        for (String order : orders) {
            for (int cnt : course) {
                backtrack(order, cnt, 0, new ArrayList<String>(), map);
            }
        }
        ArrayList<String> resultList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                resultList.add(entry.getKey());
            }
        }
        Collections.sort(resultList);

        String[] answer = new String[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i);
        }

        return answer;
    }

    public static void backtrack(String order, int count, int start, ArrayList<String> cur, HashMap<String, Integer> map) {
        if (cur.size() == count) {
            String str = "";
            for (String s : cur) {
                str += s;
            }
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            }else{
                map.put(str, 1);
            }
        }

        for (int i = start; i < order.length(); i++) {
            cur.add(order.charAt(i) + "");
            backtrack(order, count, i + 1, cur, map);
            cur.remove(cur.size() - 1);
        }
    }
}
