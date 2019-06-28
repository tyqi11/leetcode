/*

1. Comparing with 2 Add Two Numbers, the easy way is to reverse the list first, 
do the math, and reverse back. It takes O(3*n) time complexity and O(1) space 
complexity. If we are not allowed to change the input (as asked in follow up),
we can count the lengths of the lists, add corresponding digits and save them
in a stack, and pop up the add results to create a linked list. 

2. It might be easier to understand to use two stacks, but using only one stack 
(as shown below) saves more space.

*/


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 0. corner cases
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        // 1. count the lengths of two lists
        int count1 = 0, count2 = 0;
        for (ListNode p1 = l1; p1 != null; p1 = p1.next) {
            count1++;
        }
        for (ListNode p2 = l2; p2 != null; p2 = p2.next) {
            count2++;
        }
        // 2. add w/out considering carry, save in a stack
        int max = Math.max(count1, count2);
        Deque<Integer> stack = new ArrayDeque<>();
        for (ListNode p1 = l1, p2 = l2; max > 0; max--) {
            int n1 = 0, n2 = 0;
            if (max <= count1) {
                n1 = p1.val;
                p1 = p1.next;
            }
            if (max <= count2) {
                n2 = p2.val;
                p2 = p2.next;
            }
            stack.offerLast(n1 + n2);
        }
        // 3. count with carry
        int carry = 0;
        ListNode after = null;
        while (!stack.isEmpty()) {
            ListNode cur = new ListNode(stack.pollLast());
            int temp = (carry + cur.val) / 10; 
            // cannot do carry = (carry + ..).. as we need the old carry for cur.val
            cur.val = (carry + cur.val) % 10;
            cur.next = after;
            after = cur;
            carry = temp;
        }
        if (carry != 0) {
            ListNode cur = new ListNode(carry);
            cur.next = after;
            after = cur;
        }
        
        return after;
    }
}

/*
Time complexity: O(n), O(n) for lengths counting + O(n) for adding + O(n) for organizing result
Space complexity: O(max(m, n))
*/