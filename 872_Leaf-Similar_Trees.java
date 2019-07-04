/*
# DFS

Tree Traversal

*/

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        
        dfs(root1, s1);
        dfs(root2, s2);
        
        return s1.toString().equals(s2.toString());
    }
    
    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            sb.append(root.val);
        } else {
            dfs(root.left, sb);
            dfs(root.right, sb);
        }
    }
}

/*
Time complexity: O(t1 + t2), t1/t2 is the nodes of tree1/tree2
Space complexity: O(t1 + t2)
*/