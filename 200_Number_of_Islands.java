/*
# DFS
# BFS
# Union-Find

1. DFS: This problem is similar to 547. Friend Circles. We iterate over each
island, visit all its neighbor lands and the neighbors of its neighbor lands.
This is one big island. Then for the next time, if we found one land not visited
before, it must be a part of a new land.

2. BFS

3. Union-Find not shown here. Use the coordinates as "p" and "q" to do union().

*/

// Solution 1: DFS
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int x, int y, boolean[][] visited) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length 
            || visited[x][y] 
            || grid[x][y] == '0') {
            return;
        }
        visited[x][y] = true;
        dfs(grid, x - 1, y, visited);
        dfs(grid, x + 1, y, visited);
        dfs(grid, x, y - 1, visited);
        dfs(grid, x, y + 1, visited);
    }
}


/*
Time complexity: O(n), n is the number of nodes
Space complexity: O(n), for the call stack
*/

/**************************************************************/
// Solution 2: BFS
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> q = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || grid[i][j] == '0') {
                    continue;
                }
                visited[i][j] = true;
                count++;
                q.offer(new int[]{i, j});
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    for (int[] neighbor : neighbors) {
                        int x = cur[0] + neighbor[0];
                        int y = cur[1] + neighbor[1];
                        if (x >= 0 && x < m && y >= 0 && y < n
                            && !visited[x][y] 
                            && grid[x][y] == '1') {
                            visited[x][y] = true;
                            q.offer(new int[]{x, y});
                        }
                    }
                } // end while
            }
        }
        return count;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/

/**************************************************************/
// Solution 3: Union-Find



/*
Time complexity: O()
Space complexity: O()
*/