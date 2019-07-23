/*
# DFS

This problem is easier than 51. N-Queens, as it only requires us to count results.


*/

class Solution {
    int count = 0;
    
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // diagonal \
        boolean[] diag2 = new boolean[2 * n - 1]; // diagonal /
        dfs(0, n, cols, diag1, diag2);
        return count;
    }
    
    private void dfs(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            count++;
        }
        
        for (int col = 0; col < n; col++) {
            if (cols[col] || diag1[row - col + n - 1] || diag2[row + col]) {
                continue;
            }
            // put a queen on board
            cols[col] = true;
            diag1[row - col + n - 1] = true;
            diag2[row + col] = true;

            dfs(row + 1, n, cols, diag1, diag2);
            
            // remove the queen off board
            cols[col] = false;
            diag1[row + n - col - 1] = false;
            diag2[row + col] = false;
        }
    }
    
}

/*
Time complexity: O(n!), n! times of searching
Space complexity: O(n), for the boolean arrays
*/