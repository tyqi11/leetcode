/*
# Dynamic Programming

1. dp[i][j] is the minimum score to triangulate A[i] ~ A[j], assuming edge ij is
the last edge. So our goal is to get dp[0][n - 1].

2. How many ways to partition the polygon A[i], A[i + 1], ... A[j]. The middle
vertex k goes from i + 1 to j - 1. 

3. To avoid recursion, we use the bottom-up dynamic programming. The furthest
vertex j goes from 2 to n - 1. The nearest vertex i goes from j - 2 to 0, the 
middle vertex k goes from i + 1 to j - 1. For example:
	 0  1  2  3
A = [3, 7, 4, 5]
j = 2, i = 0, k = 1, dp[0][2] = min(MAX, 0 + 3 * 7 * 4 + 0) = 84 
j = 3, i = 1, k = 2, dp[1][3] = min(MAX, dp[1][2] + 7 * 4 * 5 + dp[2][3]) = 140
	   i = 0, k = 1, dp[0][3] = min(MAX, dp[0][1] + 3 * 7 * 3 + dp[1][3]) 
	                          = min(MAX, 0 + 63 + 140) = 203
	   	      k = 2, dp[0][3] = min(203, dp[0][2] + 3 * 4 * 5 + dp[2][3])
	   	                      = min(203, 84 + 60 + 0) = 144

*/

class Solution {
    
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int j = 2; j < n; j++) {
            for (int i = j - 2; i >= 0; i--) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + A[i] * A[k] * A[j] + dp[k][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}

/*
Time complexity: O(n^3), n = A.length
Space complexity: O(n^2)
*/