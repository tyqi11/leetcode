/*


*/

class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        ListNode cur = root;
        int cnt = 0;
        while (cur != null) {
            cur = cur.next;
            cnt++;
        }
        int each = cnt / k;
        int extra = cnt % k;
        cur = root;
        ListNode pre = null;
        for (int i = 0; cur != null && i < k; i++) { // cur != null
            res[i] = cur;
            for (int j = 0; j < each; j++) {
                pre = cur;
                cur = cur.next;
            }
            if (i < extra) {
                pre = cur;
                cur = cur.next;
            }
            pre.next = null;
        }
        return res;
    }
}


/*
Time complexity: O(n)
Space complexity: O(1)
*/