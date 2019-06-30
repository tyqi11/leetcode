/*

1. There are two ways to solve this problem. The one using PriorityQueue is easier
to implement. We keep a pq of size k, so that the next least element is on top, 
and it takes only O(1) to get it. After this least one pops out, we push into
cur.next into comparision, until the pq is empty.

2. Another way is MergeSort. We use binary search to split the lists and compare
each group every time. After the first loop, the number of lists will be reduced
to k/2, then k/4, ... all together logk levels. But on each level, we need to 
compare all the n nodes' value to merge.

*/

// Solution 1: PriorityQueue
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }
        Queue<ListNode> q = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode node : lists) {
            if (node != null) { // in case: [[],[]], node == null
                q.offer(node);   
            }
        } // O(logk)
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!q.isEmpty()) {
            cur.next = q.poll();
            cur = cur.next;
            if (cur.next != null) {
                q.offer(cur.next);
            }
        }
        return dummy.next;        
            
    }
}

/*
Time complexity: O(nlogk), for n times of q.offer(), q.offer() takes O(logk)
Space complexity: O(k), for the priority queue
*/
/****************************************************************/
// Solution 2: Merge Sort
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }
        return sort(lists, 0, lists.length - 1);
    }
    
    private ListNode sort(ListNode[] lists, int start, int end) {
        if (start >= end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = sort(lists, start, mid);
        ListNode right = sort(lists, mid + 1, end); // logk times of sort
        return merge(left, right); 
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}

/*
Time complexity: O(nlogk), ? many people say O(nklogk)?
Space complexity: O(nklogk), ? I am not so sure
*/