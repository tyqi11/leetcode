/*
# Dynamic Programming

0. This is like Longest Common Subsequence. We draw a table to solve the problem.
To optimize, the 2D matrix can be optimized to a 1D array, because we only need
the result from the previous row. Numbers in the table, from left to right are 
ascending, from top to bottom are also ascending.

1. To draw a line, it must be A[i] == B[i]. If they are equal, we increase
the amount of lines by 1 from A[i - 1] and B[j - 1], in case we use one element
for more than once.

2. If they are not equal, as the elements in the table will never decrease, 
dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]). It means that until comparing 
A[i] and B[j], the result will be not less than A[i] and B[j - 1], and also 
not less than A[i - 1] and B[j]. The last element in the table is the answer.

3. In the optimization, as we may need a previous dp[i - 1][j - 1], we need to 
use temp variables to keep it. And temp and pre needs to be initialized before
each row (A[i]), because start from each row, pre = 0.

*/

class Solution {
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}

/*
Time complexity: O(m * n), m = A.length, n = B.length
Space complexity: O(m * n)
*/

// Further optimized, use 1D array of size O(n) instead of O(m * n)
// initialize temp and pre before each row
class Solution {
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[] dp = new int[n + 1]; 
        for (int i = 1; i <= m; i++) {
            int temp = 0;   // IMP
            int pre = 0;    // IMP
            for (int j = 1; j <= n; j++) {
                temp = dp[j];
                if (A[i - 1] == B[j - 1]) {
                    dp[j] = pre + 1;        // IMP
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                pre = temp;
            }
        }
        return dp[n];
    }
}

/*
Time complexity: O(m * n), m = A.length, n = B.length
Space complexity: O(n)
*/