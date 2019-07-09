/*

1. We use a queue to store all nodes in level order. Before traversing the next level,
we use size = q.size() to remember how many nodes are in this current level.

*/

class Solution {    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode cur = root;
        q.offer(cur);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                cur = q.poll();
                curList.add(cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(new ArrayList<>(curList));
        }        
        return res;
    }
    
}

/*
Time complexity: O(n)
Space complexity: O(h), h is the height of the tree
*/