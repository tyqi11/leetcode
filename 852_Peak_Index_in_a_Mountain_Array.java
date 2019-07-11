/*
# Binary Search

1. This is similar to 162. Find Peak Element but even easier. Use the same logic is
enough to solve this problem.

*/

class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int left = 0;
        int right = A.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (A[mid] < A[mid + 1]) {
                left = mid + 1;
            } else if (A[mid] > A[mid + 1]) {
                right = mid;
            }
        }
        return left;
    }
}

/*
Time complexity: O(logn)
Space complexity: O(1)
*/