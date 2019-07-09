/*

Comparing to Level Order traversal, we use LinkedList instead of ArrayList when we
move to a new level, and add the result at index 0. 

*/

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();//
        if (root == null) {          //////////////////
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
            }      ////////////////////////////////
            res.add(0, new ArrayList<>(curList));//
        }          ////////////////////////////////
        return res;
    }
}

/*
Time complexity: O(n)
Space complexity: O(h)
*/