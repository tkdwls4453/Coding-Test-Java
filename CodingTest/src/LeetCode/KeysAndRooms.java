package LeetCode;

import java.util.*;

/**
 * Keys and Rooms
 * link : https://leetcode.com/problems/keys-and-rooms/
 */
public class KeysAndRooms {
    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(new ArrayList<>(Arrays.asList(1, 3)));
        rooms.add(new ArrayList<>(Arrays.asList(3, 0, 1)));
        rooms.add(new ArrayList<>(Arrays.asList(2)));
        rooms.add(new ArrayList<>(Arrays.asList(0)));

        System.out.println(canVisitAllRoomsBFS(rooms));
        System.out.println(canVisitAllRoomsDFS(rooms));

    }

    /**
     * BFS 풀이 방식
     */
    public static boolean canVisitAllRoomsBFS(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : rooms.get(cur)) {
                if(!visited[next]){
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }

        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                return false;
            }
        }

        return true;
    }

    /**
     * DFS 풀이 방식
     */
    public static boolean canVisitAllRoomsDFS(List<List<Integer>> rooms) {
       boolean[] visited = new boolean[rooms.size()];

       dfs(rooms, visited, 0);

        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                return false;
            }
        }

        return true;
    }

    public static void dfs(List<List<Integer>> rooms, boolean[] visited, int start){
        visited[start] = true;

        for(int next : rooms.get(start)){
            if(!visited[next]){
                dfs(rooms, visited, next);
            }
        }
    }

}
