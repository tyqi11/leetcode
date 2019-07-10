/*
# Recursion

*/

class Solution {
    public int sumNumbers(TreeNode root) {       
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = 10 * sum + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return dfs(root.left, sum) + dfs(root.right, sum);
    }
}

/*
Time complexity: O()
Space complexity: O()
*/