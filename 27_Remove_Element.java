/* 
# Two pointers

1. This question gives an array of ints and asks us to remove elements in-order, 
this reminds me of the two pointers method.

2. First we check the corner cases, and return 0 if nums is null or its length is 0 

3. Then we creat pointer i as the indicator of the current result. Be careful whether the 
problem asks us return the last index of the result array or the length. In this case, from 
index 0 to index (i-1) is the result elements, and i is the length to be returned.

4. Finally, we create pointer j indicating the current element we are dealing with. j goes
from 0 to the last index: nums.length - 1. If nums[j] is the same as the target value, we 
ignore this element, and increase j. If nums[j] does not equal to the target, we use nums[i]
to record the current value, and increase i. Thus (i-1) is always the last one not equal to
target.

*/

class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0; 
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}

/*
Time complexity: O(n), as in the worst case, both i and j go through index 0 to nums.length - 1
Space complexity： O(1), as this is an in-place algorithm, we use O(1) extra space to store
two indicators.
*/