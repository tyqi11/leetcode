/*

1. 3Sum = A set first element + 2Sum.

2. To avoid duplicates, first we sort the array to make duplicates next to each other.
Then in each iteration, check if the current is the sme as previous.

3. We can also do it using DFS, but it will be O(n^3) time complexity and cause TLE.

*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }
        Arrays.sort(nums); // O(nlogn)
        int n = nums.length;
        if (nums[0] > 0 || nums[n - 1] < 0) {
            return res;
        }
        // i, j, k: index of first, second, third element
        for (int i = 0; i < n - 2; i++) {
            while (i != 0 && i < n - 2 && nums[i] == nums[i - 1]) {
                i++;
            } // exit when nums[i] is not equal to a previous
            if (nums[i] > 0) {
                return res;
            } // if the smallest > 0, no more answers
            // for each first element, do 2Sum
            int j = i + 1;
            int k = n - 1;
            int sum = 0 - nums[i];
            while (j < k) {
                if (nums[j] + nums[k] == sum) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j + 1] == nums[j]) {
                        j++;
                    }
                    while (j < k && nums[k - 1] == nums[k]) {
                        k--;
                    }
                    j++; // IMP, when exit, j is the last duplicate 
                    k--; // IMP, when exit, k is the last duplicate
                } else if (nums[j] + nums[k] < sum) {
                    j++;
                } else {
                    k--;
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