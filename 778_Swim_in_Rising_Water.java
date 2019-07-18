/*
# PriorityQueue
# Dynamic Programming + DFS

1. As we always want to know the smallest depth around current place. We use
PriorityQueue to sort in time. Everytime we are at one spot, we iterate over
its four neighbors and put into queue their elevations. Next time, we choose
the lowest elevation and continue searching the next lowest one. Elevation is
the same meaning as time in this problem. 

2. Another way to solve the problem is similar to 875/1101: using binary search
to get one possible solution, and use a sub-function to judge if it works. That
is much faster as it has the same O(n^2*logn) time complexity, but avoids
using advanced data structures.

*/

// Solution 1: PriorityQueue
class Solution {    
    public int swimInWater(int[][] grid) {
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        pq.offer(new int[]{0, 0, grid[0][0]});
        int n = grid.length;
        int[][] neighbors = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[n][n];
        int time = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            time = Math.max(time, cur[2]);
            if (cur[0] == n - 1 && cur[1] == n - 1) {
                return time;
            }
            
            for (int[] neighbor : neighbors) {
                int x = cur[0] + neighbor[0];
                int y = cur[1] + neighbor[1];
                if (x >= 0 && x <= n - 1 && y >= 0 && y <= n - 1 && !visited[x][y]) {
                    visited[x][y] = true;
                    pq.offer(new int[]{x, y, grid[x][y]});
                }
            }
        }
        return -1;        
    }
}

/*
Time complexity: O(n*logn), n elements, n times of O(logn) pq.offer()
Space complexity: O(n), for the visited array
*/

/************************************************************/
// Solution 2: binary search + DFS
class Solution { 
    int[][] neighbors = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  
    
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int left = grid[0][0];
        int right = n * n - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            boolean[][] visited = new boolean[n][n];
            if (canSwim(grid, mid, 0, 0, visited)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    private boolean canSwim(int[][] grid, int time, int x, int y, boolean[][] visited) {
        int n = grid.length;
        visited[x][y] = true;
        if (x == n - 1 && y == n - 1) {
            return true;
        }
        for (int[] neighbor : neighbors) {
            int curX = x + neighbor[0];
            int curY = y + neighbor[1];
            if (curX >= 0 && curX <= n - 1 && curY >= 0 && curY <= n - 1 &&
               !visited[curX][curY] && grid[curX][curY] <= time) {
                if (canSwim(grid, time, curX, curY, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}

/*
Time complexity: O(n*logn), n times of O(logn) binary search
Space complexity: O(n), for the visited array
*/