/*

1. This is a follow up of 852. Peak Index in a Mountain Array. We need three steps
to solve this problem. Check the code below.

*/

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        int left = 0;
        int right = n - 1;
        int mid = 0;
        // 1. find the peak element
        while (left < right) {
            mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int peak = left;
        // 2. find target in left half
        left = 0;
        right = peak;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int cur = mountainArr.get(mid);
            if (cur == target) {
                return mid; // find in left half, return
            } else if (cur > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 3. If not in left half, search right half
        left = peak;
        right = n - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int cur = mountainArr.get(mid);
            if (cur == target) {
                return mid; // find in right half, return
            } else if (cur > target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // not found in both two halves
    }
}

/*
Time complexity: O(logn)
Space complexity: O(1)
*/