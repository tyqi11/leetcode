/*

1. To do both get(key) and put(key, value) in constant time, we think of HashTable 
(or HashMap). To remove and add a new node in constant time, we think of doubly
linked list. So we use a HashMap to get the key in O(1) and a doubly linked list
to remove and add in O(1). As we need to add to the head and remove from the tail
frequently, we keep a head node and a tail node.

2. In the implementation, try to use as much helper functions as possible to avoid
duplicate code blocks. For example, we split moveToHead() into remove() and add().

credit to https://www.youtube.com/watch?v=S6IfqDXWa10 for a great explanation and codes.

*/

class LRUCache {

    class DNode {
        int key, val;  // key in node is used for searching in map and deleting
        DNode prev, next;
    }
    
    private Map<Integer, DNode> map;
    private DNode head, tail;
    private int cnt;
    private int cap;
        
    public LRUCache(int capacity) {
        map = new HashMap<>();
        
        head = new DNode();
        head.prev = null;
        
        tail = new DNode();
        tail.next = null;
        
        head.next = tail;
        tail.prev = head;
        
        cnt = 0;
        cap = capacity;
    }
    
    public int get(int key) {
        DNode n = map.get(key);
        if (n == null) {
            return -1;
        }
        moveToHead(n);
        return n.val;
    }
    
    public void put(int key, int value) {
        DNode n = map.get(key);
        if (n != null) {
            n.val = value;
            moveToHead(n);
        } else {
            n = new DNode();
            n.key = key;
            n.val = value;
            map.put(key, n);
            addAtHead(n);
            cnt++;
        }     
        
        while (cnt > cap) {
            removeAtTail();
            cnt--;
        }
    }
    
    private void moveToHead(DNode node) {
        remove(node);
        addAtHead(node);
    }
    
    private void addAtHead(DNode node) {
        node.next = head.next;
        node.prev = head;
        
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeAtTail() {
        map.remove(tail.prev.key);
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
    }
    
    private void remove(DNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
