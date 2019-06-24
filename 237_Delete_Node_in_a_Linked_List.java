/*

1. This problem is very tricky. As we can only modify the nodes after the given 
one, we save the value of the node.next node, and delete the node.next node 
instead. Personally I do not believe this could be an interview question. But 
this is a good way for us to broaden our horizons on how to solve problems.


*/

class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

/*
Time complexity: O(1)
Space complexity: O(1)
*/