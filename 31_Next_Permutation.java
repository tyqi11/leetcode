/*

1. From right to left, it is good if numbers are increasing, like [..., 3, 2, 1]. 
We need to find the first number that breaks this order.

2. From right to left, if all the number are increasing, we just need to reverse
the whole array. However, if it decreases at one point, for example, 5 in 
[... 5, 8, 6, 4, 2...]. First we need to find from right the smallest but 
larger than 5 number, here it is 6, then swap, so [... 6, 8, 5, 4, 2].
Then we reverse [8, 5, 4, 2] to make it the smallest.

*/

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        } // exit when i = -1, or nums[i] decreases
        // if not always increasing, swap with the smallest larger first
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // then no matter which situation, reverse i + 1 to end
        reverse(nums, i + 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
    private void reverse(int[] nums, int start) {
        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
}

/*
Time complexity: O()
Space complexity: O()
*/