/*

1. How to get the largest number that is no more than k? set.ceiling()/set.floor()
vs. higher() / lower() (not including equal)
*/

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;       
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {	// starting column
            int[] preSum = new int[m];	// for every new start, initialize preSum
            for (int j = i; j < n; j++) { 	// ending column
                for (int r = 0; r < m; r++) {
                    preSum[r] += matrix[r][j];
                }
                // use TreeSet to get maxSum <= k in O(logn) time
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0); // when cur - k <= 0, cur itself is the max sum
                int cur = 0;
                
                for (int sum : preSum) {
                    cur += sum;
                    Integer num = set.ceiling(cur - k); // smallest num >= cur - k
                    if (num != null) {
                        max = Math.max(max, cur - num);
                    }
                    set.add(cur);
                }
            }
        }
        
        return max;
    }
}

/*
Time complexity: O(m * n * n * logm), m is number of rows, n is number of columns,
									  good when rows is much more than columns, if 
									  opposite, iterate from rows. 
Space complexity: O(m)
*/