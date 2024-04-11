package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class TruckPassingOverTheBridge {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int curWeight = 0;
        Queue<int[]> bridgeTrucks = new LinkedList<>();
        Queue<Integer> readyTrucks = new LinkedList<>();

        for(int truck : truck_weights){
            readyTrucks.offer(truck);
        }

        int nextTruck = readyTrucks.poll();
        curWeight += nextTruck;

        bridgeTrucks.offer(new int[]{nextTruck, 1});
        time++;


        while(!bridgeTrucks.isEmpty() || !readyTrucks.isEmpty()){
            move(bridgeTrucks);
            if(!bridgeTrucks.isEmpty() && bridgeTrucks.peek()[1] > bridge_length){
                int[] removeTruck = bridgeTrucks.poll();
                curWeight -= removeTruck[0];
            }

            if(!readyTrucks.isEmpty() && readyTrucks.peek() + curWeight <= weight){
                nextTruck = readyTrucks.poll();
                curWeight += nextTruck;

                bridgeTrucks.offer(new int[]{nextTruck, 1});
            }
            time++;
        }
        return time;
    }
    public void move(Queue<int[]> bridgeTrucks){
        for(int[] truck : bridgeTrucks){
            truck[1]++;
        }
    }
}
