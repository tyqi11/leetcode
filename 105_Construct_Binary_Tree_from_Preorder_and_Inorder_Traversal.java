/*

0. In the preorder traversal array, we can easily get the root of each subtree 
but it's hard to split the rest into left children nodes and right children nodes. 
In the inorder traversal array, with the root node, all its left children nodes are 
on its left, and right children nodes on its right. This is will be the way we 
construct the tree.

1. As we may need to know one node's place in inorder array frequently, we use a 
hashmap to store this information.

2. Here we will illustrate how we see this two arrays. Then the code is easy to understand.
left-sub        [preStart+1     preStart+nodesLeft]                 
preorder:    [1,    2,      4,    5,                 3,                   6,  7]
right-sub  preStart                                 [preStart+nodesLeft+1    preEnd]
--------------------------------------------------------------------------------------
left-sub     [inStart     rootIndex-1]
inorder:     [4,      2,  5,            1,      6,            3,   7]
right-sub                            rootIndex  [rootIndex+1     inEnd]

*/

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        // 1. store position index of inorder array in a map
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }                                 
        return buildHelper(preorder, 0, preorder.length - 1, 
                           inorder, 0, inorder.length - 1, inMap);
    }                          
    
    private TreeNode buildHelper(int[] preorder, int preStart, int preEnd, 
                                 int[] inorder, int inStart, int inEnd, 
                                 Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = inMap.get(root.val);
        int nodesLeft = rootIndex - inStart;
        root.left = buildHelper(preorder, preStart + 1, preStart + nodesLeft, 
                                 inorder, inStart, rootIndex - 1, inMap);
        root.right = buildHelper(preorder, preStart + nodesLeft + 1, preEnd, 
                                  inorder, rootIndex + 1, inEnd, inMap);
        return root;
    }
}

/*
Time complexity: O(n)
Space complexity: O(h), h is the height of the tree, in worst case, h -> n
*/