package Programmers;

import java.util.Stack;

public class MakingBigNumbers {
    public static void main(String[] args) {
        String number = "1924";
        int k = 2;
        System.out.println(solution(number, k));

    }

    public static String solution(String number, int k){
        String answer = "";

        Stack<Character> stack = new Stack<>();

        for (char c : number.toCharArray()) {
            if(stack.isEmpty()){
                stack.push(c);
            }else{
                while (!stack.isEmpty() && stack.peek() < c && k > 0) {
                    stack.pop();
                    k--;
                }
                stack.push(c);
            }
        }

        for (int i = 0; i < k; i++) {
            stack.pop();
        }

        for (char c : stack) {
            answer += c;

        }
        return answer;
    }
}
