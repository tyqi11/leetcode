/*
# Recursion

*/

class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }
        
        return root.val + rangeSumBST(root.left, L, root.val) + rangeSumBST(root.right, root.val, R);
                       
    }
}

/*
Time complexity: O(n), average case O(h)
Space complexity: O(n), average case O(h)
*/