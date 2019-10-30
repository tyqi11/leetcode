class Solution {
    private Map<Node, Node> map = new HashMap<>();
    
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        Node dummy = new Node();
        Node cur = dummy;
        Node p = head;
        while (p != null) {
            Node n = new Node(p.val, null, null);
            map.put(p, n);
            cur.next = n;
            cur = cur.next;
            p = p.next;            
        } // p == null
        
        p = head;
        cur = dummy.next;
        while (p != null) {
            Node n = p.random;
            cur.random = map.get(n);
            p = p.next;
            cur = cur.next;
        }
        
        return dummy.next;
    }
}