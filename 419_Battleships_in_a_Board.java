/*

1. We count only the first (the most top-left) cell of a battleship. Most
top-left means it meets three requirements:
1) board[i][j] == 'X'
2) if there is a left cell, board[i - 1][j] != 'X'
3) if there is a upper cell, board[i][j - 1] != 'X'
This is how the codes works.

*/

class Solution {
    public int countBattleships(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int m = board.length;
        int n = board[0].length;
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' 
                    && (i == 0 || board[i - 1][j] != 'X')
                    && (j == 0 || board[i][j - 1] != 'X')) {
                    count++;
                }
            }
        }
        return count;
    }
}


/*
Time complexity: O(n^2), one-pass solution
Space complexity: O(1)
*/