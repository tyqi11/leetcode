/*

1. The key point in solving the problem is what to return and what to update. 
We need to return the height of each node but we want to height just to update
the max. The update function and return function is different. We set max as a 
global variable, update at each node and return it as the final result.

*/

class Solution {
    
    public int max = Integer.MIN_VALUE;
    
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxHeight(root);
        return max;       
    }
    
    private int maxHeight(TreeNode root) {
        if (root == null) {
            return 0;
        } 
        int left = maxHeight(root.left);
        int right = maxHeight(root.right);
        max = Math.max(max, left + right);
        return 1 + Math.max(left, right);
    }   
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/