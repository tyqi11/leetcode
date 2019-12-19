class Solution {
    PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] == b[0]) {
                if (a[1] == b[1]) {
                    return a[2] - b[2];
                } else {
                    return b[1] - a[1];
                }
            } else {
                return a[0] - b[0];
            }
        }
    }); // [x, y, val]
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(root, 0, Integer.MAX_VALUE);
        List<Integer> curList;
        int preX = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] > preX) {
                res.add(new LinkedList<Integer>());   
                preX = cur[0];
            }
            curList = res.get(res.size() - 1);
            curList.add(cur[2]);
        }
        return res;
    }
    
    private void helper(TreeNode root, int x, int y) {
        if (root == null) {
            return;
        }
        
        q.offer(new int[]{x, y, root.val});
        helper(root.left, x - 1, y - 1);
        helper(root.right, x + 1, y - 1);
    }
}

// x small
// y big
// val small