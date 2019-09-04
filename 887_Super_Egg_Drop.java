/*
# Dynamic Programming
@lee215, https://leetcode.com/problems/super-egg-drop/discuss/158974/C%2B%2BJavaPython-2D-and-1D-DP-O(KlogN)

*/

class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[N + 1][K + 1]; 
        int m = 0; // moves
        while (dp[m][K] < N) {
            m++;
            for (int e = 1; e <= K; e++) { // eggs
                dp[m][e] = dp[m - 1][e - 1] + dp[m - 1][e] + 1;
            }
        }
        return m;
    }
}

// optimized to 1D array
class Solution {
	public int superEggDrop(int K, int N) {
        int[] dp = new int[K + 1];
        int m = 0;
        for (m = 0; dp[K] < N; ++m) {
        	for (int k = K; k > 0; --k) {
        		dp[k] += dp[k - 1] + 1;
        	}
        }
        return m;
    }
}
/*
Time complexity: O(KlogN), ???
Space complexity: O(NK)
*/