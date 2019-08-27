/*
# pre-order
# level order

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
            return sb.append("null");
        }
        sb.append(root.val).append(",");
        treeToString(root.left, sb).append(",");
        treeToString(root.right, sb);
        return sb;
    }
    
    private TreeNode stringToTree(Queue<String> q) {
        String str = q.poll();
        if (str.equals("null")) {
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

/***********************************************************/
// Solution 2: level order traversal
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur != null) {
                res.append(cur.val + ",");
                q.offer(cur.left);
                q.offer(cur.right);
            } else { // cur == null
                res.append("null,");
            }
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] nodes = data.split(",");
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        q.offer(root);
        
        int i = 1;   // starts from 1
        while (i < nodes.length) { // for each parent, check two children nodes
            TreeNode parent = q.poll();
            if (!nodes[i].equals("null")) {
                TreeNode l = new TreeNode(Integer.valueOf(nodes[i]));
                q.offer(l);
                parent.left = l;
            }
            i++;
            if (!nodes[i].equals("null")) {
                TreeNode r = new TreeNode(Integer.valueOf(nodes[i]));
                q.offer(r);
                parent.right = r;
            }
            i++;
        }    
        return root;
    }
}