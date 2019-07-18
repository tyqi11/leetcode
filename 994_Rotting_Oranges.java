/*
# BFS

1. First we iterate over all the oranges, put all rotten oranges into the queue,
and count fresh oranges.

2. Every minute, the existing rotten oranges make all its neighbors rotten.
And put the new rotten oranges into the queue for the next minute. When there
are no fresh oranges, return time. If fresh is not 0 until all the rotten
ones make their neighbors rotten, return -1.
For example, the most bottom-left one will never get rotten as it is isolated
from other rotten oranges. Return -1 if this happens.
		2, 1, 1     2, 2, 1    2, 2, 2    2, 2, 2    2, 2, 2
		0, 1, 1 ->  0, 1, 1 -> 0, 2, 1 -> 0, 2, 2 -> 0, 2, 2
		1, 0, 1     1, 0, 1    1, 0, 1    1, 0, 1    1, 0, 2


*/

class Solution {
    int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                } else if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        
        // no fresh at minute 0, return 0
        if (fresh == 0) {
            return 0;
        }
        
        int time = 0;
        while (!q.isEmpty()) {
            time++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] neighbor : neighbors) {
                    int x = cur[0] + neighbor[0];
                    int y = cur[1] + neighbor[1];
                    if (x >= 0 && x < m && y >= 0 && y < n 
                       && grid[x][y] == 1) {
                        fresh--;
                        grid[x][y] = 2;
                        q.offer(new int[]{x, y});
                    }
                }
            }    
            if (fresh == 0) {
                return time;
            }
        }                                //      vvv   
        return -1;  // return fresh == 0 ? (time - 1) : -1
    }
}

/*
Time complexity: O(n), number of oranges
Space complexity: O(n), for the queue
*/