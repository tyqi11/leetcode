/*
# Binary Search

*/

class Solution {
    public int findMin(int[] nums) {   
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else { // nums[mid] <= nums[right]
                right = mid;
            }
        }
        return nums[left];
    }
}

/*
Time complexity: O(logn)
Space complexity: O(1)
*/