package Programmers;

import java.util.ArrayList;
import java.util.List;

public class ThatSongJustNow {
    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        System.out.println(solution(m, musicinfos));

    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        List<String> mList = new ArrayList<>();
        int maxPlayTime = 0;
        for(char c : m.toCharArray()){
            if(c=='#'){
                String last = mList.remove(mList.size()-1);
                mList.add(last+"#");
            }else{
                mList.add(c+"");
            }
        }

        for(String info : musicinfos){
            String[] splitArr = info.split(",");
            String[] start = splitArr[0].split(":");
            String[] end = splitArr[1].split(":");
            String name = splitArr[2];
            int playTime = (Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1])) -
                    (Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]));

            List<String> code = new ArrayList<>();

            for(char c : splitArr[3].toCharArray()){
                if(c=='#'){
                    String last = code.remove(code.size()-1);
                    code.add(last+"#");
                }else{
                    code.add(c+"");
                }
            }

            for(int i=0; i<=playTime; i++){
                int idx = i % code.size();
                int cnt = 0;
                for(String str : mList){

                    if(!code.get(idx).equals(str)){
                        break;
                    }
                    idx = (idx+1) % code.size();
                    cnt++;
                }

                if (cnt == mList.size() && maxPlayTime < playTime) {
                    maxPlayTime = playTime;
                    answer = name;
                    break;
                }
            }
        }

        if (answer.equals("")) {
            answer = "(None)";
        }
        return answer;
    }
}
