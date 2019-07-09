/*

1. Comparing to Level Order Traversal, the only difference is marked with "//". After
each level, we change the order to add cur.val by using LinkedList instead of ArrayList,
because for LinkedList, it takes O(1) to add at head and add at tail.

*/

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode cur = root;
        q.offer(cur);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                cur = q.poll();
                ///////////////////////////////
                if (leftToRight) {           // 
                    curList.add(cur.val);    //
                } else {                     //
                    curList.add(0, cur.val); //
                }                            //
                ///////////////////////////////
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(new LinkedList<>(curList));
            leftToRight = !leftToRight;
        }
        return res;
    }
}

/*
Time complexity: O(n)
Space complexity: O(h)
*/