/*
# DFS

1. A typical DFS problem. In the exist() method, iterate over each char to start
a DFS search. Once the current char matches, visit all its neighbors. Keep a 
visited array to avoid using one char more than once.

*/

class Solution {
    int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        } else if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;        
    }
    
    private boolean dfs(char[][] board, String word, boolean[][] visited, int x, int y, int idx) {
        if (idx == word.length()) {
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length 
           || visited[x][y]
           || board[x][y] != word.charAt(idx)) {
            return false;
        }
        
        visited[x][y] = true;
        for (int[] neighbor : neighbors) {
            if (dfs(board, word, visited, x + neighbor[0], y + neighbor[1], idx + 1)) {
                return true;
            }
        }
        visited[x][y] = false; // though the current char matches, no answer, change back to unvisited
        return false;
    }
}

/*
Time complexity: O(m * n), m * n is the number of elements
Space complexity: O(m * n), the visited array and call stack
*/