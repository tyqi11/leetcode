/*
# Binary Search

1. Same as 33. Search in Rotated Sorted Array, but with duplicate elements. 
The only difference is when nums[mid] == nums[right], do right--.

*/

class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > nums[right]) { // left in order
                if (target > nums[mid] || target <= nums[right]) { // go right
                    left = mid + 1;
                } else { 
                    right = mid - 1;
                }
            } else if (nums[mid] < nums[right]) { // right in order
                if (target > nums[mid] && target <= nums[right]) { // go right
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else { // nums[mid] == nums[right]
                right--;
            }
        }
        return false;
    }
}

/*
Time complexity: O(logn)
Space complexity: O(1)
*/