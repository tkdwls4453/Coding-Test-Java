package Programmers;

import java.util.*;

/**
 * 디스크 컨트롤러
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/42627
 */
public class DiskController {
    public static void main(String[] args) {
        int[][] jobs = {{1, 4}, {7, 9}, {1000, 3}};
        System.out.println(solution(jobs));
    }
    public static int solution(int[][] jobs){
        int time = 0;
        int finishedJobCnt = 0;
        int totalJobTime = 0;
        Queue<int[]> pq = new PriorityQueue<>((e1, e2) -> {
            return e1[1] - e2[1];
        });

        Arrays.sort(jobs, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        int idx = 0;
        while(finishedJobCnt < jobs.length){
            while (idx < jobs.length && jobs[idx][0] <= time) {
                pq.add(jobs[idx++]);
            }

            if(!pq.isEmpty()){
                int[] cur = pq.remove();
                time += cur[1];
                totalJobTime += time - cur[0];
                finishedJobCnt++;
            }else{
                time++;
            }
        }

        return totalJobTime / finishedJobCnt;
    }
}
