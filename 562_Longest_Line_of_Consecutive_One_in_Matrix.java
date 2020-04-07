/*
The way I thought of was like brutal force DFS, which leads to TLE at the 28th test case.
Here are three ways to solve the problem:
1. Dynamic Programming: It takes only one iteration of all spots to get the result. But
it also takes extra space to for the dy array. (m * n but can be reduced to n).
2. Use 1D array to save separately to save space.
3. Also we can just iterate 4 times, each time focusing on different direction. It is
4 times time-comsuming, but takes no extra space.

In my opinion, the second is the most recommended. In reality, the third is the fastest.
*/
// Solution 1: Dynamic Programming
class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        
        int m = M.length, n = M[0].length;
        int[][][] dp = new int[m][n][4]; // for horizontal, vertical, diag and anti-diag
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 0) {
                    continue;
                }
                
                // for four directions, start with 1
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] = 1;
                }
                // 0 for horizontal, so compare with j - 1
                if (j > 0) {
                    dp[i][j][0] += dp[i][j - 1][0];
                }
                // 1 for vertical
                if (i > 0) {
                    dp[i][j][1] += dp[i - 1][j][1];
                }
                // 2 for diagonal
                if (j > 0 && i > 0) {
                    dp[i][j][2] += dp[i - 1][j - 1][2];
                }
                // 3 for anti-diagonal
                if (j + 1 < n && i > 0) {
                    dp[i][j][3] += dp[i - 1][j + 1][3];
                }
                
                ans = Math.max(ans, Math.max(dp[i][j][0], Math.max(dp[i][j][1], Math.max(dp[i][j][2], dp[i][j][3]))));
            }
        }
        
        return ans;
    }
}


/*
Time complexity: O(m * n * 4) = O(m*n)
Space complexity: O(m * n * 4) = O(m*n)
*/
// can be optimized because the current row uses data from only the previous row. 

/***********************************************************************/
// Also, a similar Solution 2:
class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        
        int m = M.length, n = M[0].length;
        int ans = 0;
        
        int[] col = new int[n];
        int[] diag = new int[m + n];
        int[] antiD = new int[m + n];
        
        
        for (int i = 0; i < m; i++) {
            int row = 0;
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 0) {
                    row = 0;
                    col[j] = 0;
                    diag[m - i + j] = 0;
                    antiD[j + i] = 0;
                    
                } else {
                    row++;
                    col[j]++;
                    diag[m - i + j]++;
                    antiD[j + i]++;
                    ans = Math.max(ans, Math.max(row, Math.max(col[j], Math.max(diag[i + j], antiD[m - i + j]))));
                }
                
            }
        }
        
        return ans;
    }
}

/***********************************************************************/
// Solution 3
class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        
        int m = M.length, n = M[0].length;
        int max = 0, hori = 0, vert = 0, inc = 0, desc = 0;
        for (int i = 0; i < m; i++) {
            hori = 0;
            for (int j = 0; j < n; j++) {
                hori = M[i][j] > 0 ? hori + 1 : 0;
                max = Math.max(max, hori);
            }
        }
        for (int j = 0; j < n; j++) {
            vert = 0;
            for (int i = 0; i < m; i++) {
                vert = M[i][j] > 0 ? vert + 1 : 0;
                max = Math.max(max, vert);
            }
        }
        for (int k = 0; k < m + n; k++) {
            inc = 0; desc = 0;
            // increasing start from left cells then bottom cells
            for (int i = Math.min(k, m - 1), j = Math.max(0, k - m); i >= 0 && j < n; i--, j++) {
                inc = M[i][j] > 0 ? inc + 1 : 0;
                max = Math.max(max, inc);
            }
            // decreasing start from left cells then top cells;
            for (int i = Math.max(m - 1 - k, 0), j = Math.max(0, k - m); i < m && j < n; i++, j++) {
                desc = M[i][j] > 0 ? desc + 1 : 0;
                max = Math.max(max, desc);
            }
        }
        return max;        
    }
}