/*
# backtracking

Follow up is 698 Pairition To K Equal Sum Subsets.

*/

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        Arrays.sort(nums); // O(nlogn)
        return dfs(nums, nums.length - 1, 0, sum / 2);
    }
    
    private boolean dfs(int[] nums, int idx, int sum, int target) {
        if (sum == target) {
            return true;
        }
        for (int i = idx; i >= 0 && sum + nums[i] <= target; i--) {
            if (dfs(nums, i - 1, sum + nums[i], target)) {
                return true;
            }
        }
        return false;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/