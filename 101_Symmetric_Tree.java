/*

Same logic as 100. Same Tree. Only difference is from comparing 
    left.left == right.left && left.right == right.right ?  
--> left.left == right.right && left.right == right.left ?

*/

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }
        return (left.val == right.val) && helper(left.left, right.right) && helper(left.right, right.left);       
    }
}

/*
Time complexity: O(n)
Space complexity: O(h), h is the height of the trees.
*/