/*
# BFS

*/

class Solution {    
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        boolean[][] visited = new boolean[n][n];
        int[][] neighbors = {{-1, 0}, {-1, -1}, {-1, 1}, {1, 0}, {1, -1}, {1, 1}, {0, -1}, {0, 1}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        int steps = 1;
        while (!q.isEmpty()) {
            steps++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] neighbor : neighbors) {
                    int x = cur[0] + neighbor[0];
                    int y = cur[1] + neighbor[1];
                    if (x == n - 1 && y == n - 1) {
                        return steps;
                    }
                    if (x >= 0 && x < n && y >= 0 && y < n 
                        && !visited[x][y] && grid[x][y] == 0) {
                        visited[x][y] = true;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }      
        return -1;
    }
}

/*
Time complexity: O(n^2), n = grid.length
Space complexity: O(n^2), for the visited array
*/