/*
# Dynamic Programming

1. This is similar to 198. House Robber, but houses are in a circle this time.
We split this problem into two sub-problems. Houses at [0, nums.length - 2] and
[1, nums.length - 1]. And the max of the two answers is the final result.


*/

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int first = rob(nums, 0, nums.length - 2);
        int second = rob(nums, 1, nums.length - 1);
        return Math.max(first, second);
    }
    
    private int rob(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        } else if (start + 1 == end) {
            return Math.max(nums[start], nums[end]);
        }
        
        int two = nums[start];
        int one = Math.max(nums[start], nums[start + 1]);
        int temp;
        for (int i = start + 2; i <= end; i++) {
            temp = one;
            one = Math.max(two + nums[i], one);
            two = temp;
        }
        
        return one;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/