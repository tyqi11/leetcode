class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int bottom = m - 1;
        int right = n - 1;
        int start = 0;
        while (ans.size() < m * n) {
            for (int col = start; col <= right && ans.size() < m * n; col++) {
                ans.add(matrix[start][col]);
            }
            for (int row = start + 1; row < bottom && ans.size() < m * n; row++) {
                ans.add(matrix[row][right]);
            }
            for (int col = right; col >= start && ans.size() < m * n; col--) {
                ans.add(matrix[bottom][col]);
            }
            for (int row = bottom - 1; row > start && ans.size() < m * n; row-- ) {
                ans.add(matrix[row][start]);
            }
            start++;
            right--;
            bottom--;
        }
        return ans;
    }
    
}