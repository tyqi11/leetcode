/*

*/

class BSTIterator {    
    private Deque<TreeNode> stack;
    private TreeNode cur;
    
    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        cur = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || cur != null;
    }

    /** @return the next smallest number */
    public int next() {
        while (cur != null) {
            stack.offerFirst(cur);
            cur = cur.left;
        }
        cur = stack.pollFirst();
        int ans = cur.val;
        cur = cur.right;
        return ans;
    }
}

/*
Time complexity: O(1) for next(), hasNext()
Space complexity: O(h), h is the height of the tree
*/

