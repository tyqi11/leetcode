/*
# Binary Search

1. Partition
 0        3           7
[4, 5, 6, 7, 0, 1, 2, 3], nums[mid] > nums[right], left in order
         mid
 0       mid          7
[5, 6, 7, 0, 1, 2, 3, 4], nums[mid] < nums[right], right in order

When left in order, target is sure to fall into the right part only if:
target > nums[mid] || target <= nums[right]

When right in order, target is sure to fall into the right part only if:
nums[mid] < target <= nums[right]

2. terminal condition
According to the summary in README, when we return results during the search,
we use left <= right. When we leave the loop but still not find the answer,
we return -1.

*/

class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }    
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > nums[right]) {
                if (target > nums[mid] || target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

/*
Time complexity: O(logn)
Space complexity: O(1)
*/