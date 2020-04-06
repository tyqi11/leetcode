/*
# No trick, just compare.
I was thinking about two pointers, but it did not work.
*/
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        
        int n = s.length();
        for (int len = 1; len <= n / 2; len++) {
            if (n % len != 0) {
                continue;
            }
            
            String sub = s.substring(0, len);
            int i = len;
            while (i < n) {
                if (s.substring(i, i + len).equals(sub)) {
                    i += len;
                } else {
                    break;
                }
            }
            if (i == n) {
                return true;
            }
        }
        
        return false;
    }
}


/*
Time complexity: O() n^2? I'm not sure.
Space complexity: O() n^2?
*/