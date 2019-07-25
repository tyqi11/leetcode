/*
# Dynamic Programming

1. Be careful that one = Math.max(nums[0], nums[1]). If there are only two rooms,
we still steal the one with max amount of money.

*/

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        
        int two = nums[0];
        int one = Math.max(nums[0], nums[1]);
        int temp;
        for (int i = 2; i < nums.length; i++) {
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