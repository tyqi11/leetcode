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
    
    private class DNode {
        int key, val;
        DNode prev, next;
    }
    
    private Map<Integer, DNode> map = new HashMap<>();
    private DNode head, tail;
    private int count; // number of nodes in the map
    private int cap; // number of initial capacity set

    public LRUCache(int capacity) {
        head = new DNode();
        head.prev = null;
        
        tail = new DNode();
        tail.next = null;
        
        head.next = tail;
        tail.prev = head;
            
        count = 0;
        cap = capacity;        
    }
    
    public int get(int key) {
        DNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        DNode node = map.get(key);
        if (node != null) { // map contains key
            node.val = value;
            moveToHead(node);
        } else { // map does not contains key
            DNode newNode = new DNode();
            newNode.key = key;
            newNode.val = value;
            map.put(key, newNode);
            addNode(newNode);
            count++;
            if (count > cap) {
                removeLRU();
                count--;
            }
        }
    }
    
    /** helper functions*/
    private void moveToHead(DNode node) {
        removeNode(node);
        addNode(node);
    }
    
    private void addNode(DNode node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    
    private void removeLRU() {
        DNode removed = tail.prev;
        // remove the last before tail
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        map.remove(removed.key);
    }
    
    private void removeNode(DNode node) {
        DNode prevNode = node.prev;
        DNode nextNode = node.next;
        
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}
