/*
# Two pointers

1. This is similar to 26, but the current element should be compared with the one two 
positions before. 

2. When we follow the previous way to solve the same problem, it gives a wrong answer when 
nums= [1,1,1,2,2,3]. When j reaches index 3, i = 2, then nums[i] = 2, and the original array
       0 1 2 3 4 5
becomes nums = [1,1,2,2,2,3]. When j reaches index 4, nums[j] == nums[i], i will not increase.
                0 1 2 3 4 5
While in fact i should copy the second 2. We find a flaw in the algorithm.

3. The solution is the compare nums[j] with nums[i-2] as i is the value we want to store and 
compare.

4. So in 26, it will be better if we compare nums[j] with nums[i-1] instead of nums[j-1]. 

5. This algorithm can be generalized to "at most k duplicates" with checking if nums[j] != nums[i - k].

6. It might be better to use the iterator: for (int n : nums), to avoid mixing i and j.

*/
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 2;
        for (int j = 2; j < nums.length; j++) {
            if (nums[j] != nums[i - 2]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/