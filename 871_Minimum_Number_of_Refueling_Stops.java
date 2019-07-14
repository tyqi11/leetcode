/*
# Dynamic Programming
# PriorityQueue          *preferred*

1. When it comes to *furthest*, it is natural to think of dynamic programming. 
We use dp[t] to track the furthest distance we can get with *t* times of 
refuelling. For station i, if furthest distance dp[t] >= s[i][0], we can 
arrive at station i. And if we refuel, the furthest distance would be 
dp[t + 1] = Math.max(dp[t + 1], dp[t] + s[i][0]). The first k which makes 
dp[k] >= target is the right answer. 
For example: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
dp = [10, ?, ?, ?, ?], 
i = 0, t = 0, dp[0] >= 10, dp[1] = max(0, 10 + 70) = 80
dp = [10, 80, ?, ?, ?] 
i = 1, t = 1, dp[1] >= 20, dp[2] = max(0, 80 + 30) = 110
       t = 0, dp[0] < 20
dp = [10, 80, 110, ?, ?]
i = 2, t = 2, dp[2] >= 30, dp[3] = max(0, 110 + 30) = 140
       t = 1, dp[1] >= 30, dp[2] = max(110, 80 + 30) = 110
       t = 0, dp[0] < 30
...
i = x,                  means we are at station x now,
t = i, t >= 0,          at station x, there are at most x times of refuel, so t is not larger than x
dp[t] >= stations[i][0],means with t times of refuel, we can reach current station at stations[i][0]
dp[t + 1] = ...         the furthest t times can arrive, and refuel at current current station

2. Another way is using PriorityQueue. We record the gas in our tank *cur*, 
as long as cur < target, we continue searching. For all the stations our
*cur* amount of gas can reach, we add the how much we can refuel from these
stations. And choose the one with most gas to refuel.


*/

// Solution 1: Dynamic Programming
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (stations.length == 0) {
            return startFuel >= target ? 0 : -1;
        }
        int n = stations.length;
        long[] dp = new long[n + 1];
        dp[0] = startFuel;
        for (int i = 0; i < n; i++) {
            for (int t = i; t >= 0 && dp[t] >= stations[i][0]; t--) {
                dp[t + 1] = Math.max(dp[t + 1], dp[t] + stations[i][1]);
            }
        }
        for (int t = 0; t <= n; t++) {
            if (dp[t] >= target) {
                return t;
            }
        }
        return -1;
    }
}


/*
Time complexity: O(n^2), n = stations.length
Space complexity: O(n)
*/

/******************************************************************/
// Solution 2: PriorityQueue
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int i = 0; // station index
        int ans = 0;
        int cur = startFuel; // cur fuel we have in tank
        while (cur < target) {
            while (i < stations.length && cur >= stations[i][0]) {
                pq.offer(stations[i++][1]);
            }
            if (pq.isEmpty()) {
                return -1;
            }
            cur += pq.poll();
            ans++;
        }
        return ans;
    }
}

/*
Time complexity: O(nlogn), n = stations.length
Space complexity: O(n), for PriorityQueue
*/