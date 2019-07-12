/*

1. A typical way to solve circular array problems is to extend the original array to
twice its length, 2nd half has the same elements as first half(@shawngao). Here we 
iterate over the array twice to avoid copying the array.

2. Comparing to 496. Next Greater Element I, there are duplicate elements in the array.
So we cannot use hashmap to store values and their indices as latter ones will erase
the result of previous ones. And we cannot use stack to store the values either. Storing
indices in the stack helps us to keep track of elements (maybe duplicate value, but
never duplicate index). So when compare, we do num > nums[stack.peekLast()] instead of
num > stack.peekLast(). And similar other changes from values to indices.

3. All the indices(numbers) in the stack are ones looking forward to a greater element.
So in the second round of searching, we do not offer elements into the stack anymore.
The second round is only to find greater elements for the ones that did not find greater
elements in the first round.

*/

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new ArrayDeque<>(); // index
        for (int i = 0; i < n * 2; i++) { // go twice the array as circular
            int num = nums[i % n];
            while (!stack.isEmpty() && num > nums[stack.peekLast()]) {
                ans[stack.pollLast()] = num;
            }
            if (i < n) {
                stack.offerLast(i);
            }
        }
        
        return ans;
    }
}

/*
Time complexity: O(2n)
Space complexity: O(n)
*/