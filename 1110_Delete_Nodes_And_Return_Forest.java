/*
# Recursion (Because it's a Tree problem!)

*/


class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> forest = new LinkedList<>();
        if (to_delete == null || to_delete.length == 0) {
            forest.add(root);
            return forest;
        } else if (root == null) {
            return forest;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i : to_delete) {
            set.add(i);
        }

        root = deleteNodes(root, set, forest);
        if (root != null) {
            forest.add(root);
        }
        
        return forest;
    }
    
    private TreeNode deleteNodes(TreeNode node, Set<Integer> set, List<TreeNode> forest) {
        if (node == null) {
            return null;
        }
        
        node.left = deleteNodes(node.left, set, forest); // if null, child not exists
        node.right = deleteNodes(node.right, set, forest);
        
        if (set.contains(node.val)) {
            if (node.left != null) {
                forest.add(node.left);
            }
            if (node.right != null) {
                forest.add(node.right);
            }
            return null; // this node is deleted, return null
        }
        
        return node; // this node is not deleted, not null
    }
}

/*
Time complexity: O(E + V), go through all the edges and vertices
Space complexity: O(m + h), m is the length of to_delete, h is the height of the tree
*/

