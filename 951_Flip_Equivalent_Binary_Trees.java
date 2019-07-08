/*

Similar logic as 100 and 101. To make the two trees equivalent after *some* flip operations,
we check both if they can be the same or symmatic. So we check both:
    left.left == right.left && left.right == right.right ?   same
or  left.left == right.right && left.right == right.left ?   symmetric

*/

class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        }
        return (root1.val == root2.val) && 
               (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left))
               || (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right));
    }
}

/*
Time complexity: O()
Space complexity: O()
*/