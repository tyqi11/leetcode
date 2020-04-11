/*
@lee215
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/discuss/392933/JavaC%2B%2BPython-Two-Pointers-and-Stack-Solution

# Two pointers
# Stack
*/
// Solution 1: two pointers
class Solution {
    public String removeDuplicates(String s, int k) {
        int i = 0;
        char[] res = s.toCharArray();
        int[] count = new int[s.length()]; // a new array to record consecutive duplicates
        for (char c : s.toCharArray()) {
            res[i] = c;
            count[i] = (i > 0 && res[i - 1] == c) ? count[i - 1] + 1 : 1;
            if (count[i] == k) {
                i -= (k - 1);
            } else {
                i++;
            }
        }
        return new String(res, 0, i);   

    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/

/****************************************************************************/
// Solution 2: stack @BillTang123

class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<Pair> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peekLast().ch == c) {
                stack.peekLast().cnt++;
                if (stack.peekLast().cnt == k) {
                    stack.pollLast();
                }
            } else {
                stack.offerLast(new Pair(1, c));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (Pair p : stack) {
            int cnt = p.cnt;
            char c = p.ch;
            for (int i = 0; i < cnt; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    class Pair {
        int cnt;
        char ch;
        
        Pair(int cnt, char ch) {
            this.cnt = cnt;
            this.ch = ch;
        }
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/
