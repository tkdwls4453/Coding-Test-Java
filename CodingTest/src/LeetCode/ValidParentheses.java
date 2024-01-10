package LeetCode;

import java.util.*;

/**
 * Valid Parentheses
 * link : https://leetcode.com/problems/valid-parentheses/description/
 */
public class ValidParentheses {
    public static void main(String args[]) {
        String s = "()[]{}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s){
        Deque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()){
            if (c == '(') {
                stack.addLast(')');
            } else if (c == '[') {
                stack.addLast(']');
            } else if (c == '{') {
                stack.addLast('}');
            } else if (!stack.isEmpty() && c == stack.peekLast()) {
                stack.removeLast();
            }else{
                return false;
            }
        }

        return stack.isEmpty();
    }
}
