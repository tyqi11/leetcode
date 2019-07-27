/*
# Backtracking

0. This is a classical problem, solution at GeeksforGeeks:
https://www.geeksforgeeks.org/partition-set-k-subsets-equal-sum/

1. First we get the sum of all numbers to calculate the target in each subset. 
Then we sort the array and check numbers from large to small. A visited array is
needed to avoid using one number twice.

2. During the partitioning, we keep the number of subsets, and sum in this subset. 
Once sum == target, we go back to the largest number and start a new round of 
checking. If not, we go to each smaller elements and go deeper from here.

3. It is vital to do backtracking, which in this problem is visited[i] = false.
From nums[i], we go deep and check, so visited[i] = true, if this path works, 
return true. If it does not work, we drop current number by visited[i] = false.

*/

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) {
            return false;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        return dfs(nums, visited, nums.length - 1, 0, sum / k, k);
    }
    
    private boolean dfs(int[] nums, boolean[] visited, int idx, int sum, int target, int k) {
        if (k == 0) {
            return true;
        }
        if (sum == target && dfs(nums, visited, nums.length - 1, 0, target, k - 1)) {
            return true;
        }
        for (int i = idx; i >= 0; i--) {
            if (!visited[i] && sum + nums[i] <= target) {
                visited[i] = true;
                if (dfs(nums, visited, i - 1, sum + nums[i], target, k)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/