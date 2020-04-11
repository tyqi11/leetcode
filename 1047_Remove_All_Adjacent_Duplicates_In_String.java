/*

*/
// My intuitive way, but not most time-efficient
class Solution {
    public String removeDuplicates(String S) {
        int i = 0, j = 0;
        char[] chs = S.toCharArray();
        while (j < S.length()) {
            j = i + 1;

            while (i >= 0 && j < S.length() && chs[i] == chs[j]) {
                chs[i--] = '#';
                while (i >= 0 && chs[i] == '#') {
                    i--;
                }
                chs[j++] = '#';
            }
            
            i = j;
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : chs) {
            if(c != '#') {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}

/*
Time complexity: O(n^2)? Because for #######aa******, i always reach the beginning
Space complexity: O(n), n is the length of the string
*/

// @lee215 
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/discuss/294893/JavaC%2B%2BPython-Two-Pointers-and-Stack-Solution
class Solution {
    public String removeDuplicates(String S) {
        int i = 0;
        char[] res = S.toCharArray();
        for (char c : S.toCharArray()) {
            res[i] = c;
            if (i > 0 && res[i - 1] == res[i]) {
                i--;
            } else {
                i++;
            }
        }
        return new String(res, 0, i);   
    }
}
/*
Time complexity: O(n), n is the length of the string
Space complexity: O(n)
*/

/*******************************************************************/
// Stack by @lee215
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/discuss/294893/JavaC%2B%2BPython-Two-Pointers-and-Stack-Solution
// Java solution by @krrs
class Solution {
    public String removeDuplicates(String S) {
        Deque<Character> stack = new ArrayDeque<>();        
        for (char c : S.toCharArray()) {
            if (!stack.isEmpty() && stack.peekLast() == c) {
                stack.pollLast();
            } else {
                stack.offerLast(c);
            }    
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}

/******************************************************************/
// concatenate string
class Solution {
    public String removeDuplicates(String S) {
        int i = 0, j = 0;
        String str = S;
        while (j < str.length()) {
            j = i + 1;
            while (i >= 0 && j < str.length() && str.charAt(i) == str.charAt(j)) {
                str = str.substring(0, i) + (j + 1 < str.length() ? str.substring(j + 1) : "");
                j--;
                i--;
            }
            
            i = j;
        }
        
        return str;
    }
}