/*

1. The key is the second if loop. when root is a left node and root.val == sum, we
get a right answer. And this ensures that when it reaches the last return line,
sum - root.val is not 0. So if root == null, we will return false.

2. It is even better if we write:
if (root.left == null && root.right == null) {
	return root.val == sum;
}
as root is already a leaf node, if root.val != sum, we do not need to go to the 
next level of recursion and return false because root == null.

*/

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) 
            || hasPathSum(root.right, sum - root.val);
        
    }
}

/*
Time complexity: O()
Space complexity: O(h)
*/