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
        
        int max = nums[0];
        int sum = 0;
        int i = 0;
        while (i < nums.length) {
            sum += nums[i++];
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        
        return max;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/