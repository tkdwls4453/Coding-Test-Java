package Programmers;

import java.util.Arrays;

/**
 * 110 옮기기
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/77886
 */
public class move110 {
    public static void main(String[] args) {
        String[] s = {"1110", "100111100", "0111111010"};
        System.out.println(Arrays.toString(solution(s)));

    }

    public static String[] solution(String[] s){
        String[] result = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            int cnt110 = 0;
            String str = s[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < str.length(); j++) {
                if (j + 1 < str.length() && j + 2 < str.length() && str.charAt(j) == '1' && str.charAt(j + 1) == '1' && str.charAt(j + 2) == '0') {
                    cnt110++;
                    j+=2;
                } else {
                    sb.append(str.charAt(j));
                }
            }
            System.out.println(sb.toString());
            System.out.println("cnt:" + cnt110);
            String insertStr = "";
            while (cnt110 > 0) {
                insertStr += "110";
                cnt110--;
            }

            if (sb.indexOf("0") == -1) {
                sb.insert(0, insertStr);
            } else {
                int idx = sb.lastIndexOf("0");
                sb.insert(idx + 1, insertStr);
            }
            System.out.println(sb.toString());
            result[i] = sb.toString();
        }
        return result;
    }


}
