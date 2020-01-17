/*

*/

class TicTacToe {
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;
    int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
        this.n = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */

    public int move(int row, int col, int player) {
        int p = player == 1 ? 1 : -1; // player
        int t = player == 1 ? n : -n; // target
        int n = rows.length;
        
        rows[row] += p;
        cols[col] += p;
        if (row == col) {
            diagonal += p;
        }
        if (row + col == n - 1) {
            antiDiagonal += p;
        }
        if (rows[row] == t || cols[col] == t || diagonal == t || antiDiagonal == t) {
            return player;
        }
        
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */


/*
Time complexity: O(1)
Space complexity: O(n), for rows and cols
*/

