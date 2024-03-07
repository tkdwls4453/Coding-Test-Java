package Programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 다단계 칫솔 판매
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/77486
 */
public class MultiLevelToothbrushSales {
    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // <이름, [등록자 index, 총 수익]>
        Map<String, String> referralMap = new HashMap<>();
        Map<String, Integer> profitMap = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            referralMap.put(enroll[i], referral[i]);
            profitMap.put(enroll[i], 0);
        }

        for (int i = 0; i < seller.length; i++) {
            String name = seller[i];
            int profit = amount[i] * 100;

            while (referralMap.get(name) != null && profit != 0) {
                profitMap.put(name, profitMap.get(name) + (profit - (int) (profit * 0.1)));

                name = referralMap.get(name);
                profit = (int) (profit * 0.1);
            }
        }

        int[] result = new int[enroll.length];

        for (int i = 0; i < enroll.length; i++) {
            result[i] = profitMap.get(enroll[i]);
        }

        return result;
    }
}
