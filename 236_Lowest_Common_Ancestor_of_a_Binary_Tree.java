/*

1. We need a way to mark that one of the nodes is found. Here we do:
	if (root == null || p == root || q == root) {
        return root;
    }
   So if root == null, there is not valid nodes, return null.
      if p == root || q == root, we find a target node, return the node, not null.
   In recursion, we go from the deepest to find if we reach a target.

2. If we find one target in left subtree and one target in right subtree, the LCA is
   the root. Else if one subtree returns null, it shows that both targets are in the
   other subtree.
	if (left != null && right != null) return root;
    if (left == null) return right;
    if (right == null) return left;

3. To prune（剪枝）and avoid unnecessary visiting, we check *left* before go to the
right subtree. If left != null, there is at least one node in left subtree. 
If left is neither p nor q, it must be their LCA, which is the final result. 

*/

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if (left != null && left != p && left != q) { //
            return left;                              // for pruning
        }                                             // 
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}


/*
Time complexity: O(n)
Space complexity: O(h)
*/

/********************************************************/
// Follow up: if either a or b is not in the tree?
class Solution {
    public TreeNode LCAFollowUp(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = lowestCommonAncestor(root, p, q); 
        // get the answer from the former problem first, then do double check
        if (ans == null) {
            return null; // sure to have found neither
        } else if (ans != p && ans != q) {
            return ans; // sure to have found both p and q
        } else { // just found one, check if the other is in the tree
            if (ans == p) { 
                return lowestCommonAncestor(ans, q, q) == null? null : ans;
            } else { // ans == q
                return lowestCommonAncestor(ans, p, p) == null? null : ans;
            }
        }
    }
}