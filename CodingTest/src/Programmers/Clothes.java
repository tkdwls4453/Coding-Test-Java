package Programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Clothes {
    static int answer = 0;
    public int solution(String[][] clothes) {

        Map<String, Boolean> visited = new HashMap<>();
        Set<String> set = new HashSet<>();
        for(String[] clothe : clothes){
            set.add(clothe[1]);
        }
        int max = set.size();
        dfs(0, clothes, visited, max);
        return answer;
    }

    public void dfs(int start, String[][] clothes, Map<String, Boolean> visited, int max){
        if(visited.size() >= max){
            return;
        }

        for(int i=start; i<clothes.length; i++){
            if(!visited.containsKey(clothes[i][1])){
                answer++;
                visited.put(clothes[i][1], true);
                dfs(i+1, clothes, visited, max);
                visited.remove(clothes[i][1]);
            }
        }
    }
}



