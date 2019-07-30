/*
# Dynamic Programming

1. We use dp[i][j] to represent how many more scores the first-pick-player can
get than the second-pick-player. Obviously, if there is only one number nums[i],
dp[i][i] = nums[i]. 

2. But if there are more numbers, for numbers between [i, j], there are two 
choices for the first player.
2.1 If he picks nums[i], he will immediately get nums[i]. But his opponent will 
be the first-pick-player between [i + 1, j], so he will lose dp[i + 1][j]. 
2.2 If he picks nums[j], he get nums[j] and loses dp[i][j - 1].

3. So the max of nums[i] - dp[i + 1][j] and nums[j] - dp[i][j - 1] will be 
the choice to make. 

4. We can further optimize the algorithm by using a 1D array to replace the 2D
one, decreasing the space complexity from O(n^2) to O(n).

*/

class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = nums[i];
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(n^2)
*/

// Optimized to O(n) space complexity
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = nums[i];
            for (int j = i + 1; j < n; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[n - 1] >= 0;
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(n)
*/