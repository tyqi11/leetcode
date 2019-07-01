/*

1. This is similar to 290 Word Pattern, but easier. As we only need to map from
character to character. And there are only 256 ASCII letters. So we can store
them in arrays.

*/

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int[] sc = new int[256];
        int[] tc = new int[256];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (sc[s.charAt(i)] != tc[t.charAt(i)] ) {
                return false;
            }
            sc[s.charAt(i)] = i + 1;
            tc[t.charAt(i)] = i + 1;
        }
        return true;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/