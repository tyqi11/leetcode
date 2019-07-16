/*
# Binary Search

1. Comparing to 153, one more condition, when nums[mid] == nums[right], do
right--.

*/

class Solution {
    public int findMin(int[] nums) {    
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }
}

/*
Time complexity: O()
Space complexity: O()
*/