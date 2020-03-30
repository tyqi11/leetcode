/*
prefixSum 
*/

class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        
        int res = 0;
        int len = 1; // square side length

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // prefixSum
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1]; 
                
                if (i >= len && j >= len && 
                    sum[i][j] - sum[i - len][j] - sum[i][j - len] + sum[i - len][j - len] <= threshold) {
                    res = len++; // found one already, try bigger next time
                }
                    
            }
        }
        
        return res;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/
