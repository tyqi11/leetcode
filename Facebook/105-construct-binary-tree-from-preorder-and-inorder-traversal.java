class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
        return root;
    }
    
    private TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd) {
            return null;
        }       
        
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == preEnd) {
            return root;
        } 
        
        int rootIndex = inMap.get(root.val);
        int leftChildren = rootIndex - inStart;
        root.left = buildTree(pre, preStart + 1, preStart + leftChildren, in, inStart, rootIndex - 1, inMap);
        root.right = buildTree(pre, preStart + leftChildren + 1, preEnd, in, rootIndex + 1, inEnd, inMap);
        return root;
    }
}