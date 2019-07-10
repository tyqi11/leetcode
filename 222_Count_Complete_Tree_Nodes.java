/*

1. The easiest way must be traversal all the nodes and count. But this is a general
way for any trees, and for complete binary tree, we can take advantage of its defition.

2. We count the height of the leftmost path and rightmost path. If they are the same,
this is a complete binary tree whose last level is completely filled. Total nodes: 2^level - 1.
If the two heights are different, we go to the next level to check two smaller subtrees.
                        1
                    /   |   \ 
                  2     |     3
                / | \   |   / | \
               4  |  5  |  6  |  7
                  |     |     |
             second   first   second 
                     partition

*/

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;
        TreeNode leftNode = root;
        TreeNode rightNode = root;
        while (leftNode != null) {
            leftNode = leftNode.left;
            leftHeight++;
        } // leftmost must exist, height of the tree
        while (rightNode != null) {
            rightNode = rightNode.right;
            rightHeight++;
        } // leftHeight - 1 or leftHeight 
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1; // 2^(leftHeight) - 1
        } else { // leftHeight = 1 + rightHeight
            return 1 + countNodes(root.left) + countNodes(root.right);
        }    // root +   left subtree nodes  + right subtree nodes  
    }
}

/*
Time complexity: O(logn*logn), there are logn levels, in each level, we go from
                               root to left, that is logn times of counting.
Space complexity: O(logn)
*/