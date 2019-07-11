/*

1. Searching a 2D matrix is similar to a 1D array. The key point is the change the
index to (x, y) position. THE DIVISOR IS N! NOT M!
x = mid / n;
y = mid % n; 

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
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int x = mid / n;
            int y = mid % n;
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}

/*
Time complexity: O(log(m * n))
Space complexity: O(1)
*/