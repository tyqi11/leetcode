/*
# Two pointers

1. Comparing to 121, we are allowed to >1 transactions. So we need to record
all the low prices and buy, and wait until the highest price to sell. We 
use i to record buying point and j for selling point. Difference is that we 
do not calculate every profit when prices[j] > prices[i]. Take [1, 2, 3, 4, 1] 
as an example. It is not wise to buy at 1 and sell at 2, and buy at 3 and sell
at 4. As the price is rising, we wait until it starts to fall. So we buy at 1
and sell until 4. After this, we set buying point to the price after selling,
and new selling point after new buying point.

*/

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int i = 0;
        int total = 0;
        for (int j = 1; j < prices.length; j++) {
            if (prices[j] < prices[i]) {
                i = j;
            } else { // valid potential selling point
                while (j < prices.length - 1 && prices[j] < prices[j + 1]) {
                    j++;
                } // if the price is rising, not sell
                total += prices[j++] - prices[i];
                i = j;
            }
        }
        return total;
        
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/