/*

The O(m + n) space complexity solution is easy to think of. But we are asked to
improve it to O(1).

So we make use of the first row and first column to record the status of the
corresponding row/column. One thing to be careful is that the [0, 0] cannot indicate
the first row and first column at the same time. So we need a boolean to record
the status of either the first row or the first column.

*/

class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstColZero = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
            } // use [0,0] for 1st row, set boolean for 1st column
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        } 
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (firstColZero) {
                matrix[i][0] = 0;
            }
        } // from last row/col to first row/col 
    }
}

/*
Time complexity: O(m * n)
Space complexity: O(1)
*/
