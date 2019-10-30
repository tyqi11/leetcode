// DFS
class Solution {
    private Map<Node, Node> map = new HashMap<>(); // <oldNode, newNode>
    
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        if (map.containsKey(node)) {
            return map.get(node);
        }
        
        Node newNode = new Node(node.val, new LinkedList<Node>());
        map.put(node, newNode);
        
        for (Node n : node.neighbors) {
            newNode.neighbors.add(cloneGraph(n));
        }
        
        return newNode;
    }
}

// BFS
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        Queue<Node> q = new LinkedList<>(); // oldNode
        Map<Node, Node> map = new HashMap<>(); // <oldNode, newNode>
        Node newNode = new Node(node.val, new LinkedList<Node>());
        map.put(node, newNode);
        q.offer(node);
        
        while (!q.isEmpty()) {
            Node cur = q.poll(); // oldNode
            
            for (Node n : cur.neighbors) {
                if (!map.containsKey(n)) {
                    Node newN = new Node(n.val, new LinkedList<Node>());
                    map.put(n, newN);
                    q.offer(n);
                }
                map.get(cur).neighbors.add(map.get(n));
            }
        }
        
        return newNode;
    }
}