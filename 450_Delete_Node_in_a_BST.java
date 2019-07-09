/*

0. As is indicated in the problem description, it takes two steps to solve this problem,
First we find the one to be removed, then we remove it.

1. To connect the tree after removal, we need to track the previous node of the one
to be removed. Find the node according to the characteristic of Binary Search Tree.

2. To remove root, we connect root.left as the left child of the left-most leaf node
of root.right. 
            pre                              pre
             |  left/right                    |  left/right
            root                           root.right
           /    \                           /      \
  [root.left]  root.right     ---->       .a.      .b.
     |           /  \                     /
    ...        .a.  .b.              [leftmost]
               /                      /      \
           [leftmost]          [root.left]   .c.
             /  \   
          null  .c.

      root.val > root.left.val, root.right.val > leftmost.val > root.val
  --> leftmost.val > root.left.val

*/

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = root;
        TreeNode pre = null;
        // 1. find the node to remove
        while (cur != null && cur.val != key) {
            pre = cur;
            if (key < cur.val) {
                cur = cur.left;
            } else { // key > cur.val
                cur = cur.right;
            }
        } // cur is the node we want        
        // 2. delete the node
        if (pre == null) {
            return delete(cur);
        } // remove root
        if (pre.left == cur) {
            pre.left = delete(cur);
        } else {
            pre.right = delete(cur);
        }
        return root;
    }
    
    private TreeNode delete(TreeNode root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }
        TreeNode next = root.right;
        while (next.left != null) {
            next = next.left;
        } // next is the rightmost
        next.left = root.left;
        return root.right;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/