/*
# Dynamic Programming
You must know this method to solve Stock IV.
(credit to Tushar Roy: https://www.youtube.com/watch?v=oDhu5uGq_ic)

This method can be generalized to k max transactions.

idx  0  1  2  3  4  5  6  7
     2  5  7  1  4  3  1  3  
0    0  0  0  0  0  0  0  0
1    0  3  5  5  5  5  5  5  
2    0  3  5  5  8  8  8  8
3    0  3  5  5  8  8  8  10

T[i][j] = max{T[i][j - 1], T[i - 1][m] + prices[j] - prices[m], m = 0, 1, ..., j -1}
T[i][j] = max{T[i][j - 1], prices[j] + maxDiff}
e.g. i = 2, j = 3
m = 0, T[1][0] + prices[j] - prices[0]
m = 1, T[1][1] + prices[j] - prices[1]
m = 2, T[1][2] + prices[j] - prices[2]
      --------            ------------ -> T[i- 1][m] - prices[m] -> maxDiff
m = 3, T[1][3] + prices[j] - prices[3]  (m = j - 1)
       => maxDiff = Math.max(maxDiff, T[i][j - 1] - prices[j - 1])
In the code, we calculate the first maxDiff outside inner loop. So when we calculate 
maxDiff in the inner loop, it is prepared for the j + 1, so we need to change to 
maxDiff = Math.max(maxDiff, T[i][j] - prices[j]).

*/

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[][] profits = new int[3][prices.length]; // 3 = k + 1
        
        for (int i = 1; i <= 2; i++) { // i <= k 
            int maxDiff = -prices[0]; // T[0][0] - prices[0], i = 0, m = 0
            for (int j = 1; j < prices.length; j++) {
                profits[i][j] = Math.max(profits[i][j - 1], maxDiff + prices[j]);
                maxDiff = Math.max(maxDiff, profits[i - 1][j] - prices[j]);
            }
        }
        return profits[2][prices.length - 1];
    }
}

/*
Time complexity: O(k * n) -> O(n)
Space complexity: O((k + 1) * n) -> O(n)
*/

/******************************************************************/
/*
# O(n) solution
credit to @weijiac, @xwa130: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39611/Is-it-Best-Solution-with-O(n)-O(1).

The buying price is the cost, so we save it as negative value. The bigger the cost
is (-1 > -2), in reality, the cheaper the cost is (1 < 2).

*/
class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0; // money in our hand at the beginning
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0; // money in our hand at the beginning
        
        for (int p : prices) {
            buy1 = Math.max(buy1, -p); // after a buy, how much money we have
            sell1 = Math.max(sell1, buy1 + p); // after a sell
            buy2 = Math.max(buy2, sell1 - p); // after a second buy
            sell2 = Math.max(sell2, buy2 + p); // after a second sell
        }
        
        return sell2;
    }
}