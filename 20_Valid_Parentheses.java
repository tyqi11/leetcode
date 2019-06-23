/*

1. When it comes to the matching of parentheses, we think of stacks. When we 
meet a left parenthese, we push it into the stack. And it comes a right part,
we check the stack, if 1) the stack is empty, which means there is no matching
with it, or 2) the one popped out is not a matching part, return false. Else,
after popping, we continue to check the next character.

*/

class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.offerLast(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pollLast() != '(') {
                    return false;
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pollLast() != '[') {
                    return false;
                }
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pollLast() != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/