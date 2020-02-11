/*
# In-Order Traversal
# Divide and Conquer
*/

// inorder traversal
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        int ans = cur.val;
        while (k > 0) {
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            }
            TreeNode n = stack.pollFirst();
            ans = n.val;
            cur = n.right;
            k--;
        }
        return ans;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/

/****************************************************/
// divide and conquer
class Solution {    
    public int kthSmallest(TreeNode root, int k) {
        int cnt = count(root.left);
        if (k == cnt + 1) { // IMP! cnt is the nodes in left subtree
            return root.val;
        } else if (k <= cnt) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - cnt - 1); // all - nodes_on_left - root
        }
    }
    
    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }
}

/*
Time complexity: O(n + n/2 + n/4 + ..) = O(n), n is the number of nodes
Space complexity: O(n), worst case
*/