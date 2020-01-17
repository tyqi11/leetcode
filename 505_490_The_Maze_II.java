/*
# BFS (much much much much faster)
# DFS (very slow)
*/
            
// BFS
class Solution {
    int[][] dirs = new int[][]{ {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0 ) {
            return -1;
        }
        
        int[][] visited = new int[maze.length][maze[0].length];
        for (int[] v : visited) {
            Arrays.fill(v, Integer.MAX_VALUE);
        }
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] d : dirs) {
                int x = cur[0];
                int y = cur[1];
                int len = visited[x][y];
                
                while (isValid(x + d[0], y + d[1], maze)) {
                    x += d[0];
                    y += d[1];
                    len++;    
                }
                
                if (len > visited[destination[0]][destination[1]]) {
                    continue;
                }
                if (len < visited[x][y]) {
                    visited[x][y] = len;
                    q.offer(new int[]{x, y});
                }
            }
        }
        
        return visited[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : visited[destination[0]][destination[1]]; 
        
    }
    
    private boolean isValid(int x, int y, int[][] maze) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
    
}

/********************************************************************/
// DFS
class Solution {
    int[][] dirs = new int[][]{ {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
    int min = Integer.MAX_VALUE;
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] visited = new int[maze.length][maze[0].length];
        dfs(maze, start[0], start[1], destination[0], destination[1], visited, 0);        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private void dfs(int[][] maze, int sx, int sy, int dx, int dy, int[][] visited, int spaces) {
        if (sx == dx && sy == dy) {
            min = Math.min(min, spaces);
            return;
        }
        
        if (visited[sx][sy] > 0 && visited[sx][sy] <= spaces) {
            return;
        }
        
        visited[sx][sy] = spaces;
        for (int[] d : dirs) {
            int row = sx;
            int col = sy;
            int s = spaces;
            
            while (isValid(maze, row + d[0], col + d[1])) {
                row += d[0];
                col += d[1];
                s++;
            }
            
            if (s == spaces) {
                continue; // stay at the same spot
            }
            dfs(maze, row, col, dx, dy, visited, s);
        }
    }
    
    private boolean isValid(int[][] maze, int x, int y) {
        return (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0);
    }
}

/*
Time complexity: O(n + nlogn)
Space complexity: O(nlogk)
*/