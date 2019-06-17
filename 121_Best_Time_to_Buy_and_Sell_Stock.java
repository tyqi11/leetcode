/*
# Two pointers

1. We use two pointers recoring the buying point and selling point, and one
variable to remember the maximum profit that has appeared before. If the
current selling price is higher than buying price. We calculate the profit 
and compare with previous maximum. If the selling price is lower than the 
buying price, we change the buying price as the new one to increase the 
profit.

*/

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