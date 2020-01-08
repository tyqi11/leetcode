/*
# Binary Search

*/

class Solution {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;
        int missing = (nums[hi] - nums[lo] + 1) - n; // should be (), but is: n
        
        if (k > missing) { // result > nums[n - 1]
            return nums[n - 1] + (k - missing);
        }
        
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            missing = (nums[mid] - nums[lo]) - (mid - lo);
            if (k > missing) {
                k -= missing;
                lo = mid;
            } else {
                hi = mid;
            }
        }
        
        return nums[lo] + k;
    }
}

/*
Time complexity: O(logn)
Space complexity: O(1)
*/