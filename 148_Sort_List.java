/*
# Merge Sort

0. Summary of sorting algorithms:
O(n): Bucket Sort:  
	  Radix Sort: Sort by digits from least significant to most significant
O(nlogn): Quick Sort: Choose a pivot, split < pivot and >= pivot, and find a new pivot
          Merge Sort: Split into halves, sort in each half, merge two halves
O(n^2): Bubble Sort:
        Insertion Sort: Assume [0, i-1] is sorted, move current to the right place
	  	Selection Sort: Find the smallest of the rest, swap with the current


1. To do list sorting in O(nlogn) time, we think about MergeSort. Instead of splitting
into halves, we split by power of 2's. Sort in each 2^n, and combine. Then, sort
in 2^(n+1). 

2. For example: 5 -> 4 -> 3 -> 2 -> 1 -> null

step = 1: (split into length of 1, and merge)
split: dummy -> 5 ->null   4 ->null  3 -> 2 ->  1 -> null
        pre    left      right      cur
merge: dummy -> 4 -> 5     3 -> 2 ->  1 -> null
                    pre   cur
split: dummy -> 4 -> 5     3->null   2->null  1 -> null
                    pre   left     right     cur
merge: dummy -> 4 -> 5 -> 2 -> 3     1 -> null
                              pre    cur
split: dummy -> 4 -> 5 -> 2 -> 3     1 -> null  null   null
                              pre   left        right  cur
merge: dummy -> 4 -> 5 -> 2 -> 3 -> 1 -> null

step = 2: (split into length of 2, and merge)
split: dummy -> 4 -> 5 -> null    2 -> 3 -> null  1 -> null
        pre    left             right            cur
merge: dummy -> 2 -> 3 -> 4 -> 5    1 -> null
                              pre  cur
split: dummy -> 2 -> 3 -> 4 -> 5    1 -> null  null   null
                              pre  left       right   cur
merge: dummy -> 2 -> 3 -> 4 -> 5 -> 1 -> null
                                   pre    

step = 4: (split into length of 4)
split: dummy -> 2 -> 3 -> 4 -> 5 -> null  1 -> null  null
        pre    left                     right        cur
merge: dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null
                                   pre   cur

step = 8: step > n (number of nodes), return dummy.next                                 

*/

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int n = 0;
        while (head != null) {
            head = head.next;
            n++;
        } // when exit, n is the number of ListNodes
        
        for (int step = 1; step < n; step <<= 1) {
            ListNode pre = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, step);
                cur = split(right, step);
                pre = merge(left, right, pre);
            }
        }
        return dummy.next;        
    }
    
    private ListNode split(ListNode head, int step) {
        if (head == null) {
            return null;
        }
        for (int i = 1; i < step && head.next != null; i++) {
            head = head.next;
        }
        ListNode right = head.next;
        head.next = null;
        return right;
    }
    
    private ListNode merge(ListNode left, ListNode right, ListNode pre) {
        ListNode cur = pre;
        while (left != null || right != null) {
            if (left != null && (right == null || left.val < right.val)) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        return cur;
    }
}

/*
Time complexity: O(nlogn)
Space complexity: O(1)
*/