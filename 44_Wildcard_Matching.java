/*
# Dynamic Programming

0. This is very much like 10. Regular Expression Matching.

1. In 10, '#*' represents any number of '#' (including 0), but in this problem, 
'*' is any sequence (including empty sequence). We do not need to care about the
character before '*' in this problem, which makes it easier than 10.

*/

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        // dp[i][0] = false, by default, empty pattern matches no string
        // dp[0][i], empty string matches only "*"
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sch = s.charAt(i - 1);
                char pch = p.charAt(j - 1);
                if (sch == pch || pch == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pch == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }// '*' acts like empty     || '*' acts like other sequences
            }
        }
        return dp[m][n];
    }
}

/*
Time complexity: O(m * n)
Space complexity: O(m * n)
*/