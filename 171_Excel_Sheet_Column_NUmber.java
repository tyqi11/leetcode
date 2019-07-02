/*

1. int cur = s.charAt(i) - 'A' + 1.
*/

class Solution {
    public int titleToNumber(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + cur;
        }
        return ans;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/