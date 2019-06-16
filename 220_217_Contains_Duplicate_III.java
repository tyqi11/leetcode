/*
# Bucket

1. We will use a hashmap to remember previous appeared numbers. To ensure the two elements we are
comparing satisfies Math.abs(i, j) <= k, after we put element at i into the hashmap, we delete the
element before and at index i - k, make the earliest element nums[i - k + 1]. So that in the next 
loop, the new element nums[i + 1] is at most k positions away from elements in the hashmap.

2. To compare the two values nums[i] and nums[j], we use buckets of size = t + 1. Elements fall into
the same bucket surely meets the requirement. And if there are neighbor buckets, we check the
elements in them, if they meet the "at most t" requirement. It is a bit tricky to get the bucket
index. And we use the following graph to illustrate.
e.g.: t = 2, size = 3
input:   -6 -5 -4 | -3 -2 -1 | 0  1  2 | 3  4  5
in/size: -2 -1 -1 | -1  0  0 | 0  0  0 | 1  1  1
bucket:  -2 -2 -2 | -1 -1 -1 | 0  0  0 | 1  1  1  in < 0: (in+1)/size - 1  

3. We use long instead of int as the number can be very big. If t = 2^32 - 1, size will overflow.
If nums[i] = 2^32 - 1, and nums[j] < 0, nums[i] - nums[j] will overflow.

*/

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || t < 0 || k < 1) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        long size = (long) t + 1; // in case t = 2^32 - 1
        for (int i = 0; i < nums.length; i++) {
            long bucket = getIndex(nums[i], size);
            if (map.containsKey(bucket)) {
                return true;
            } else if (map.containsKey(bucket - 1) && 
                      (Math.abs(nums[i] - map.get(bucket - 1)) <= t)) {
                return true;
            } else if (map.containsKey(bucket + 1) && 
                      (Math.abs(nums[i] - map.get(bucket + 1)) <= t)) {
                return true;
            }
            map.put(bucket, (long) nums[i]);
            if (i >= k) {
                bucket = getIndex(nums[i - k], size);
                map.remove(bucket);
            }
        }
        return false;
    }
    
    private long getIndex(int num, long size) {
        if (num < 0) {
            return (num + 1) / size - 1;
        } else {
            return num / size;
        }
    }
}       

/*
Time complexity: O(n)
Space complexity: O(k)
*/