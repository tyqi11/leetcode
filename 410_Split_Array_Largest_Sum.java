/*
# Binary Search

1. The low bound should be the maximum element in the array instead of the
average sum / m. An example is [1, 1, 1, 2147483647]. IF average < max,
max suits in no subarray.

*/

class Solution {
    public int splitArray(int[] nums, int m) {
        long left = 0;
        long right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        while (left < right) {
            long mid = left + ((right - left) >> 1);
            if (canSplit(nums, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int) left;
    }
    
    private boolean canSplit(int[] nums, int m, long target) {
        int count = 1; // avoid count++ at the end
        long sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > target) {
                sum = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }
}

/*
Time complexity: O(n*log(sum - max))
Space complexity: O(1)
*/