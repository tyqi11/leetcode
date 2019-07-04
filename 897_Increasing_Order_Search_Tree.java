/*

1. This is like an inorder traversal problem. We can do it recursively or iteratively.


*/


// Solutoin 1: Iteration
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode dummy = new TreeNode(0);
        TreeNode p = dummy;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.offerLast(cur);
                cur = cur.left;
            }
            cur = stack.pollLast();
            p.right= cur;
            cur.left = null;
            cur = cur.right;
            p = p.right;
        }
        return dummy.right;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/

/********************************************************/
// Solution 2: Recursion
class Solution {
    
    public TreeNode cur;
    
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        cur = dummy;
        traverse(root);
        return dummy.right;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        root.left = null;
        cur.right = root;
        cur = cur.right;
        traverse(root.right);
    }
}

/*
Time complexity: O(n)
Space complexity: O(h), h is the height of the tree. It is logn for average, but n in the worst case.
*/