/*

Same idea as in inorder traversal. The first solution is easy to understand but works
for only preorder traversal. The second is a general template for traversal problems.
Compare it with other traversal problems so you can find.

*/

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollLast();
            ans.add(cur.val);
            if (cur.right != null) {
                stack.offerLast(cur.right);
            }
            if (cur.left != null) {
                stack.offerLast(cur.left);
            }
        }
        return ans;
    }
}

// we can also make it more general for inorder and postorder traversal
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                ans.add(cur.val);
                stack.offerLast(cur);
                cur = cur.left;
            }
            cur = stack.pollLast();
            cur = cur.right;
        }
        return ans;
    }
}
/*
Time complexity: O(n)
Space complexity: O(h), h is the height of the tree
*/