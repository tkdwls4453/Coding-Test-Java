package Programmers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 두 큐합 같게 만들기
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/118667
 */
public class MakeSumOfTwoQueueEqual {
    public static void main(String[] args){
        int[] queue1 = {1, 1};
        int[] queue2 = {1, 5};

        System.out.println(solution(queue1, queue2));
    }

    public static int solution(int[] queue1, int[] queue2) {
        int result = 0;
        Queue<Integer> queueOne = Arrays.stream(queue1)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        Queue<Integer> queueTwo = Arrays.stream(queue2)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        long sumOne = queueOne.stream().mapToLong(Integer::longValue).sum();
        long sumTwo = queueTwo.stream().mapToLong(Integer::longValue).sum();

        int size = queue1.length * 4;
        for (int i = 0; i < size; i++) {
            if (sumOne == sumTwo) {
                return result;
            }

            if (sumOne < sumTwo) {
                int num = queueTwo.poll();
                queueOne.offer(num);
                sumTwo -= num;
                sumOne += num;
                result++;
            }else{
                int num = queueOne.poll();
                queueTwo.offer(num);
                sumOne -= num;
                sumTwo += num;
                result++;
            }
        }

        return -1;
    }

}
