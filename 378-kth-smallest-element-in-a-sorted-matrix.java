/*
# PriorityQueue (similar to merge k sorted list)
# Binary Search

*/

// PriorityQueue
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for (int i = 0, j = 0; j < matrix[0].length; j++) {
            pq.offer(new int[]{matrix[i][j], i, j});
        }
        
        int ans = matrix[0][0];
        while (k > 0) {
            int[] cur = pq.poll();
            ans = cur[0];
            if (cur[1] + 1 < matrix.length) {
                int x = cur[1] + 1;
                int y = cur[2];
                pq.offer(new int[]{matrix[x][y], x, y});
            }
            k--;
        }
        
        return ans;
    }
}

/*
Time complexity: O(klogk), k times of O(logk) of pq
Space complexity: O(n), for the pq, n = matrix[0].lengths
*/

/*********************************************************/
// Binary Search
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0];
        int hi = matrix[m - 1][n - 1];
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0;
            for (int i = 0, j = n - 1; i < m; i++) {
                while (j >= 0 && matrix[i][j] > mid) {
                    j--;
                }
                count += j + 1;
            }
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
/* if count == k, maybe the target kth smallest is 25 but mid is 28, which
is not a number in the matrix. we still need to decrease the upper bound until
the upper bound cannot be decreased any more. */
/*
Time complexity: O(log(mn))
Space complexity: O(1)
*/

