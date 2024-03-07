package Programmers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 주차 요금 계산
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/92341
 */
public class ParkingFeeCalculation {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        solution(fees, records);
        System.out.println(Arrays.toString(solution(fees, records)));

    }

    public static int[] solution(int[] fees, String[] records){
        int basicMinute = fees[0];
        int basicFee = fees[1];
        int unitMinute = fees[2];
        int unitFee = fees[3];

        // < 차량번호, [시각, 누적 주차 시간] >
        Map<String, int[]> recordMap = new HashMap<>();

        for(String record : records){
            String[] recordSplit = record.split(" ");
            String time = recordSplit[0];
            String carNum = recordSplit[1];
            String act = recordSplit[2];

            if(act.equals("IN")) {
                recordMap.putIfAbsent(carNum, new int[2]);
                recordMap.get(carNum)[0] = changeMinute(time);
            }else{
                int enterMinute = recordMap.get(carNum)[0];
                int exitMinute = changeMinute(time);

                recordMap.get(carNum)[0] = -1;
                recordMap.get(carNum)[1] += exitMinute - enterMinute;
            }
        }

        List<Integer> feeList = new ArrayList<>();
        List<String> sortedCarNumber = recordMap.keySet().stream()
                .sorted()
                .collect(Collectors.toList());

        for (String carNumber : sortedCarNumber) {

            if (recordMap.get(carNumber)[0] != -1) {
                int enterMinute = recordMap.get(carNumber)[0];
                int exitMinute = changeMinute("23:59");
                recordMap.get(carNumber)[1] += exitMinute - enterMinute;
            }

            feeList.add(calculateTotalFee(recordMap.get(carNumber)[1], basicMinute, basicFee, unitMinute, unitFee));
        }

        return feeList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int changeMinute(String time){
        int h = Integer.parseInt(time.split(":")[0]);
        int m = Integer.parseInt(time.split(":")[1]);

        return h * 60 + m;
    }

    public static int calculateTotalFee(int totalMinute, int basicMinute, int basicFee, int unitMinute, int unitFee) {
        if (totalMinute <= basicMinute) {
            return basicFee;
        }

        return basicFee + (int)Math.ceil((totalMinute - basicMinute + 0.0) / unitMinute) * unitFee;
    }
}
