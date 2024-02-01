package LeetCode;

import java.sql.Array;
import java.util.*;

/**
 * 207. Course Schedule
 * link : https://leetcode.com/problems/course-schedule/description/
 */
public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0},{0,1}};

        System.out.println(canFinish(numCourses, prerequisites));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites){
        Map<Integer, List<Integer>> edges = new HashMap<>();

        int[] indgree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            edges.putIfAbsent(prerequisite[1], new ArrayList<>());
            edges.get(prerequisite[1]).add(prerequisite[0]);
            indgree[prerequisite[0]]++;
        }

        List<Integer> sortList = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indgree[i] == 0) {
                visited[i] = true;
                sortList.add(i);
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (edges.containsKey(cur)) {
                for (int next :edges.get(cur)) {
                    indgree[next]--;
                    if (indgree[next] == 0) {
                        visited[next] = true;
                        sortList.add(next);
                        queue.add(next);
                    }
                }
            }
        }

        if (sortList.size() != numCourses) {
            return false;
        }
        return true;
    }
}
