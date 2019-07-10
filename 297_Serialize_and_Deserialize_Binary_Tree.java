/*

1. We do a preorder traversal of the tree, append each value of the nodes, append "#" as
an indicator of null, separate values with ",", to serialize the tree to a String.

2. When we deserialize, we iterate over the nodes in order, and assign them as left
children and right children.

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
        String[] nodes = data.split(",");
        List<String> nodesList = Arrays.asList(nodes);
        Queue<String> q = new LinkedList<>(nodesList);
        return stringToTree(q);
    }
    
    /** Helper functions*/
    private StringBuilder treeToString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return sb.append("#");
        }
        sb.append(root.val).append(",");
        treeToString(root.left, sb).append(",");
        treeToString(root.right, sb);
        return sb;
    }
    
    private TreeNode stringToTree(Queue<String> q) {
        String str = q.poll();
        if (str.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(str));
        root.left = stringToTree(q);
        root.right = stringToTree(q);
        return root;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/