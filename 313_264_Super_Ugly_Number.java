/*

1. First of all, we can use the exact same idea of 264. Ugly Number II to generate 
Solution 1.

2. In Solution 1, we iterate over the primes array twice. Now we optimize this method.
Before, we update count using dp[i] after generating dp[i]. Now, we update count
using dp[i - 1] in the previous round and then generating dp[i]. In this way, we 
iterate from 2 * k * n times to k * n times.

3. There is another way using PriorityQueue. It is neither space efficient nor time
efficient. Just providing a new way of thinking. 
For example, n = 12, primes = [2, 7, 13, 19], dp = [1, 2, 4]
In the PriorityQueue:   [2, 0, 2], [7, 0, 7], [13, 0, 13], [19, 0, 19]
dp[1] = 2, cur = [2, 0, 2], cur[0] = 2 * dp[0] = 2, cur[1] = 1 --> [2, 1, 2]
           cur = [2, 1, 2], cur[0] = 2 * dp[1] = 4, cur[1] = 2 --> [4, 2, 2]
dp[2] = 4, cur = [4, 2, 2], cur[0] = 2 * dp[2] = 8, cur[1] = 3 --> [8, 3, 2]
dp[3] = 7, cur = [7, 0, 7], cur[0] = 7 * dp[0] = 7, cur[1] = 1 --> [7, 1, 7]
           cur = [7, 1, 7], cur[0] = 7 * dp[1] = 14, cur[1] = 2 --> [14, 2, 7]
...

*/

// Solution 1: Ugly Number II method
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n <= 0) {
            return -1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int[] count = new int[primes.length];
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                dp[i] = Math.min(dp[i], primes[j] * dp[count[j]]);
            }
            for (int j = 0; j < primes.length; j++) {
                if (dp[i] == primes[j] * dp[count[j]]) {
                    count[j]++;
                }
            }            
        }
        return dp[n - 1];
    }
}

/*
Time complexity: O(2kn), k is the length of primes
Space complexity: O(k + n)
*/

/**********************************************************************/
// Solution 2: optimized Ugly Number II method
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n <= 0) {
            return -1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int[] count = new int[primes.length];
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                if (dp[i - 1] == primes[j] * dp[count[j]]) {  //
                    count[j]++;                               // change order
                }                                             //
                dp[i] = Math.min(dp[i], primes[j] * dp[count[j]]);
            }            
        }
        return dp[n - 1];
    }
}

/*
Time complexity: O(kn), k is the length of primes
Space complexity: O(k + n)
*/

/*************************************************************/
// Solution 3: PriorityQueue
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n <= 0) {
            return -1;
        }
        Queue<int[]> pq = new PriorityQueue(new Comparator<int[]>() {
           @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < primes.length; i++) {
            pq.offer(new int[]{primes[i], 0, primes[i]});
        } // current value, current index, original prime value    
        
        int[] dp = new int[n];
        dp[0] = 1;
        
        for (int i = 1; i < n; i++) {
            int next = pq.peek()[0];
            dp[i] = next;
            while (pq.peek()[0] == next) {
                int[] cur = pq.poll();
                cur[0] = cur[2] * dp[cur[1]];
                cur[1]++;
                pq.offer(cur);
            }
        }
        
        return dp[n - 1];
    }
}

/*
Time complexity: O(nklogk)
Space complexity: O(n + k)
*/