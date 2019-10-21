// runtime: 1ms, 100%
// memory usage: beat 48.82%

class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        
        int n = nums.length;
        
        // output[i] is the product of nums[0] * ... * nums[i - 1]
        int[] output = new int[n];
        output[0] = 1;
        for (int i = 1; i < n; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        } // [1, 1, 2, 6]
        
        int rightProduct = 1; // nums[i + 1] * ... * nums[n - 1]
        for (int i = n - 2; i >= 0; i--) {
            rightProduct *= nums[i + 1];
            output[i] *= rightProduct;
        }
        
        return output;
    }
}