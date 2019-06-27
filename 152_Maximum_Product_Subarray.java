/*

1. To take care of negative elements, we keep a max and a min at the same time. 
As min * negative value can be the max. So if nums[i] < 0, we swap min and max. 

*/

class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null) {
            return Integer.MIN_VALUE;
        }
        int ans = nums[0];
        for (int i = 1, min = ans, max = ans; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = min;
                min = max;
                max = tmp;
            } // if nums[i] < 0, swap(min, max)
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            
            ans = Math.max(ans, max);
        }
        return ans;        
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/