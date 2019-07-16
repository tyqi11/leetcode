/*
# Dynamic Programming
# Dynamic Programming + Binary Search

1. We are allowed to solve the problem in O(n^2) time. So we used DP.

2. But we can find that when i increases, we are checking from 0 to i again
and again whick results in unnecessary checks. So we combine DP and binary
search to reduce time complexity. 
credit to@dietpepsi: https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
we use a tails array to store the smallest tail of all increasing subsequences
with length i + 1 at tails[i].

Each time we only do one of the two:
(1) if x is larger than all tails, append it, increase the size by 1
(2) if tails[i-1] < x <= tails[i], update tails[i]

*/

// Solution 1: dp
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int size = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            size = Math.max(size, dp[i]);
        }
        return size;
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(n)
*/

/*******************************************************/
// Solution 2: dp + binary search
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int left = 0;
            int right = size;
            while (left < right) {
                int mid = left + ((right - left) / 2);
                if (tails[mid] < num) {
                    left = mid + 1;
                } else { // tails[mid] >= m
                    right = mid;
                }
            }
            tails[left] = num;
            if (left == size) {
                size++;
            }
        }
        return size;
    }
}


/*
Time complexity: O(nlogn), n times of O(logn) binary search
Space complexity: O(n)
*/

