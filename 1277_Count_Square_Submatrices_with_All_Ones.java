/*

*/

class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        
        int cnt = 0;
        // make the first row same as matrix
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
            cnt += dp[0][j]; // if 1, 1 square of side 1
        }
        // make the first col same as matrix, no [0,0]
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0];
            cnt += dp[i][0]; // if 1, 1 square of side 1
        }
        
        // for the rest
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                int pre = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                dp[i][j] = pre + 1;
                cnt += dp[i][j];
            }
        }
        
        return cnt;
    }
}

/*
Time complexity: O(m * n), m*n is the size of the matrix
Space complexity: O(m * n)
*/