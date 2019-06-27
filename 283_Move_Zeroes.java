/*
# Two pointers

1. Move all the non-zero elements in order to the left. 

2. Track the end of non-zero elements. 

3. Fill the rest of the array with 0's.

*/

class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int i = 0; // [0, i) are non-zero elements
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }
        while (i < nums.length) {
            nums[i++] = 0;
        }
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/