class Solution {
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; 
        
    public int maximumMinimumPath(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> b[2] - a[2]); // max-heap
        
        pq.offer(new int[]{0, 0, A[0][0]});
        visited[0][0] = true;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int row = cur[0];
            int col = cur[1];
            
            // reach the destination
            if (row == m - 1 && col == n - 1) {
                return cur[2];
            }
            
            for (int[] dir : directions) {
                int x = row + dir[0];
                int y = col + dir[1];
                
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                    continue;
                } else {
                    pq.offer(new int[]{x, y, Math.min(cur[2], A[x][y])});
                    visited[x][y] = true;
                }                
            }
        }
        return -1;
    }
}