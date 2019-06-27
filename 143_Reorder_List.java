/*

1. This is a very typical problem which can be solve by splitting it into 3 different
smaller problems. Find the middle node -> Reverse Linked List -> Connect two (sorted)
lists.

2. I used a trick here as the first and second half of lists both end with the 
middle node. So I used (slow != fast && slow.next != fast) as the while condition.
When there are odd number of nodes, like 1 -> 2 -> 3 -> 4 -> 5, after reversing:
1 -> 2 -> 3                   1(s1)   2(s2)-> 3 (s3, f3)
          ^                   |     ^ |     ^
          |  after two loops, v   /   V    /
5 -> 4 ---                    5(f1)   4(f2)        slow == fast
or there are even number of nodes, like 1 -> 2 -> 3 -> 4, after reversing:
1 -> 2 -> 3                   1(s1) 2 (s2) -> 3 (f2)
          ^                   |    ^    
          |  after one loop,  v   /    
4 --------                    4(f1)               slow.next == fast

*/

class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 1. find the start of second half
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 2. reverse the second, get the secondHead
        ListNode secondHead = reverse(slow);
        slow = head;
        fast = secondHead;
        // 3. connect two lists
        while (slow != fast && slow.next != fast) {
            ListNode sNext = slow.next;
            ListNode fNext = fast.next;
            slow.next = fast;
            fast.next = sNext;
            slow = sNext;
            fast = fNext;
        }      
    }
    
    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = dummy.next;
            dummy.next = next;
        }
        return dummy.next;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/