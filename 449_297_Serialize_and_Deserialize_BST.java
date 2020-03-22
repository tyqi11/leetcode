/*

*/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        treeToString(root, sb);
        return sb.toString();
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        TreeNode root = null;
        for (String s : arr) {
            if (s.length() > 0) {
                root = stringToTree(root, Integer.parseInt(s));
            }
        }
        return root;
    }
    
    private void treeToString(TreeNode root, StringBuilder sb) {
        if (root == null ) {
            return;
        }
        
        sb.append(root.val).append(","); 
        treeToString(root.left, sb);
        treeToString(root.right, sb);        
    }
    
    private TreeNode stringToTree(TreeNode root, int v) {       
        if (root == null) {
            return new TreeNode(v);
        }
        if (v < root.val) {
            root.left = stringToTree(root.left, v);
        } else {
            root.right = stringToTree(root.right, v);
        }
        return root;
    }
}



/*
Time complexity: O()
Space complexity: O()
*/
