/*

*/

class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        treeToString(root, sb).setLength(sb.length() - 1); // remove the last ","
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        String[] parts = data.split(",");
        List<String> list = Arrays.asList(parts);
        Queue<String> q = new LinkedList<>(list);
        return stringToTree(q);
    }
    
    private StringBuilder treeToString(Node root, StringBuilder sb) {
        if (root == null) {
            return sb.append("null,"); // add ","
        }
        
        sb.append(root.val).append(",");
        sb.append(root.children.size()).append(",");
        for (Node n : root.children) {
            treeToString(n, sb);
        }
        return sb;
    }
    
    private Node stringToTree(Queue<String> q) {
        String cur = q.poll();
        if (cur.equals("null")) {
            return null;
        }
        
        int count = Integer.valueOf(q.poll());
        Node root = new Node(Integer.valueOf(cur), new ArrayList<Node>(count));
        for (int i = 0; i < count; i++) {
            root.children.add(stringToTree(q));
        }
        return root;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/
