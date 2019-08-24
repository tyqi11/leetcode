/*

*/

class Solution {
    private int idx = 0;
    
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return bst(preorder, Integer.MAX_VALUE);
    }
    
    private TreeNode bst(int[] pre, int bound) {
        if (idx == pre.length || pre[idx] > bound) {
            return null;
        }
        TreeNode root = new TreeNode(pre[idx++]);
        root.left = bst(pre, root.val);
        root.right = bst(pre, bound);
        
        return root;
    }
}


/*
Time complexity: O(n)
Space complexity: O(h)
*/