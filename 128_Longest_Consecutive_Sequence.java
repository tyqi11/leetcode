/*
# Sort
# HashSet

1. The first we can think of is to sort the array so that the consecutive 
numbers are placed in order. However, Arrays.sort() requires O(nlogn) time 
complexity while we are required to finish in O(n) time.

2. To know if one number is in the array in O(1) time, we put all of them 
in a HashSet. Then we check if set contains n - 1 to make sure current n
is the first of a sequence. Then we start to find all the consecutive numbers
and check the length with previous longest.

*/

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }      
        int longest = 0;
        for (Integer n : set) {
            if (!set.contains(n - 1)) {
                int cur = n;
                int length = 1;
                while (set.contains(cur + 1)) {
                    cur++;
                    length++;
                }
                longest = length > longest ? length : longest;
            }
        }
        return longest;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/