/*

*/

// Solution 1: the prev node of cur.right is the rightmost of cur.left
class Solution {   
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) { // go to cur.left
                TreeNode lChild = cur.left;
                while (lChild.right != null) {
                    lChild = lChild.right;
                } // find rightmost of cur.left
                lChild.right = cur.right; // it is the pre of cur.right
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right; // if no left child, go right
        }
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/***********************************************************/
// Solution 2: recursion, if there are only root, root.left, root.right,
//             how to connect these three
class Solution {   
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        root.right = temp;
    }
}

/*
Time complexity: O(n)
Space complexity: O(h), for call stack, h is the height, 
*/
