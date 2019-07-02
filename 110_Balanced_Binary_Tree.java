/*

1. The difinition requires *every* node meets depth differ no more than 1. When 
we check bottom-up, we should return the result as soon as one node cannot meet
the requirement. We used -1 as a false in this problem. Before comparing heights
of subtrees, we check if there is a false. If not, we compare and return. Use -1
because the height cannot be -1.

*/

class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }   
        return maxHeight(root) != -1;
    }
    
    private int maxHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxHeight(root.left);
        if (left == -1) {
            return -1;
        }
        int right = maxHeight(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), worst case, if the tree is like a linked list
*/