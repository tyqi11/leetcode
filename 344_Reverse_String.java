/*

1. Veru basic.

*/

class Solution {
    public void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            if (s[i] != s[n - 1 - i]) {
                char temp = s[i];
                s[i] = s[n - 1 - i];
                s[n - 1 - i] = temp;   
            }
        }
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/