/*

1. There are plenty of corner cases to deal with, and the solution below covers
all of them in one logic. Thus I consider it as the best solution.

*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;   
            }
            Integer pre = map.get(sum);
            if (pre == null) {
                map.put(sum, i);
            } else if (i - pre >= 2) {
                return true;
            }
        }
        return false;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), worst case all sums are different and are put into map
*/
