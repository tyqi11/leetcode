/*
# Dynamic Programming

1. dp[i] is the fewest number of coins that can make up i amount of money. We initialize all elements in the array as amount + 1, an impossible number (we will do Math.min() later, so this should be a big and impossible number). For dp[0], when the money is 0, we need 0 coins. So dp[0] = 0;

2. Now, we iterate each coin. 
2.1 If sum < coin, this coin cannot make up this sum. We skip and do not change dp[i]
2.2 If sum >= coin, we return the min of 
1) previous result dp[sum], 
2) we can get sum from (sum - coin) and current coin, so dp[sum - coin] + 1, the 1 is one current coin

3. When we reach dp[amount], if its value is greater than amount, it was the default value we set. Return -1. Else return dp[amount].

*/

class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int sum = 1; sum <= amount; sum++) {
                if (sum >= coin) {
                    dp[sum] = Math.min(dp[sum], dp[sum - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

/*
Time complexity: O(n * amount), n is number of coins 
Space complexity: O(amount)
*/