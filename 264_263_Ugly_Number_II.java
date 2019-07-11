/*
# Dynamic Programming

1. To find the n-th ugly number, we use dynamic programming to store previous results,
and add one more factor each time. 

2. two, three, five are the indices of the least number which we can multiply another
2/3/5. For example, when two = 5, it means that last time we get a result through
multiply 2 at dp[4], this time we can try if 2 * dp[5] gives us a small enough number.
And the same idea for *three* and *five*. 

3. Each time, we need to compare the three potential results and store the smallest one.
Also, we update the three pointers. 

*/

class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return -1;
        }
        int[] dp = new int[n];
        int two = 0, three = 0, five = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int temp = Math.min(dp[two] * 2, dp[three] * 3);
            dp[i] = Math.min(temp, dp[five] * 5);
            if (dp[i] == dp[two] * 2) {
                two++;
            } 
            if (dp[i] == dp[three] * 3) {
                three++;
            }
            if (dp[i] == dp[five] * 5) {
                five++;
            }
        }
        return dp[n - 1];
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/