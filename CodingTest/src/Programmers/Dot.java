package Programmers;

public class Dot {
    public static void main(String[] args) {
        System.out.println(solution(2, 4));

    }

    public static long solution(int k, int d) {
        long answer = 0;
        long y = d;
        for(long x=0; x<=d; x+=k){
            while(x * x + y * y > (long)d * d){
                y -= 1;
            }
            answer += y/k + 1;
        }
        return answer;
    }
}
