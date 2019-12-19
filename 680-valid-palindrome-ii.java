class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        if (s.length() < 2) {
            return true;
        }
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
}