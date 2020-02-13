/*
credit to: @tankztc https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution

# Dynamic Programming
We use two pointers to mark the begining and the ending of the current substring.
When we are working on charAt(i) and charAt(j), we may need to dp[i + 1], which
is the next row in the dp matrix. So i should go from n - 1 to 0.

i is the begining. In each row, it starts with i and j pointing at the same
character, which is [i,i] and in this way, the max length is 1. And then,
we move the ending character from i + 1 to the end.

The final result should be the substring from 0 to n - 1.

# Top-down recursive method with memorization
*/

// Dynamic Programming
class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}


/*
Time complexity: O(n * n)
Space complexity: O(n * n)
*/

/*************************************************************/
// top-down with memorization
class Solution {
    public int longestPalindromeSubseq(String s) {        
        int n = s.length();
        int[][] memo = new int[n][n];
        
        return helper(s, 0, n - 1, memo);
    }
    
    private int helper(String s, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        
        if (i > j) {
            return 0;
        } else if (i == j) {
            return 1;
        }
        
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        
        return memo[i][j];
    }
}