/*
# Morris Traversal

1. It takes two steps to solve the problem. First we need to find the two mistake 
nodes, then we swap their values.

2. We need to traverse all the nodes. In inorder traversal, all the nodes' values 
will be in ascending order. This helps us to decide if the node is mistakenly places. 
But recursion and iteration traversal both need a O(h), h is the height of the tree, 
space for call stack or a stack to store information. So we need a O(1) space 
complexity traversal method: Morris Traversal. A great illustration (in Chinese) can be 
found at https://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html


*/
class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode pre = null;
        TreeNode first = null, second = null;
        
        // Morris Traversal
        TreeNode temp = null;
        while (root != null){
            if (root.left != null) { // has left subtree
                temp = root.left;
                while (temp.right != null && temp.right != root) {
                    temp = temp.right;
                } 
                if (temp.right == null) { 
                    temp.right = root; // construct threading
                    root = root.left;
                } else { // temp.right = root, threading exists
                    if (pre != null && pre.val > root.val) {
                        if (first == null) {
                            first = pre;
                        }
                        second = root;
                    }
                    pre = root;
                    root = root.right;
                    temp.right = null; // break threading
                }
            } else { // root.left == null
                if (pre != null && pre.val > root.val) {
                    if (first == null) {
                        first = pre;
                    } 
                    second = root;
                }
                pre = root;
                root = root.right;
            }
        }        
        
        // swap two node values;
		if (first != null && second != null) {
		    int t = first.val;
		    first.val = second.val;
		    second.val = t;
		}
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/