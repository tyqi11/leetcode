/*

1. Check the current level, and then go both sides at the same time.

*/

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

/*
Time complexity: O(n)
Space complexity: O(h), h is the height of the tree
*/