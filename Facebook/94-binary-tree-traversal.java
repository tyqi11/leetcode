class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.offerLast(cur);
                cur = cur.left;
            }
            cur = stack.pollLast();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}

// recursive way
class Solution {
    List<Integer> res = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        traverse(root);
       
        return res;
    }
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        
        traverse(root.left);
        res.add(root.val);
        traverse(root.right);
    }
}