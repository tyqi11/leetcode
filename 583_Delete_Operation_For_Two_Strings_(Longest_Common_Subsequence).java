/*
# Dynamic Programming

Longest Common Subsequence problem.

*/

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        char[] ch1 = word1.toCharArray();
        char[] ch2 = word2.toCharArray();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m - dp[m][n] + n - dp[m][n];
    }
}

/*
Time complexity: O(m * n), m and n are the lengths of word1 and word2
Space complexity: O(m * n), for dp array, can be optimized
*/