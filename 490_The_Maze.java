/*
# BFS
# DFS

In this problem, DFS is a faster than BFS. But in 505. The Maze II, BFS is much faster.
*/

// BFS
class Solution {
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1]});
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int[] d : dirs) {
                int row = cur[0];
                int col = cur[1];
            
                while (isValid(row + d[0], col + d[1], maze)) {
                    row += d[0];
                    col += d[1];
                }
                
                if (visited[row][col]) {
                    continue;
                }
                
                if (row == destination[0] && col == destination[1]) {
                    return true;
                }
                visited[row][col] = true;
                q.offer(new int[]{row, col});
            }
        }

        return false;
    }
    
    private boolean isValid(int x, int y, int[][] maze) {
        return (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0);
    }
}


/*************************************************************************/
// DFS
class Solution {
    int[][] dirs = new int[][]{ {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }
    
    private boolean dfs(int[][] maze, int[] p, int[] dest, boolean[][] visited) {
        if (p[0] == dest[0] && p[1] == dest[1]) {
            return true;
        }
            
        if (visited[p[0]][p[1]]) {
            return false;
        }
        
        visited[p[0]][p[1]] = true;
        for (int[] d : dirs) {
            int row = p[0];
            int col = p[1];
            
            while (isValid(maze, row + d[0], col + d[1])) {
                row += d[0];
                col += d[1];
            }
            

            if (dfs(maze, new int[]{row, col}, dest, visited)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean isValid(int[][] maze, int x, int y) {
        return (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0);
    }
}

/*
Time complexity: O(n), n is the number of empty spaces 
Space complexity: O(n)
*/