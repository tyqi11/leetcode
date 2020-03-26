/*

1. We will use a stack to store the "turning point" so that we can pick up from 
the "breakup" in a reverse order.

2. The difficulty in this problem is how to organize the logic between different 
situations. If the current node is one similar to common ListNodes, we do 
cur = cur.next, when cur == null, we should return. So this is the outer loop. 

3. For the inner loop, we should deal with two special cases.
3.1 cur.child != null. If the cur is not the last of the current list, we need to 
put cur.next into the stack for future connection. Then we move the child to 
cur.next position and connect the child's prev to cur.
3.2 we reach the end of a child list. We do not use cur == null to check as we 
need the last valid node to connect. At this time, if the stack is not empty, we 
should connect to the last break point by popping it out. Else if the stack is 
empty, we finished all the connections.

*/

// Solution 1: Iteration
class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Deque<Node> stack = new ArrayDeque<>();
        Node cur = head;

        while (cur != null) {
        	if (cur.child != null) {
        		if (cur.next != null) {
        			stack.offerLast(cur.next); 
        		} // if cur is the last node, don't add null (not support null)
                cur.next = cur.child;
                cur.next.prev = cur;
                cur.child = null;
            } else if (cur.next == null && !stack.isEmpty()) {
            	cur.next = stack.pollLast();
            	cur.next.prev = cur;
            }
          	cur = cur.next; 
        }
        return head;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), actually we use number-of-break-points space.
*/

/**********************************************************/
// Solutoin 2: Recursion
// I cannot understand any of the posts. Need help.