/*
# Dynamic Programming
Silimar to 256 Paint House, but change from three colors to k colors.
Just as 256, we need the minimum cost after the previous house.
To avoid same color in a row, another second minimum value is also recorded.

*/
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        
        int n = costs.length; // houses
        int k = costs[0].length; // colors
        int cur = 0;
        
        int min = 0, minIdx = -1, secondMin = 0;
        
        for (int i = 0; i < n; i++) {
            int newMin = Integer.MAX_VALUE, newMinIdx = -1, newSecondMin = Integer.MAX_VALUE;
            
            for (int j = 0; j < k; j++) {
                int preMin = (j != minIdx) ? min : secondMin;
                cur = costs[i][j] + preMin;
                
                // update min, minIdx and secondMin
                if (cur <= newMin) {
                    newSecondMin = newMin;
                    newMin = cur;
                    newMinIdx = j;
                } else if (cur < newSecondMin) {
                    newSecondMin = cur;
                }
            }
            
            min = newMin;
            minIdx = newMinIdx;
            secondMin = newSecondMin;
        }
        
        return min;
    }
}



/*
Time complexity: O(nk)
Space complexity: O(1)
*/
