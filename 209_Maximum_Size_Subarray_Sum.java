/*
# Two pointers [O(n) time complexity]
# Binary Search [O(nlogn) time complexity]

1. Using two pointers i and j to indicate the start and end of the result array. 
While sum >= s, we shorten the array to the most until sum < s. Keep updating the
min variable. Check if the min changes from the default value before return, as it may never
satisfies the requiment.

2. We can also using binary search. For each element, we find the subarray starting
from that index satisfying sum >= s. It takes O(n) for iteration, and O(logn) for
each search.

*/

// Solution 1: two pointers
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= s) {
                min = Math.min(min, j - i + 1);
                sum -= nums[i++];
            }
        }
        return (min == Integer.MAX_VALUE) ? 0 : min;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/*******************************************************************/
// Solution 2: binary search
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] sums = new int[n];
        int min = Integer.MAX_VALUE;
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) { 
            sums[i] = sums[i - 1] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            int target = s + (i == 0 ? 0 : sums[i - 1]);
            int j = findWindowEnd(i, sums, target);
            if (j != nums.length) {
                min = Math.min(min, j - i + 1);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    private int findWindowEnd(int start, int[] sums, int target) {
        int left = start;
        int right = sums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sums[mid] == target) {
                return mid;
            } else if (sums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/*
Time complexity: O(nlogn), O(logn) for binary search in each of O(n) iterations
Space complexity: O(n), for sums array
*/