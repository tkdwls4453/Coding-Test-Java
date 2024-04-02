package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RentHotel {
    public static void main(String[] args) {
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        System.out.println(solution(book_time));
    }

    public static int solution(String[][] book_time){
        int answer = 0;
        List<int[]> times = new ArrayList<>();

        for(String[] time : book_time){
            String[] startArr = time[0].split(":");
            int start = Integer.parseInt(startArr[0]) * 60 + Integer.parseInt(startArr[1]);

            String[] endArr = time[1].split(":");
            int end = Integer.parseInt(endArr[0]) * 60 + Integer.parseInt(endArr[1]);

            times.add(new int[]{start, end});
        }

        Collections.sort(times, (o1, o2) -> o1[0] - o2[0]);

        List<Integer> lastTimes = new ArrayList<>();

        for(int[] time : times){
            boolean flag = true;
            for(int i=0; i<lastTimes.size(); i++){
                int lastTime = lastTimes.get(i);
                if(lastTime + 10 <= time[0]){
                    lastTimes.set(i, time[1]);
                    flag = false;
                    break;
                }
            }
            if(flag){
                lastTimes.add(time[1]);
            }
        }

        return lastTimes.size();
    }
}
