/*

1. In each string, there might be only a start, or start + "->" + end. So we only
add the string when we find the end.

2. To avoid checking if we are finding the start or end, we add one string in each
for loop. First we record the start number. Then if nums[i + 1] - nums[i] == 1,
the current num[i] is not an end. When we reaches the end of array, or the end
of current range, we check the end comparing with the start. Add differents strings
if start == end or not.

*/

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int start = nums[i];
            while (i + 1 < n && (nums[i + 1] - nums[i] == 1)) {
                i++;
            } // exit when i = n - 1 or nums[i] is an end
            if (nums[i] != start) {
                list.add(start + "->" + nums[i]);
            } else {
                list.add(start+""); // type transition
            }
        }
        return list;        
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/