/*
# DFS (or backtracking, as backtracking is a modified DFS)

*/

class Solution {
    int maxGold = 0;
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int getMaximumGold(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }    
        
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    visited = new boolean[m][n];
                    dfs(grid, i, j, visited, 0);
                }
            }
        }
        
        return maxGold;
    }
    
    private void dfs(int[][] grid, int x, int y, boolean[][] visited, int sum) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] == 0) {
            return;
        }
        
        sum += grid[x][y];
        visited[x][y] = true;
        maxGold = Math.max(sum, maxGold);
        
        for (int[] d : dirs) {
            int i = d[0] + x;
            int j = d[1] + y;
            dfs(grid, i, j, visited, sum);            
        }
        
        visited[x][y] = false;        
    }
}


/*
Time complexity: O(4^(mn)) 
Space complexity: O(m * n)
*/
