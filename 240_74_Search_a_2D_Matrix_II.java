/*

1. Comparing to 74, the problem changes from matrix[i + 1][0] > matrix[i][n - 1] to
matrix[i + 1][n - 1] > matrix[i][n - 1]. So we cannot do binary search any more.
We go from the upper-right corner and go down when num < target and go left when
num > target. If the pointer reaches the lower-left corner but still find no answer, 
return false.

2. The time complexity increases from O(log(m * n)) to O(m + n).
log(m * n) = logm + logn < m + n, when m > 1, n > 1.

*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }      
        int m = matrix.length;
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }
        int row = 0;
        int col = n - 1;
        while (col >= 0 && row < m) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
        
    }
}

/*
Time complexity: O(m + n)
Space complexity: O(1)
*/