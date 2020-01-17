/*
# DFS

1. It is easy to count how many islands there are. But we need to distinguish
them. We use a StringBuilder.

2. In each step when we find one more valid grid in the island, we add a String 
("T", "D", "L", "R") to show it's relative position to the previous. Once we 
finish iterating one island, turn the StringBuilder into String and compare it with former Strings.


*/
class Solution {    
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int count = 0;
        Set<String> islands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "");
                    if (islands.add(sb.toString())) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    private void dfs(int[][] grid, int i, int j, StringBuilder sb, String str) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        
        grid[i][j] = 0;
        sb.append(str);
        dfs(grid, i - 1, j, sb, "D");
        dfs(grid, i + 1, j, sb, "T");
        dfs(grid, i, j - 1, sb, "L");
        dfs(grid, i, j + 1, sb, "R");
        sb.append("E"); // VERY IMP!!!! SHOW THE END OF ONE SEARCH
    }
}

// without "E", 1 0 0 and 1 1 1 will give the same result.
//              1 1 1     1 0 0
// without "E": DRR       DRR
// with "E":    DRREEE    DERREE

/*
Time complexity: O(n), n is number of elements
Space complexity: O(n), for the call stack
*/