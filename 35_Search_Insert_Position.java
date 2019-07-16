/*
# Binary Search

1. According to the summary of binary search (in README), if we return answer
during the iteration, loop condition is left <= right.

2. When left == right, mid = left,
2.1 if nums[mid] > target, right = left - 1, left is the index to insert
2.2 if nums[mid] < target, left = left + 1, left is the index to insert
so return left;

*/

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else { // nums[mid] < target
                left = mid + 1;
            }
        }
        return left;
    }
}

/*
Time complexity: O(logn)
Space complexity: O(1)
*/