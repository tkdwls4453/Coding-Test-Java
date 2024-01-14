package LeetCode;

import java.util.Stack;

/**
 * Longest Valid Parentheses
 * link : https://leetcode.com/problems/longest-valid-parentheses/description/
 */
public class LongestValidParentheses {
    public static void main(String[] args){
        String s = "()()";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s){
        int result = 0;
        Stack<Element> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char now = s.charAt(i);
            if(now == '('){
                stack.push(new Element(i, now));
            }else{
                if(!stack.isEmpty() && stack.peek().c == '('){
                    Element pop = stack.pop();
                    if (!stack.isEmpty()) {
                        Element peek = stack.peek();
                        result = Math.max(result, i - peek.idx);
                    }else{
                        result = Math.max(result, i + 1);
                    }
                }else{
                    stack.push(new Element(i, now));
                }
            }
        }
        return result;
    }

    public static class Element{
        int idx;
        char c;

        public Element(int idx, char c){
            this.idx = idx;
            this.c = c;
        }
    }
}
