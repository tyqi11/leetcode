/*
# Two pointers
# TreeMap
# PriorityQueue

# Dynamic Programming

1. Remember that a big *difficulty* does not mean a big *profit*. So each worker
should do a job that has has the biggest profit in a pool of jobs whose difficulties 
are less than his ability. 

2. All three solutions follow the same logic but use different data structures.

3. The DP solution is an interesting one. It sacrifices space complexity for time 
complexity. 


*/

// Solution 1: PriorityQueue
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Arrays.sort(worker); // ability, small to big
        Queue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (a[0] - b[0])); // [difficulty, profit], minHeap
        for (int i = 0; i < difficulty.length; i++) {
            pq.offer(new int[]{difficulty[i], profit[i]});
        } // difficulty, small to big
        
        int maxProfit = 0;
        int temp = 0;
        for (int ability : worker) {
            while (!pq.isEmpty() && pq.peek()[0] <= ability) {
                temp = Math.max(temp, pq.poll()[1]); // largest profit till now
            }
            maxProfit += temp;
        }
        
        return maxProfit;        
    }
}

/*
Time complexity: O(DlogD + WlogW), O(WlogW) for sorting worker array
								   O(DlogD) for constructing pq and jobs
Space complexity: O(D), for two queues
*/

/**********************************************************/
// Solution 2: DP
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[] dp = new int[100001]; // difficulty[i] <= 10^5
        for (int i = 0; i < difficulty.length; i++) {
            dp[difficulty[i]] = Math.max(dp[difficulty[i]], profit[i]);
        }
        
        for (int i = 1; i < 100001; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
        } // dp[i] is highest profit by doing work not more difficult than i
        
        int sum = 0;
        for (int ability : worker) {
            sum += dp[ability];
        }
        
        return sum;
    }
}

/*
Time complexity: O(100001 + W)
Space complexity: O(100001)
*/