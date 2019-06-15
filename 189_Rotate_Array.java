/*

1. The easiest and most understandable way with O(n) time complexity and O(1) space complexity 
is to swap the elements for three times, from 0 to end, first part with k elements, and the 
rest, as the codes show.

2. Another way with O(n) time complexity and O(1) space complexity is to do cyclic replacements.
Move each element k steps after the current position.

*/

// Solution 1
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k == 0) {
            return;
        }
        int n = nums.length;
        k %= n;
        rotate(nums, 0, n - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, n - 1);
    }
    
    private void rotate(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
}

/*****************************************************************/

// Solution 2
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        k %= n;
        int count = 0;
        for (int start = 0; count < n; start++) {
            int cur = start;
            int preVal = nums[start];
            do {
                int next = (cur + k) % n;
                int temp = nums[next];
                nums[next] = preVal;
                preVal = temp;
                cur = next;
                count++;
            } while (start != cur); // a semicollon here
        }
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/