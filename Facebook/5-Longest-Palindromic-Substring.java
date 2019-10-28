class Solution {
    public String longestPalindrome(String s) {
        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = extend(s, i, i);
            int len2 = extend(s, i, i + 1);
            if (maxLen < Math.max(len1, len2)) {
                maxLen = len1 > len2 ? len1 : len2;
                start = len1 > len2 ? (i - len1 / 2) : (i + 1 - len2 / 2);
            }            
        }
        return s.substring(start, start + maxLen);
    }
    
    private int extend(String s, int i, int j) {
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        return j - i - 1; // i [[[]]] j
    }
}