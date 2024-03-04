package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 교점에 별 만들기
 * link: https://school.programmers.co.kr/learn/courses/30/lessons/87377
 */
public class CreateStarAtTheIntersection {
    public static void main(String[] args) {
        int[][] line = {{0, 1, -1}, {1, 0, -1}, {1, 0, 1}};
        System.out.println(Arrays.toString(solution(line)));

    }

    public static String[] solution(int[][] line){
        List<Coordinate> crossPointList = new ArrayList<>();
        long maxRow = Long.MIN_VALUE;
        long minRow = Long.MAX_VALUE;
        long maxCol = Long.MIN_VALUE;
        long minCol = Long.MAX_VALUE;

        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                Coordinate coordinate = calculateCrossPoint(line[i], line[j]);
                if(coordinate == null) continue;
                crossPointList.add(coordinate);
                maxRow = Math.max(maxRow, coordinate.y);
                minRow = Math.min(minRow, coordinate.y);
                maxCol = Math.max(maxCol, coordinate.x);
                minCol = Math.min(minCol, coordinate.x);
            }
        }
        List<String> result = new ArrayList<>();
        for (long i = maxRow; i >= minRow; i--) {
            String row = "";
            for (long j = minCol; j <= maxCol; j++) {
                if (crossPointList.contains(new Coordinate(j, i))) {
                    row += "*";
                } else {
                    row += ".";
                }
            }
            result.add(row);
        }
        return result.toArray(new String[result.size()]);
    }

    public static Coordinate calculateCrossPoint(int[] line1, int[] line2) {
        Long x = null;
        Long y = null;

        long a = line1[0], b = line1[1], e = line1[2];
        long c = line2[0], d = line2[1], f = line2[2];



        if (a * d - b * c == 0) {
            return null;
        }

        if ((b * f - e * d) % (a * d - b * c) == 0) {
            x = (b * f - e * d) / (a * d - b * c);
        }

        if ((e * c - a * f) % (a * d - b * c) == 0) {
            y = (e * c - a * f) / (a * d - b * c);
        }

        if (x == null || y == null) {
            return null;
        } else {
            return new Coordinate(x, y);
        }
    }

    public static class Coordinate{
        long x;
        long y;

        public Coordinate(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coordinate)) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}


