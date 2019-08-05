/*

Similar to 363. Max Sum of Rectangle No Larger Than K. 
This problem is  Number of Rectangle    Equals      K.

*/

class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        } 
        
        int res = 0;
        for (int i = 0; i < n; i++) { // starting column
            for (int j = i; j < n; j++) { // ending column
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int cur = 0;
                for (int k = 0; k < m; k++) { // for each row
                    cur += matrix[k][j] - (i == 0 ? 0 : matrix[k][i - 1]);
                    res += map.getOrDefault(cur - target, 0);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;   
    }
}

/*
Time complexity: O(m * n * n)
Space complexity: O(m * n), worst case for map
*/