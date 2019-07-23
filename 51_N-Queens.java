/*
# DFS

1. The key part in solving this problem is to know how to decide if the current
place is valid for a new queue.
1.1 To avoid being in the same row, we set one queue in each row and move to the
next one.
1.2 To avoid being in the same column, we record which columns are occupied. And
put the queen to another one.
1.3 To avoid being in the same diagonal (\), we use diag1 to track. In a grid of n * n,
there are 2 * n + 1 diagonals.
		 d c b a 0   n = 4, number of diagonals: 7 = 2 * 4 - 1
	   4 e d c b 1   element (x, y) lies on diagonal x - y + n - 1
	   5 f e d c 2   for example: c at (1, 2), the diagonal is 1 - 2 + 4 - 1 = 2
	   6 g f e d 3
1.3 To avoid being in the same back-diagonal (/), we use diag2 to track. In a grid of n * n,
there are 2 * n + 1 back-diagonals.
	   0 a b c d     n = 4, number of diagonals: 7 = 2 * 4 - 1
	   1 b c d e 4   element (x, y) lies on diagonal x + y 
	   2 c d e f 5   for example: d at (1, 2), the diagonal is 1 + 2 = 3
	   3 d e f g 6

2. Iterating rows, we find a place for the current queen and move to the next row.
Once row == n, we finished one searching and add current result to the final result.
The we go back one step and iterating again.

reference: https://github.com/RodneyShag/LeetCode_solutions/blob/master/Solutions/N-Queens.md
*/

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                board[i][j] = '.';
            }
        }

        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // diagonal \
        boolean[] diag2 = new boolean[2 * n - 1]; // diagonal /
        dfs(board, 0, n, res, cols, diag1, diag2);
        return res;
    }
    
    private void dfs(char[][] board, int row, int n, List<List<String>> res, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            List<String> curRes = new ArrayList<>();
            for (char[] line : board) {
                curRes.add(String.valueOf(line));
            }
            res.add(curRes);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols[col] || diag1[row - col + n - 1] || diag2[row + col]) {
                continue;
            }
            // put a queen on board
            cols[col] = true;
            diag1[row - col + n - 1] = true;
            diag2[row + col] = true;
            board[row][col] = 'Q';

            dfs(board, row + 1, n, res, cols, diag1, diag2);
            
            // remove the queen off board
            cols[col] = false;
            diag1[row + n - col - 1] = false;
            diag2[row + col] = false;
            board[row][col] = '.';
        }
    }
    
}

/*
Time complexity: O(n^2 & n!), n! solutions to generate, and O(n^2) to copy
Space complexity: O(n^2 * n!), n! solutions and O(n^2) each time to store

In fact, there are only 92 solutions when n = 8, which is much less than 8! = 40320.
So we can replace n! by a parameter representing number of results.
*/
