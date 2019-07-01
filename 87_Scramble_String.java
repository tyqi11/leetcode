/*

1. The first recursion method is brutal force. 

2. The second way uses dynamic programming. 
credit to: @shaka_shadows: https://leetcode.com/problems/scramble-string/discuss/29396/Simple-iterative-DP-Java-solution-with-explanation

 * S1 [   x1    |         x2         ]
 *    i         i + q                i + k - 1
 * 
 * here we have two possibilities:
 *      
 * S2 [   y1    |         y2         ]
 *    j         j + q                j + k - 1
 *    
 * or 
 * 
 * S2 [       y1        |     y2     ]
 *    j                 j + k - q    j + k - 1

*/

// Solution 1: recursion
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true; 
        } else if (s1.length() != s2.length()) {
            return false;
        }
        
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i)) 
             && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            } 
            if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i)) 
             && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) {
                return true;
            } 
        }
        return false;
    }
}

/*
Time complexity: O(n!) ?
Space complexity: O()
*/

/***********************************************************/
// Solution 2: dp

class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true; 
        } else if (s1.length() != s2.length()) {
            return false;
        }
        
        int n = s1.length();
        int[][][] dp = new int[n][n][n + 1];
        for (int k = 1; k <= n; k++) {
            for (int i = 0; i + k <= n; i++) {
                for (int j = 0; j + k <= n; j++) {
                    if (k == 1) {
                        dp[i][j][k] = (s1.charAt(i) == s2.charAt(j)) ? 1 : -1;
                    } 
                    if (dp[i][j][k] != 0) {
                        continue;
                    }
                    for (int q = 1; q < k; q++) {
                        if ((dp[i][j][q] == 1 && dp[i + q][j + q][k - q] == 1)
                         || (dp[i][j + k - q][q] == 1 && dp[i + q][j][k - q] == 1)) {
                            dp[i][j][k] = 1;
                        } else {
                            dp[i][j][k] = -1;
                        }
                        
                    }
                }
            }
        }
        return dp[0][0][n] == 1;
    }
}

/*
Time complexity: O(n^4)
Space complexity: O(n^3), for the dp array
*/