/*
# Bucket sort

1. The ideal situation is that in an array of length n, it consists of [1, ..., n]. But now,
there are 1) 0, 2) negative numbers, and 3) numbers larger than n (just call them bad numbers) 
mixed in the array so the positive numbers are not on their right position. If we move each 
positive element to its correspoding bucket, when iterating from left to right, we know which 
positive number is the first one missing. We use bucket sort.

2. How do we move? 
2.1 If the current one is a bad number, we leave it just there. It might cover the space of 
one missing positive.
2.2 If the current one is a good number (nums[i] > 0 && nums[i] <= n), we check if it is at the 
right place. For nums[i], its ideal index is nums[i] - 1. On its ideal index, the existing number
is nums[nums[i] - 1]. If nums[nums[i] - 1] == nums[i], it means on the right positon, there is 
the right number. If it is not equal, we change the current number: nums[i] to its ideal index,
which mean we swap number on i with number on ideal index: nums[i] - 1. 

3. Now, the previous nums[i] is moved to its ideal index, we get another new nums[i] from swap.
We need to check again and again (while loop) until nums[i] is a bad number so we don't move it. 
Then, we do i++ to move to the next index. 

4. After we reach the end of the array, all positive numbers are on its right position. Then we
go from left to right, the first position whose value is not one more than the index 
(nums[i] != i + 1), the expected positive number is i + 1. If we reach the end and find no such
situation, the array is already sorted like [1, 2, ... n]. Then we return n + 1.

*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        int i = 0;
        while (i < n) {
            //  need to change   &&   in range, has right pos   &&  right pos has wrong number   
            if (nums[i] != i + 1 && nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
            
        }

        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*
Time complexity: O(n), go through all elements twice
Space complexity: O(1), in-place sort
*/