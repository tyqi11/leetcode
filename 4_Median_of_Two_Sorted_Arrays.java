/*
# Binary search

1. The first solution we may think of is to use two pointers to compare each
numbers in arrays. Move the one pointing to the smaller number until the counter
tells us that we get the median. But in this way we will spend O(n) time, and
it is not allowed.

2. The requirment O(log(m + n)) reminds us of binary search.

3. To further optimize the algorithm, we can get O(log(min(m, n))) time complexity.
Logic and implementation from @Tushar Roy: https://www.youtube.com/watch?v=LPFhl65R7ww&t=1013s

*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }        
        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;
        // do binary search on the shorter array
        while (low <= high) {
            int part1 = low + (high - low) / 2;
            int part2 = (m + n + 1) / 2 - part1; // + 1 to work for both odd and even
            
            int maxLeft1 = (part1 == 0 ? Integer.MIN_VALUE : nums1[part1 - 1]);
            int minRight1 = (part1 == m ? Integer.MAX_VALUE : nums1[part1]);
            
            int maxLeft2 = (part2 == 0 ? Integer.MIN_VALUE : nums2[part2 - 1]);
            int minRight2 = (part2 == n ? Integer.MAX_VALUE : nums2[part2]);
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((m + n) % 2 == 0) { // even numbers in total
                    return 0.5 * (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2));
                } else {
                    return Math.max(maxLeft1, maxLeft2) / 1.0;
                }
            } else if (maxLeft1 > minRight2) {
                high = part1 - 1;
            } else { // maxLeft2 < minRight1
                low = part1 + 1;
            }
        }
        return 0;            
    }
}

/*
Time complexity: O(log(min(m + n)))
Space complexity: O(1)
*/