/*
# Dynamic Programming

1. Considering the fact that we used a general way to solve previous III problem, we can use
the same way to solve this too.

2. As the k in this problem can be really big, we use a quick way to avoid TLE/MLE. If k > n/2,
it means that we can do buy/sell at each price. So as long as one price is higher than the 
previous one, we do a transaction and add the profit to the sum.

*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        
        if (k >= n / 2) {
            int max = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    max += prices[i] - prices[i - 1];
                }
            }
            return max;
        }
        
        int[][] profits = new int[k + 1][n]; // 3 = k + 1
        
        for (int i = 1; i <= k; i++) { // i <= k 
            int maxDiff = -prices[0]; // T[0][0] - prices[0], i = 0, m = 0
            for (int j = 1; j < n; j++) {
                profits[i][j] = Math.max(profits[i][j - 1], maxDiff + prices[j]);
                maxDiff = Math.max(maxDiff, profits[i - 1][j] - prices[j]);
            }
        }
        return profits[k][n - 1];
    }
}

/*
Time complexity: O(k*n)
Space complexity: O((k+1)*n)
*/