/*

1. We keep a stack to store the numbers who have not found a greater one yet and a
HashMap to store number and its next greater one.

2. When we iterate to a number *num* and it's smaller than the previous one, put it 
into stack. When *num* is larger, pop from the stack all the stored ones < num, and
put them in the map.
For example: 1, 9, 5, 3, 7
At 1: 					 	stack: 1
At 9: put(1, 9), 		 	stack: 9
At 5, 						stack: 9, 5
At 3, 					 	stack: 9, 5, 3
At 7, put(3, 7)				stack: 9, 5
	  put(5, 7)             stack: 9
	                        stack: 9, 7

3. For all numbers in nums1, check if it is a map key. If it is, we found its greater
one before, map.get(). If it is not a key, there is no greater one, return -1.

*/

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return nums1;
        }
        int[] ans = new int[nums1.length];
        if (nums2 == null || nums2.length == 0) {
            Arrays.fill(ans, -1);
            return ans;
        }
        Map<Integer, Integer> map = new HashMap<>(); // target, its next greater
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peekLast() < num) { // 
                map.put(stack.pollLast(), num);
            }
            stack.offerLast(num);
        }
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
}

/*
Time complexity: O(m + n), m is the length of nums1, n is the num of nums2
Space complexity: O(n), for map and stack to store nums2 temps
*/