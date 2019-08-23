/*

Check whether a node's two children have the same height. If they are the same,
current node is the lca we want. If not, go check the child with bigger height.

Use a hashmap to record the height of each node to avoid duplicate counting and
solve the problem by one-pass. When there are large amount of nodes, it's faster.
(but it takes O(n) extra space for the map) But for this problem, with only 1000 
nodes, hashmap is even more time-consuming.


*/

// Solution 1: O(n^2)
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return root;
        } 
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == right) {
            return root;
        } else {
            return left > right ? lcaDeepestLeaves(root.left) : lcaDeepestLeaves(root.right);
        }
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(h), for call stack
*/

/******************************************************************/
// Solution 2: with hashmap, O(n)
class Solution {
    Map<TreeNode, Integer> map = new HashMap<>();
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return root;
        } 
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == right) {
            return root;
        } else {
            return left > right ? lcaDeepestLeaves(root.left) : lcaDeepestLeaves(root.right);
        }
    }
    
    private int getHeight(TreeNode root) {
        if (map.containsKey(root)) {
            return map.get(root);
        }
        if (root == null) {
            return 0;
        }
        int h = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        map.put(root, h);
        return h;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n + h)
*/
