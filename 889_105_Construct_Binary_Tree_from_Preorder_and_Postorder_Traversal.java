/*

Same logic as 105 and 106.

*/

class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return buildHelper(pre, 0, pre.length - 1, post, 0, post.length - 1, map);
    }
    
    private TreeNode buildHelper(int[] pre, int preStart, int preEnd, 
    	                         int[] post, int postStart, int postEnd, Map<Integer, Integer> map) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == preEnd) {
            return root;
        }
        int leftIndex = map.get(pre[preStart + 1]);
        int nodesLeft = leftIndex - postStart + 1;
        root.left = buildHelper(pre, preStart + 1, preStart + nodesLeft, 
        	                    post, postStart, leftIndex, map);
        root.right = buildHelper(pre, preStart + nodesLeft + 1, preEnd, 
        	                     post, leftIndex + 1, postEnd - 1, map);
        return root;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/