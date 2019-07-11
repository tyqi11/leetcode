/*
# Binary Search

0. An easy way is going through all elements in O(n) time. But this problem requires
us to do it in O(logn), so we think of binary search.

1. A typical two pointers problem. We need to compare nums[mid] with its neighbor.
As left < right, we know mid < right. So mid + 1 will not lead to ArrayIndexOutOfBounds.
But mid may be 0 so mid - 1 may not work. Thus we compare nums[mid] with nums[mid + 1].
1.1 If nums[mid] < nums[mid + 1], e.g. [.... 2(mid)  5(mid + 1)...], 2 will never
be the right answer but 5 meets half of the requiremens, so left = mid + 1.
1.2 If nums[mid] > nums[mid + 1], e.g. [.... 8(mid)  6(mid + 1)...], 8 may be an answer.
So right = mid.
1.3 As is indicated in the problem. there will not be nums[mid] == nums[mid + 1].

2. nums[left] and nums[right] are both possible answers. So when left == right, this
number meets the requirement from both left and right side.

*/

class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right  = mid;
            } else {
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