/*
credit to @sid_bit31:
Some of the questions which can be asked to the interviewer before implementing 
the solution:

For simplicity, are the keys integers only?
For collision resolution, can we use chaining?
Do we have to worry about load factors?
Can we assume inputs are valid or do we have to validate them?
Can we assume this fits memory?

credit to @asingeorge: 
Integer.hashCode() 
Returns: a hash code value for this object, equal to the primitive int value 
represented by this Integer object. 
https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html#hashCode()

*/

class MyHashMap {
    
    class ListNode {
        int key, val;
        ListNode next;
        
        ListNode(int k, int v) {
            key = k;
            val = v;
        }// next = null; is default
    }
    
    final ListNode[] buckets;

    /** Initialize your data structure here. */
    public MyHashMap() {
        buckets = new ListNode[1000]; 
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int i = getIndex(key);
        if (buckets[i] == null) {
            buckets[i] = new ListNode(-1, -1);
        }
        ListNode pre = getNode(buckets[i], key);
        if (pre.next == null) {
            pre.next = new ListNode(key, value);
        } else {
            pre.next.val = value;
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this 
    map contains no mapping for the key */
    public int get(int key) {
        int i = getIndex(key);
        if (buckets[i] == null) {
            return -1;
        }
        ListNode node = getNode(buckets[i], key);
        return node.next == null ? -1 : node.next.val;
    }
    
    /** Removes the mapping of the specified value key if this map contains a 
    mapping for the key */
    public void remove(int key) {
        int i = getIndex(key);
        if (buckets[i] == null) {
            return;
        } 
        ListNode pre = getNode(buckets[i], key);
        if (pre.next == null) {
            return;
        } else {
            pre.next = pre.next.next;
        }
    }
    
    // helper functions
    /* Return the bucket index of the key*/
    private int getIndex(int key) {
        return Integer.hashCode(key) % buckets.length;
    }
    
    /* Return the node before the node with target key / before null*/
    private ListNode getNode(ListNode bucket, int k) {
        ListNode cur = bucket;
        ListNode pre = null;
        while (cur != null && cur.key != k) {
            pre = cur;
            cur = cur.next;
        }
        return pre;
    }
}
