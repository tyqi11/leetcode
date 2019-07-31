/*
# Two pointers

1. One pointer points to the previous target index, one points at the current
index.

2. The shortest distance to left and right, means the distance to left first, 
and distance to right, and get the minimum.


*/

class Solution {
    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        int[] ans = new int[n];
        int pre = -n; // chars before first C, ans[i] >= n

        // pass 1, left to right, distance
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) != C) {
                ans[i] = i - pre;
            } else {
                pre = i;
            }
        }
        // pass 2, right to left, shortest distance
        for (int i = pre - 1; i >= 0; i--) {
            if (S.charAt(i) != C) {
                ans[i] = Math.min(ans[i], pre - i);
            } else {
                pre = i;
            }
        }
        return ans;        
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/