package Programmers;

import java.util.*;

/**
 * 두 큐합 같게 만들기
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/118667
 */
public class MakeSumOfTwoQueueEqual {
    public static void main(String[] args){
        int[] queue1 = {9, 7, 2};
        int[] queue2 = {9, 2, 11};

        System.out.println(solution(queue1, queue2));
    }

    public static int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        Queue<Long> q1 = createQueue(queue1);
        Queue<Long> q2 = createQueue(queue2);
        int size = queue1.length * 4;
        Long q1Sum = q1.stream().mapToLong(l -> l).sum();
        Long q2Sum = q2.stream().mapToLong(l -> l).sum();

        for (int i = 0; i < size; i++) {
            if (q1Sum < q2Sum) {
                Long num = q2.poll();
                q1.offer(num);
                q2Sum -= num;
                q1Sum += num;
            }else if(q1Sum > q2Sum){
                Long num = q1.poll();
                q2.offer(num);
                q1Sum -= num;
                q2Sum += num;
            }else{
                return i;
            }
        }
        return answer;
    }

    public static Queue<Long> createQueue(int[] arr){
        Queue<Long> queue = new LinkedList<>();

        for(int num : arr){
            queue.offer((long) num);
        }
        return queue;
    }

}
