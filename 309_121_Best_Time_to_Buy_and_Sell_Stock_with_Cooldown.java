/*
# Dynamic Programming
(thanks to @Hua Hua: https://www.youtube.com/watch?v=oL6mRyTn56M)

1. At day i, we can hold (1. hold previous, 2. empty in hand, and buy at day i), 
sell or rest (1. continue to rest, 2. rest because we sell yesterday). 
price is prices[i].
hold[i] = max(hold[i - 1], rest[i - 1] - price)
		  if we already have a stock in hand, we can choose to continue holding
		  if we do not have a stock, we can buy today and pay the stock price
sold[i] = hold[i - 1] + price
		  we can only sell the one we hold yesterday, and get the money back
rest[i] = max(rest[i - 1], sell[i - 1])
		  we can continue to rest or we are forced to rest

2. In the for loop, the three actions can be made at any order. We just need
to make sure the value we used is the one previous round (state[i - 1]). 
In the example, when we calculate hold and sold, the previous values can be 
use directly. But when we calculate rest, the previous sold is erased, so 
we use preSold to track this value before it is erased.

3. When return, it is ok to return the maximum of the three. But if we buy
at the end of the day, we pay the price but get no return. It must be worse
than just rest. So we return the maximum of rest and sold.

*/

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int sold = 0;
        int rest = 0;
        int hold = Integer.MIN_VALUE;
        for (int price : prices) {
        	int preSold = sold;
        	hold = Math.max(hold, rest - price);
        	sold = hold + price;
        	rest = Math.max(rest, preSold);
        }
        return Math.max(rest, sold);         
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/*********************************************************/
/*Follow Up: k days of cooldown*/ 