/*
# Dynamic Programming
dp[i][j] represents the edge length of the largest square ENDING 
(bottom-right corner) at matrix[i-1][j-1]

dp[i][j] = 2, only if dp[i - 1][j - 1] = 1, dp[i][j - 1] = 1, dp[i][j - 1] = 1
dp[i][j] = 3, only if all those three are at least 2
*/

class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int a = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    a = Math.max(a, dp[i][j]);
                }
            }
        }
        
        return a * a;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/
