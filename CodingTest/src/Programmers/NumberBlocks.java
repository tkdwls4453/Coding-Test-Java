package Programmers;

import java.util.Arrays;

/**
 * 숫자 블록
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/12923#qna
 */
public class NumberBlocks {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(100000014, 100000016)));
    }

    public static int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end-begin) + 1];

        for(long i=begin; i<=end; i++){
            answer[(int)(i-begin)] = (int)maxDivisor(i);
        }
        return answer;

    }

    public static long maxDivisor(long num){
        //10000000
        int result = 1;

        if(num == 1){
            return 0;
        }

        for(int i=2; i<=Math.sqrt(num); i++){
            if(num % i == 0){
                System.out.println(i);
                if(num / i <= 10000000){
                    result = Math.max(result, (int) (num / i));
                    break;
                }else{
                    result = Math.max(result, i);
                }
            }
        }
        return result;
    }
}
