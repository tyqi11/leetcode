class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node levelStart = root;
        while (levelStart != null) {
            Node cur = levelStart;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            levelStart = levelStart.left;
        }
        
        return root;
    }
}