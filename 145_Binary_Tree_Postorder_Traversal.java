/*

1. We use the same logic as in preorder traversal and inorder traversal. But comparing
to preorder traversal, we made a few changes:
1.1 Use LinkedList instead of ArrayList and insert value from the head, that is changing
ans.add(cur.val) to ans.add(0, cur.val).
1.2 When traversing, we add cur.right first, and after polling out, move cur to cur.left.
This is exactly the opposite of the preorder traversal, which makes it postorder.

*/

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.offerLast(cur);
                ans.add(0, cur.val);
                cur = cur.right;
            }
            cur = stack.pollLast();
            cur = cur.left;
        }
        return ans;
    }
}

/*
Time complexity: O(n)
Space complexity: O(h)
*/