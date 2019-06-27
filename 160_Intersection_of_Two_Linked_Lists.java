/*

1. Check the lengths of two lists. Move the pointer of the longer list k steps
after the head, k is the lengths difference. In this way, the two pointer can
reach the end after same steps.

2. Go one step afterward and check. When they intersect, the two nodes are the 
same. If we reach the two ends but still not find the intersection, return null.

*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        int countA = 0;
        while (p1 != null) {
            p1 = p1.next;
            countA++;
        }
        ListNode p2 = headB;
        int countB = 0;
        while (p2 != null) {
            p2 = p2.next;
            countB++;
        }
        p1 = headA;
        p2 = headB;
        if (countA < countB) {
            for (int i = 0; i < countB - countA; i++) {
                p2 = p2.next;
            }
        } else if (countB < countA) {
            for (int i = 0; i < countA - countB; i++) {
                p1 = p1.next;
            }
        }
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/