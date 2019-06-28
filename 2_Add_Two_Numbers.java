/*

1. One thing to take care of is update pre in each iteration so that it always
represents if we need an extra 1. And do not forget to check it when we reach
the ends of both lists.

2. Another thing is to use inline judgements to deal with different situations.
If we use while (p1 != null && p2 != null), after one reaches the end, we should
do similar things to the other list as we always need to check if there is a pre = 1.
*/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null || p2 != null) {
            int n1 = (p1 == null) ? 0 : p1.val;
            int n2 = (p2 == null) ? 0 : p2.val;
            int sum = carry + n1 + n2;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            p1 = (p1 == null) ? p1 : p1.next;
            p2 = (p2 == null) ? p2 : p2.next;
        } // exit when p1 == null || p2 == null
        if (carry != 0) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/