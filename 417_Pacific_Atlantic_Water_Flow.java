/*
# DFS
# BFS (not shown here)

1. Tracking the water flow from cells to the oceans makes us to iterate over all the
m * n cells each time. It is very time consuming. So we try an opposite way and consider
water going from ocean to the cells. Then we have only 2 * (m + n) starting points.

2. From cells next to the pacific ocean, we iterate over all cells in the matrix and
check if pacific water can flow from them. Then we check cells next to the atlantic 
ocean to check if atlantic water can be reached from them. The intersection will be
the answer.

*/

// Solution 1: DFS
class Solution {
    int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        for (int i = 0; i < m; i++) { // left and right border. ocean height is MIN
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0); // left, next to pacific 
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, n - 1); // right, next to atlantic
        }
        for (int i = 0; i < n; i++) { // up and bottom border
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i); // up, next to pacific
            dfs(matrix, atlantic, Integer.MIN_VALUE, m - 1, i); // bottom, next to atlantic
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(i);
                    cur.add(j);
                    res.add(cur);
                }
            }
        }
        
        return res;
    }
    
    private void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
            return;
        }
        if (matrix[x][y] >= height) {
            visited[x][y] = true;
            for (int[] neighbor : neighbors) {
                dfs(matrix, visited, matrix[x][y], x + neighbor[0], y + neighbor[1]);
            }
        }
    }
}

/*
Time complexity: O(m * n), m * n is the number of cells in the matrix
Space complexity: O(m * n)
*/