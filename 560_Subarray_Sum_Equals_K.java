/*
# Two pointers

1. A direct way to solve the problem is iterate all possible starts and all possible
ends, with time complexity O(n^2). But this solution will cause TLE.

2. We care about only sum difference. So to get sum[i, j], we can count sum[0, j]
and sum[0, i]. Put them in a HashMap.


*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); // <sum, times appears>
        map.put(0, 1); // sum = 0 appears once at the beginnning
        
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                cnt += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return cnt;       
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/