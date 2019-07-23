/*
# Union Find
# DFS/BFS

0. This problem seems hard to solve if we think about it like counting graphs.
We should change the graphs to 0's and 1's. Then we just treat contiguous
0's as a part and count the parts. There are two ways to do the transition.
One is by splitting one square into fours parts (used in solution 1). 
The second is to depict a square by a 3 * 3 pixels (used in solution 2).
Obviously, the first one takes much less extra space than the second one.

1. @lee215, https://leetcode.com/problems/regions-cut-by-slashes/discuss/205680/JavaC%2B%2BPython-Split-4-parts-and-Union-Find 
	 ___________
	|\         /|   in case "/", top and left, right and bottom is contiguous
	|  \  3  /  |   in case "\\", top and right, left and bottom is contiguous
	| 0  \ /  2 |   in case " ", four parts are contiguous
	|    / \    |
	|  /  1  \  |
	|/_________\|

2. This problem become very much similar to 200. Number of Islands.

*/

// Solution 1: Union Find
class Solution { 
    int count, n;
    int[] f;
    
    public int regionsBySlashes(String[] grid) {
        n = grid.length;
        f = new int[n * n * 4];
        count = n * n * 4;
        for (int i = 0; i < n * n * 4; ++i) {
            f[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > 0) {
                    union(g(i - 1, j, 2), g(i, j, 0)); // last right and current left
                }
                if (j > 0) {
                    union(g(i , j - 1, 1), g(i , j, 3)); // last bottom and current top
                } 
                char c = grid[i].charAt(j);
                if (c != '/') { // '\\' or ' '
                    union(g(i , j, 0), g(i , j,  1)); // left and bottom
                    union(g(i , j, 2), g(i , j,  3)); // right and top
                }
                if (c != '\\') { // '/' or ' '
                    union(g(i , j, 0), g(i , j,  3)); // left and top
                    union(g(i , j, 2), g(i , j,  1)); // right and bottom
                }
            }
        }
        return count;
    }

    public int find(int x) {
        if (x != f[x]) {
            f[x] = find(f[x]);
        }
        return f[x];
    }
    
    public void union(int x, int y) {
        x = find(x); y = find(y);
        if (x != y) {
            f[x] = y;
            count--; // once two parts become one union, number of parts - 1
        }
    }
    
    public int g(int i, int j, int k) {
        return (i * n + j) * 4 + k;
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(n^2)
*/

/*************************************************************/
// Solution 2: DFS
class Solution { 
    int count = 0;
    
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        boolean[][] graph = new boolean[3 * n][3 * n];
        for (boolean[] part : graph) {
            Arrays.fill(part, true);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '/') {
                    graph[3 * i + 2][3 * j + 0] = false; // 0 0 1
                    graph[3 * i + 1][3 * j + 1] = false; // 0 1 0
                    graph[3 * i + 0][3 * j + 2] = false; // 1 0 0 
                }
                if (c == '\\') {
                    graph[3 * i + 0][3 * j + 0] = false; // 1 0 0
                    graph[3 * i + 1][3 * j + 1] = false; // 0 1 0
                    graph[3 * i + 2][3 * j + 2] = false; // 0 0 1 
                }
            }
        }
        
        for (int i = 0; i < 3 * n; i++) {     // [0, 3 * n)
            for (int j = 0; j < 3 * n; j++) { // [0, 3 * n)
                if (graph[i][j]) {
                    count++;
                    dfs(graph, i, j);
                }
            }
        }
        return count;
    }
    
    private void dfs(boolean[][] g, int x, int y) {
        if (x < 0 || x >= g.length || y < 0 || y >= g[0].length || !g[x][y]) {
            return;
        }

        g[x][y] = false;
        dfs(g, x - 1, y);
        dfs(g, x + 1, y);
        dfs(g, x, y - 1);
        dfs(g, x, y + 1);
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(n^2)
*/
