/*

*/

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0 && popped.length == 0) {
            return true;
        }
       
        int j = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int p : pushed) {
            stack.offerLast(p);
            
            while (!stack.isEmpty() && stack.peekLast() == popped[j]) {
                stack.pollLast();
                j++;
            }
            
        }
        
        return stack.isEmpty();
    }
}


/*
Time complexity: O(n)
Space complexity: O(n)
*/

