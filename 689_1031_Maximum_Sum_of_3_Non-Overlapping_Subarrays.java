/*
I want to start with a similar solution with 1031 Maximum Sum of 2 Non-overlapping subarrays.
But failed. May want to try again when I'm free.
This solution is brilliant, came up with by: @zestypanda
https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108231/C%2B%2BJava-DP-with-explanation-O(n)

*/

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;  
        int max = 0;
        int[] sum = new int[n + 1];
        int[] posLeft = new int[n];
        int[] posRight = new int[n];
        int[] ans = new int[3];
        
        // prefix sum
        // I'm not used to setting prefixSum array as length n + 1
        // but in this case, it is more convinient, so I need to improve
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        // start index of the left-most interval
        int total = Integer.MIN_VALUE;
        for (int i = 0; i + k <= n; i++) {
            if (sum[i + k] - sum[i] > total) { // ">" 
                posLeft[i + k - 1] = i; // update at end index in posLeft
                total = sum[i + k] - sum[i];
            } else {
                posLeft[i + k - 1] = posLeft[i + k - 2];
            }
        }
        
        // start index of the right-most interval
        total = Integer.MIN_VALUE;
        for (int i = n - k; i >= 0; i--) {
            if (sum[i + k] - sum[i] >= total) { // ">="
                posRight[i] = i; // update at start index in posRight
                total = sum[i + k] - sum[i];
            } else {
                posRight[i] = posRight[i + 1]; // no new max
            }
        }
        
        // all possible middle interval
        for (int i = k; i <= n-2 * k; i++) {
            int l = posLeft[i - 1], r = posRight[i + k];
            //               left         +        middle         +      right
            total = (sum[l + k] - sum[l]) + (sum[i + k] - sum[i]) + (sum[r + k] - sum[r]);
            if (total > max) {
                max = total;
                ans = new int[]{l, i, r};
            }
        }
        
        return ans;        
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/