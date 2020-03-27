/*
No special algorithm. Very intuitive. But it takes a lot of carefulness.
*/

class Solution {
    public int[][] candyCrush(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        boolean crushAgain = false;
        
        // CRUSH: iterate over each grid to check if there are three same vertically or horizontally
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = Math.abs(board[i][j]); // a little trick here to use abs to deal with len > 3
                // horizontally check if len = 3
                if (cur != 0 && j + 2 < n && Math.abs(board[i][j + 1]) == cur && Math.abs(board[i][j + 2]) == cur) {
                    board[i][j] = board[i][j + 1] = board[i][j + 2] = -cur;
                    crushAgain = true;
                }
                
                // vertically check if len = 3
                if (cur != 0 && i + 2 < m && Math.abs(board[i + 1][j]) == cur && Math.abs(board[i + 2][j]) == cur) {
                    board[i][j] = board[i + 1][j] = board[i + 2][j] = -cur;
                    crushAgain = true;
                }
            }
        }
        
        // DROP: column by column, down to up
        for (int j = 0; j < n; j++) {
            int down = m - 1, up = m - 1;
            while (up >= 0) {
                if (board[up][j] >= 0) {
                    board[down--][j] = board[up][j];
                }
                up--;
            }
            while (down >= 0) {
                board[down--][j] = 0;
            }
        }
                            
        return crushAgain? candyCrush(board) : board;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/

