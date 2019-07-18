/*
# DFS
# BFS

1. The description is not so clear to understand. So we first change them into 
pseudo code language.
1.1	if (board[row][col] == 'M') {
		board[row][col] = 'X';
		return board;			
	}
	if (board[row][col] == 'E') {
		int count = numberOfAdjacentMines(...);
1.2		if (count > 0) {
			board[row][col] = (char)(count + '0');
			return board;
1.3		} else {
			board[row][col] = 'B';
			// reveal all its adjacent unrevealed squares recursively
		}
	}

2. BFS is the same logic as DFS but bringing in an extra queue to help.

*/

// Solution 1: DFS
class Solution {
    int[][] neighbors = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        int x = click[0];
        int y = click[1];
        // 1.1 'M' -> 'X'
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        // 1.2, 1.3 board[x][y] == 'E'
        dfs(board, x, y);
        
        return board;        
    }
    
    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length 
           || board[x][y] != 'E') {
            return;
        }
        int count = countAdjacentMines(board, x, y);
        // 1.2 has adjacent mines, change number and return
        if (count > 0) {
            board[x][y] = (char)(count + '0');
            return;
        } else { // 1.3 no adjacent mines, check neighbors
            board[x][y] = 'B';
            for (int[] neighbor : neighbors) {
                dfs(board, x + neighbor[0], y + neighbor[1]);
            }
        }
    }
    
    private int countAdjacentMines(char[][] board, int x, int y) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        for (int[] neighbor : neighbors) {
            int i = x + neighbor[0];
            int j = y + neighbor[1];
            if (i >= 0 && i < m && j >= 0 && j < n 
                &&(board[i][j] == 'M' || board[i][j] == 'X')) {
                count++;
            }
        }
        return count;
    }
}


/*
Time complexity: O(n), n is number of elements
Space complexity: O(n), for the call stack
*/

/***************************************************************/
// Solution 2: BFS
class Solution {    
    int[][] neighbors = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(click);
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            // 1.1 'M' -> 'X'
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
                return board;
            }
            // count adjacent mines
            int count = countAdjacentMines(board, x, y);
            if (count > 0) {
                board[x][y] = (char)(count + '0');
            } else { // 1.3 no adjacent mines
                board[x][y] = 'B';
                for (int[] neighbor : neighbors) {
                    int i = x + neighbor[0];
                    int j = y + neighbor[1];
                    if (i >= 0 && i < m && j >= 0 && j < n 
                        && board[i][j] == 'E') {
                        board[i][j] = 'B';
                        q.offer(new int[]{i, j});
                    }
                }
            }
        }
        return board;
    }
    
    private int countAdjacentMines(char[][] board, int x, int y) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        for (int[] neighbor : neighbors) {
            int i = x + neighbor[0];
            int j = y + neighbor[1];
            if (i >= 0 && i < m && j >= 0 && j < n 
                &&(board[i][j] == 'M' || board[i][j] == 'X')) {
                count++;
            }
        }
        return count;
    }
}

/*
Time complexity: O(n), n is number of elements
Space complexity: O(n), for the queue
*/