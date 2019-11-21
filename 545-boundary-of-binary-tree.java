/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<Integer> res = new LinkedList<>();
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return res;
        }
        
        res.add(root.val);
        leftBoundary(root.left);
        leaves(root.left);
        leaves(root.right);
        rightBoundary(root.right);
        
        return res;
    }
    
    private void leftBoundary(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return; // if find a leave node, skip, to avoid duplicates
        }
        res.add(root.val);
        if (root.left != null) {
            leftBoundary(root.left);
        } else {
            leftBoundary(root.right);
        }
    }
    
    private void rightBoundary(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return; // if find a leave node, skip, to avoid duplicates
        }
        if (root.right != null) {
            rightBoundary(root.right);
        } else {
            rightBoundary(root.left);
        }
        res.add(root.val); // go deep first, and add root at last
    }
    
    private void leaves(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        leaves(root.left);
        leaves(root.right);
    }
}