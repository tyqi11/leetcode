class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // recursion
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }     
        return left == null ? right : left;
    }
}

// Follow-up 1: pruning
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // recursion
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // pruning
        if (left != null && left != p && left != q) {
            return left;                                
        }

        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }     
        return left == null ? right : left;
    }
}

// Follow-up 2: if either a or b is not in the tree?
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