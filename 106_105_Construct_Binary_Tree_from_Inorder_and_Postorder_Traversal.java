/*

This follows the same idea of 105. Construct Binary Tree from Preorder and Inorder Traversal.

*/


class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildHelper(inorder, 0, inorder.length - 1, 
        	               postorder, 0, postorder.length - 1, inMap);
    }
    
    private TreeNode buildHelper(int[] inorder, int inStart, int inEnd, 
    	                         int[] postorder, int postStart, int postEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootIndex = inMap.get(root.val);
        int nodesLeft = rootIndex - inStart;
        root.left = buildHelper(inorder, inStart, rootIndex - 1, 
        	                    postorder, postStart, postStart + nodesLeft - 1, inMap);
        root.right = buildHelper(inorder, rootIndex + 1, inEnd, 
        	                     postorder, postStart + nodesLeft, postEnd - 1, inMap);
        return root;
    }
}

/*
Time complexity: O(n)
Space complexity: O(h), h is the height of the tree, in worst case, h -> n
*/