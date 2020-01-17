/*
# PriorityQueue

When it comes to the maximum, or minimum of a group of number, the first data
structure we think of should be Heap. So we can solve this problem using Priority
Queue. (the solution is boring so I'll skip it)

But we can also use pointers to solve the problem. Although the code is 
not quite reusable for other problems.
*/

class Solution {
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        
        Integer big = null;
        Integer mid = null;
        Integer sml = null;
        
        for (Integer n : nums) {
            if (n.equals(big) || n.equals(mid) || n.equals(sml)) {
                continue;
            }
            
            if (big == null || n.compareTo(big) > 0) {
                sml = mid;
                mid = big;
                big = n;
            } else if (mid == null || n.compareTo(mid) > 0) {
                sml = mid;
                mid = n;
            } else if (sml == null || n.compareTo(sml) > 0) {
                sml = n;
            }
        }
        
        if (sml == null) {
            return big;
        }
        return sml;        
    }
}


/*
Time complexity: O(n)
Space complexity: O(1)
*/

