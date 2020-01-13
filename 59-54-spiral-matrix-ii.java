/*
split one circle into four parts: first whole row, rest of the right-most column,
last whole row, and rest of the left-most column.

after each round, increase the starting point to its down right corner by 1,
and decrease the length of step by 2.

*/

class Solution {
    public int[][] generateMatrix(int n) {
        int num = 1;
        int len = n;
        int s = 0; // starting index (left most and up most)
        int[][] res = new int[n][n];
        while (num <= n * n) {
            int cL = s, cH = s + len - 1, rL = s, rH = s + len - 1;
            for (int c = cL; c <= cH && num <= n * n; c++) {
                res[rL][c] = num++;
            }
            for (int r = rL + 1; r < rH && num <= n * n; r++) {
                res[r][cH] = num++;
            }
            for (int c = cH; c >= cL && num <= n * n; c--) {
                res[rH][c] = num++;
            }
            for (int r = rH - 1; r > rL && num <= n * n; r--) {
                res[r][cL] = num++;
            }
            s++;
            len -= 2;
        }
        
        return res;
    }
}


/*
Time complexity: O(m * n)
Space complexity: O(1)
*/