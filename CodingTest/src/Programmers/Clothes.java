package Programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Clothes {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> clothesCnt = new HashMap<>();

        for(String[] arr : clothes){
            clothesCnt.putIfAbsent(arr[1], 0);
            clothesCnt.put(arr[1], clothesCnt.get(arr[1]) + 1);
        }

        for(String key : clothesCnt.keySet()){
            answer *= (clothesCnt.get(key) + 1);
        }
        return answer - 1;
    }
}



