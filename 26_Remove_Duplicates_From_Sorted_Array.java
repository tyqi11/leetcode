/*
# Two pointers

1. The given array is sorted, so all elements with the same value lies next to each other.

2. So the only difference between [26. Remove Duplicates from Sorted Array] with 
[27. Remove Element] is: this time we remove one element not because it equals to certain
target, but its previous value. So we only change one line in the whole code block.

*/

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 1; // return length
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[j - 1]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1), in-place remove
*/