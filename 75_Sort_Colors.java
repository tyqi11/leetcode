/*

1. We use 3 pointers to divide the array into three parts. Show as below:
         i           j           k
0  0  0  1  1  1  1  2  2  1  0  1  2  2 
[0, i) is 0, [i, j) is 1 and (k, n - 1] is k. j is also the current pointer.

2. One thing to take care of is that, when swap(i, j), the new element comes to j
must be 1 (because left-side of j is 1, j has checked the elements of i before), 
so j++ is fine. But when swap(j, k), as the element on index k has never been 
checked, so we can only do k--. And in the next round, we need to check this
new element again. When all elements are checked, it will be like:
            i              k  j          
0  0  0  0  1  1  1  1  1  1  2  2  2  2  
so the termination condition is j > k.

*/

class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int i = 0; // [0, i) is red 0
        int j = 0; // [i, j) is white 1 
        int k = nums.length - 1; // (k, n - 1] is blue 2
        while (j <= k) {
            if (nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == 2) {
                swap(nums, j, k);
                k--;
            } else { // nums[j] == 1
                j++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/