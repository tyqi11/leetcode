/*

1. This is the extension of reverse nodes in pairs. But there is a big difference.
Reversing in pairs means that we can finish the reversing in one time and loop 
until it reaches the end of list. But in this problem, there is one outer loop
to go through the groups and one inner loop to reverse k nodes. After each inner
loop, we reset pre like a new dummy.

2. There are also two ways to solve the problem, recursively and iteratively. But
this time, recursion and iteration are more different than usual. Recursion is
better at time complexity.

*/

// Solution 1: iteration
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;    
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        int group = count / k;
        cur = head;
        for (int i = 0; i < group; i++) {
            // in each group, reverse k nodes
            for (int j = 1; j < k; j++) { // j starts from 1
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            } 
            pre = cur; // pre is the new dummy
            cur = pre.next; // cur is the new given start node
        } 
        return dummy.next;
    }
}

/*
Time complexity: O(n), from O(2n)
Space complexity: O(1)
*/

/***********************************************************/
// Solution 2: recursion
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 1. check if there are k nodes left
        ListNode cur = head;
        int count = 0;
        while (count < k) {
            if (cur == null) {
                return head;
            } // less than k nodes
            cur = cur.next;
            count++;
        } // check null first, cur is at k+1 node, count = k
        // 2. reverse k nodes
        ListNode newHead = reverseKGroup(cur, k);
        while (count > 0) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
            count--;
        }
        return newHead;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), from O(n / k) for stack.
*/
