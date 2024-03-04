package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 혼자 놀기의 달인
 * link : https://translate.google.co.kr/?hl=ko&sl=ko&tl=en&text=%ED%98%BC%EC%9E%90%20%EB%86%80%EA%B8%B0%EC%9D%98%20%EB%8B%AC%EC%9D%B8%0A&op=translate
 */
public class masterOfPlayingAlone {
    static List<Integer> cntList = new ArrayList<>();

    public static void main(String[] args) {
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};
        System.out.println(solution(cards));
    }

    public static int solution(int[] cards){
        boolean[] visited = new boolean[cards.length];

        for (int i = 0; i < cards.length; i++) {
            if (!visited[i]) {
                dfs(cards, i, visited, 0);
            }
        }

        if(cntList.size() == 1) return 0;

        Collections.sort(cntList, Collections.reverseOrder());
        return cntList.get(0) * cntList.get(1);
    }

    public static void dfs(int[] cards, int num, boolean[] visited, int cnt) {
        if (visited[num]) {
            cntList.add(cnt);
            return;
        }

        visited[num] = true;
        dfs(cards, cards[num] - 1, visited, cnt + 1);
    }
}
