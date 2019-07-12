/*

1. This is a follow up of 15. 3Sum. We do the same thing of setting a first
element, and do a 2Sum in the rest of the array.

2. In the iteration, we need to compare the difference of current sum and target,
and record the min distance and closest result.

3. To improve performance, we skip duplicates when setting the first element.
But we cannot skip duplicates when searching for the second and third element
because the duplicates may lead to a final result.

*/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // O(nlogn)
        int min = Integer.MAX_VALUE;
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            while (i != 0 && i < nums.length - 2 && nums[i] == nums[i - 1]) {
                i++;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum - target) < min) {
                    min = Math.abs(sum - target);
                    res = sum;
                }                
                if (sum == target) {
                    return sum;
                } 
                
                if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;        
    }
}


/*
Time complexity: O(n^2)
Space complexity: O(1)
*/