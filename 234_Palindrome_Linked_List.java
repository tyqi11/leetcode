/*
# Two pointers

1. It is a shortcat if we can use similar algorithms from previous problems. In 
this problem, we can do it using two other solutions: finding the middle of a list,
and reversing a linked list. After that, we just need to compare each value.  
*/

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = reverse(slow);
        slow = head;
        while (slow != null && fast != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;   
    }
    
    // same as 206. Reverse Linked List
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

/*
Time complexity: O(n), from O(2n), O(n) for finding the middle, O(n/2) for 
				 reversing half of the list, and O(n/2) for comparing
Space complexity: O(n), if using recursion for reversing list
				  O(1), if using iteration for reversing list
*/