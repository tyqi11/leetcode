/*

1. The difficulty is the count the index to add or delete.

2. One bug in the test case is in addAtIndex(), if the given index < 0, do addAtHead() or set
index = 0, then you can pass the tests.

*/

class MyLinkedList {
    
    class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int v) {
            val = v;
            next = null;
        }
    }

    ListNode dummy;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        dummy = new ListNode(0);
    }
    
    /** Get the value of the index-th node in the linked list. If the index is 
    invalid, return -1. */
    public int get(int index) {
        if (index < 0) {
            return -1;
        }
        ListNode cur = dummy.next;
        int curIdx = 0;
        while (cur != null) {
            if (curIdx == index) {
                return cur.val;
            }
            cur = cur.next;
            curIdx++;
        }
        return -1;
    }
    
    /** Add a node of value val before the first element of the linked list. 
    After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode next = dummy.next;
        dummy.next = new ListNode(val);
        dummy.next.next = next;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode cur = dummy;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new ListNode(val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If 
    index equals to the length of linked list, the node will be appended to the 
    end of linked list. If index is greater than the length, the node will not be 
    inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0) {
            index = 0;
        } // test case bug, if add index < 0, treat it like add at head
        ListNode cur = dummy;
        int curIdx = 0;
        while (cur != null) {
            if (curIdx == index) {
                break;
            }
            cur = cur.next;
            curIdx++;
        }
        if (cur == null) {
            return;
        }
        ListNode next = cur.next;
        cur.next = new ListNode(val);
        cur.next.next = next;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0) {
            return;
        }
        ListNode cur = dummy;
        int curIdx = 0;
        while (cur != null) {
            if (curIdx == index) {
                break;
            }
            cur = cur.next;
            curIdx++;
        }
        if (cur.next != null) {
            cur.next = cur.next.next;
        }
    }
}
