/*
# Two pointers
# Dynamic Programming

1. We use two pointers recording the buying point and selling point, and one
variable to remember the maximum profit that has appeared before. If the
current selling price is higher than buying price. We calculate the profit 
and compare with previous maximum. If the selling price is lower than the 
buying price, we change the buying price as the new one to increase the 
profit.

2. We use dynamic programming. A buy array (L[i], optimize to a variable) 
records the lowest price before today. And a profit array (optimize to a 
variable) records the maximum profit we can get until today. 
P[i] = Math.max(P[i - 1], prices[i] - L[i]). Return the profit at the last day.
(credit to: basketwangcoding)

*/

// Solution 1: Two pointers
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int i = 0; // i: buying point
        int max = 0;
        for (int j = 1; j < prices.length; j++) { // j: selling point
            if(prices[j] < prices[i]) {
                i = j;
            } else {
                int profit = prices[j] - prices[i];
                max = (profit > max) ? profit : max;
            }
        }
        return max;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/**************************************************************/

// Solution 2: DP

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            buy = Math.min(buy, prices[i]);
            profit = Math.max(profit, prices[i] - buy);
        }
        return profit;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

