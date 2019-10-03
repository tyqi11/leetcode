/*
# DFS

1. It is easy to count how many islands there are. But we need to distinguish
them. We use a StringBuilder.

2. In each step when we find one more grid in the island, we add a char 
('T', 'B', 'L' or 'R' to show it's position). Once we finish iterating one island,
turn the StringBuilder into String and compare it with former Strings.


*/
class Solution {    
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int count = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j,sb);
                    if (set.add(sb.toString())) {
                        count++;
                    }
                }
             }
        }
        
        return count;
    }
    
    private void dfs(int[][] grid, int x, int y, StringBuilder sb) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
            grid[x][y] = 0; // avoid visiting again
            dfs(grid, x - 1, y, sb.append('L'));
            dfs(grid, x + 1, y, sb.append('R'));
            dfs(grid, x, y - 1, sb.append('B'));
            dfs(grid, x, y + 1, sb.append('T'));
        }
        
    }
}


/*
Time complexity: O(n), n is number of elements
Space complexity: O(n), for the call stack
*/