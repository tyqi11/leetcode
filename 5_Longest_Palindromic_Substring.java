/*
# Brute force
Taking each character as the center of the palindrome, go to the two ends.

# Manacher Algorithm (How can a human be so so smart!!!)



*/

class Solution {
    public String longestPalindrome(String s) {
        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = extend(s, i, i);
            int len2 = extend(s, i, i + 1);
            if (maxLen < Math.max(len1, len2)) {
                maxLen = len1 > len2 ? len1 : len2;
                start = len1 > len2 ? (i - len1 / 2) : (i + 1 - len2 / 2);
            }            
        }
        return s.substring(start, start + maxLen);
    }
    
    private int extend(String s, int i, int j) {
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        return j - i - 1; // i [[[]]] j
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(1)
*/


/**********************************************************************/
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        // 1. preparation to make new String and array        
        StringBuilder sb = new StringBuilder();
        sb.append("$#"); // '@' mark the beginning
        for (char c : s.toCharArray()) {
            sb.append(c);
            sb.append('#');
        }
        sb.append('@'); // mark the end
        String t = sb.toString();
        int[] p = new int[t.length()];
        
        // 2. start searching
        int C = 0; // C: center of the main palindrome
        int R = 0; // R: right bound of the main palindrome
        int resCtr = 0, resLen = 1;
        
        // @#A#B#C....
        // 0123456....  the first valid character is at 1
        for (int i = 1; i < t.length() - 1; i++) { // next center, first '#' to last '#'
            int mirr = 2 * C - i; // mirr is the mirror location of i
            
            if (i < R) {
                p[i] = Math.min(R - i, p[mirr]);
            }
            
            while (t.charAt(i + (1 + p[i])) == t.charAt(i - (1 + p[i]))) {
                p[i]++;
            }

            
            if (R < i + p[i]) {
                C = i;
                R = i + p[i];
            }

            if (resLen < p[i]) {
                resLen = p[i];
                resCtr = i;
            }
        }
        int start = (resCtr - resLen) / 2;
        return s.substring(start, start + resLen);
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), for the new String
*/