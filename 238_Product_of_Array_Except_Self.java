/*

1. Product of array except self = product of elements on the left * product of 
elements on the right. Going from left to right, we save the product of 0 to 
i - 1 as ans[i]. Going from right to left, we save the product of n-1 to i + 1
as temp. So ans[i] * temp should be the final result ans[i].

*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for (int i = n - 2; i >= 0; i--) {
            temp *= nums[i + 1];
            ans[i] *= temp;
        }
        return ans;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1), not count the output array
*/