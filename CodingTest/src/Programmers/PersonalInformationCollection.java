package Programmers;

import java.util.*;

/**
 * 개인정보 수집 유효기간 도움말
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/150370
 */
public class PersonalInformationCollection {
    public static void main(String[] args) {
        String today = "2020.01.01";
        String[] terms = {"Z 3", "D 5"};
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};

        System.out.println(Arrays.toString(solution(today, terms, privacies)));
    }

    public static int[] solution(String today, String[] terms, String[] privacies){
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String term : terms) {
            String type = term.split(" ")[0];
            int month = Integer.parseInt(term.split(" ")[1]);

            map.put(type, month);
        }

        int num = 1;
        for (String privacy : privacies) {
            // 수집 일자 -> year, month, day 로 split
            String date = privacy.split(" ")[0];
            String type = privacy.split(" ")[1];

            String[] splitDate = date.split("\\.");

            int year = Integer.parseInt(splitDate[0].trim());
            int month = Integer.parseInt(splitDate[1].trim()) + map.get(type);
            int day = Integer.parseInt(splitDate[2].trim());

            year += month / 12;
            month %= 12;

            if (month == 0) {
                month = 12;
                year--;
            }

            today = today.replaceAll("\\.", "");


            int dateInt = year * 10000 + month * 100 + day;
            int todayInt = Integer.parseInt(today);

            if (dateInt <= todayInt) {
                answer.add(num);
            }
            num++;
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

}
