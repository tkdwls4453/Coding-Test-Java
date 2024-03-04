package Programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 둘만의 암호
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/155652
 */
public class OurPassword {
    public static void main(String[] args){
        String s = "aukks";
        String skip = "wbqd";
        int index = 5;

        System.out.println(solution(s, skip, index));
    }

    public static String solution(String s, String skip, int index){
        String result = "";
        char[] sArr = s.toCharArray();
        HashMap<Character, Boolean> skipMap = new HashMap<>();

        for (char c : skip.toCharArray()) {
            if(!skipMap.containsKey(c)){
                skipMap.put(c, true);
            }
        }

        for(int i=0; i<sArr.length; i++){
            char cur = sArr[i];

            int cnt = index;

            while(cnt > 0){
                cur++;
                if(cur > 122) cur -= 26;
                if(skipMap.containsKey((char)cur)) continue;
                cnt--;
            }
            sArr[i] = cur;
        }
        for (char c : sArr) {
            result += c;
        }
        return result;
    }
}
