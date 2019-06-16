/*

1. Comparing to 217, this time we cares about the indices of elements. So we cannot sort anymore. 
We use a hashmap to record appeared elements and their indices. If a second one appears and they 
an meet the requirements, return true. Else, we change the current element's index to the new one, 
and continue searching.

*/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 1) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int idx = map.get(nums[i]);
                if (i - idx <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/