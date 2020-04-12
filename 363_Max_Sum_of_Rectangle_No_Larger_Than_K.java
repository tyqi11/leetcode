/*
@Tushar Roy - Coding Made Simple
https://www.youtube.com/watch?time_continue=19&v=yCQN096CwWM&feature=emb_logo

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
/*IMP*/         set.add(0); // when cur - k <= 0, cur itself is the max sum
                int cur = 0;
                
                // we want to find a num with: cur - num <= k
                // so we check if there is a num that: num >= cur - k
                // the num should be as small as possible, as cur is set
                // if there is this num, there is a cur - num <= k
                // this cur - num is a possible result
 /*IMP*/        for (int sum : preSum) {
 /*IMP*/            cur += sum;
 /*IMP*/            Integer num = set.ceiling(cur - k); // smallest num >= cur - k
 /*IMP*/            if (num != null) {
 /*IMP*/               max = Math.max(max, cur - num);
                    }
                    set.add(cur);
                }
            }
        }
        
        return max;
    }
}

/*
Time complexity: O(n * n * mlogm), m is number of rows, n is number of columns,
Space complexity: O(m)
(Assuming n = min(m, n), m = max(m, n))
*/