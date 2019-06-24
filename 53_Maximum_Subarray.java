/*

1. At each point, sum <= 0 means previous part is not contributing to the growth
of the sum. So we discard the history and start a new round of sum from the current
point. If sum > 0, the history is doing good to the final result, so we keep it.
No matter which way we calculate the sum, we check if we need to update the max.


*/

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum <= 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = (max > sum) ? max : sum;
        }
        return max;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/