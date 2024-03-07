package Programmers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * k진수에서 소수 개수 구하기
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/92335
 */
public class FindNumberOfDecimalsInBaseK {
    public static void main(String[] args) {
        System.out.println(solution(110011, 10));
    }

    public static int solution(int n, int k){
        int result = 0;
        StringBuffer sb = new StringBuffer();

        while(n != 0){
            sb.insert(0, n % k);
            n /= k;
        }


        String[] splits = sb.toString().split("0");

        List<Long> numList = new ArrayList<>();

        for (String numStr : splits) {
            if (!numStr.equals("")) {
                numList.add(Long.parseLong(numStr));
            }
        }

        for (long num : numList) {
            if (isPrime(num)) {
                result++;
            }
        }
        return result;
    }

    public static boolean isPrime(long num){
        if(num == 1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
