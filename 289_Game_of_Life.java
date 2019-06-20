/*
# Bit operation

1. This problem is hard as we need to record both the current and future state
of an element at the same time. The solution is to use bits. We use the first 
bit to record current states, and the second bit to record the future states. 
So: 00: dead <- dead, 01: dead <- live, 10: live <- dead, and 11: live <- live. 
In the input: there are 00 and 01. We can interpret as all the cells will die
in the future. And we need to know which cells will live.

2. We use countLiveNeighbors() to get the live neighbors of each cell. To get
the current state of a neighbor, we do board[i][j] & 1 to get the first bit.
Then, we figure out cells that will live after change. And the rest will die.
In the passage, there are two circumstances when cells will live.

3. Finally, we go through each cell and remove the number to 1 bit right. We 
get the current state.

*/

class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = countLiveNeighbors(board, i, j);
                // die -> live: 3 live neighbors
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // 0000 -> 0010
                }
                // live -> live: 2 or 3 live neighbors
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // 0001 -> 0011
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }
    
    private int countLiveNeighbors(int[][] board, int i, int j) {
        int count = 0;
        for (int x = Math.max(0, i - 1); x <= Math.min(board.length - 1, i + 1); x++) {
            for (int y = Math.max(0, j - 1); y <= Math.min(board[0].length - 1, j + 1); y++) {
                count += (board[x][y] & 1);
            }
        }
        count -= (board[i][j] & 1);
        return count;
    }
}

/*
Time complexity: O(m * n)
Space complexity: O(1)
*/