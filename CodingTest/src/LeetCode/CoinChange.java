package LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Coin Change
 * link : https://leetcode.com/problems/coin-change/description/
 */

public class CoinChange {
    public static void main(String[] args){
        int[] coins = {2};
        int amount = 1;

        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount){
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount+1];
        if (amount == 0) {
            return 0;
        }
        for(int coin : coins){
            queue.offer(new int[]{amount - coin, 1});
            if(amount-coin >= 0){
                visited[amount-coin] = true;
            }
        }

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            if(now[0] == 0){
                return now[1];
            }
            if(now[0] < 0 ){
                continue;
            }
            for(int coin : coins){
                if(now[0]-coin >=0 && !visited[now[0]-coin]){
                    queue.offer(new int[]{now[0] - coin, now[1] + 1});
                    visited[now[0]-coin] = true;
                }
            }
        }
        return -1;
    }
}

