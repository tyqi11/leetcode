/*
# Two pointers

Comparing to 121, we are allowed to >1 transactions. So we need to record
all the price increase intervals. We use i to record buying point and j for 
selling point. 

If selling >= buying, we conclude this transaction and set the new buying price
as the previous selling point. This way, if the price rises later, we will not 
miss it. (As in [1, 2, 3, 4]. After selling at 2, we buy at 2 instead of 3. 
This is not true in reality because we should consider the transaction fee.)
If selling < buying, we give up the previous buying point and choose to buy at
the current time with lower price.

*/

class Solution {
    public int maxProfit(int[] prices) {
        int sum = 0;
        int i = 0, j = 0;
        while (j < prices.length) {
            if (prices[j] >= prices[i]) {
                sum += prices[j] - prices[i];
            }  
            i = j;
            j++;
        }
        
        return sum;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/