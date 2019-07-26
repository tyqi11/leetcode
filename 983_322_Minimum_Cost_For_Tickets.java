/*
# Dynamic Programming


Very similar to 322. Coin Change.

*/

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int last = days[days.length - 1]; // last day of travel
        int[] dp = new int[last + 1];
        boolean[] travel = new boolean[last + 1];
        for (int day : days) {
            travel[day] = true;
        } // mark all the travel days
        int min1, min7, min30;
        for (int i = 1; i <= last; i++) {
            if (travel[i]) {
                min1 = dp[i - 1] + costs[0];
                min7 = (i < 7 ? 0 : dp[i - 7]) + costs[1];
                min30 = (i < 30 ? 0 : dp[i - 30]) + costs[2];
                dp[i] = Math.min(min1, Math.min(min7, min30));
            } else { // travel[i] = false
                dp[i] = dp[i - 1];
            }
        }
        return dp[last];            
    }
}

/*
Time complexity: O(d), d is number of days
Space complexity: O(d)
*/