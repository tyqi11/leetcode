/*

1. For tree traversal, there are two ways: recursively and iteratively. Recursion
is easier but it takes much space in the call stack.

*/

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
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

/*
Time complexity: O(n)
Space complexity: O(n)
*/

/*********************************************************/
// Solution 2: Recursion
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        return res;
    }
    
    private void traverse(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        traverse(root.left, res);
        res.add(root.val);
        traverse(root.right, res);
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), for call stack,
                        worst case O(n), average case O(logn)
*/