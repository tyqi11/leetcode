/*
# Two Pointers
*/

class Solution {
    public int expressiveWords(String S, String[] words) {
        if (S.length() == 0 || words.length == 0) {
            return 0;
        }    
        int cnt = 0;
        for (String w : words) {
            if (w.length() != 0 && isStretchy(S, w)) {
                cnt++;
            }
        }
        
        return cnt;
    }
    
    private boolean isStretchy(String s, String word) {
        int i = 0, j = 0; // length >= 1, for both
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) != word.charAt(j)) {
                return false;
            }            
            int len1 = getRepeatedLength(s, i);
            int len2 = getRepeatedLength(word, j);
            if ((len1 < 3 && len1 != len2) || (len1 >= 3 && len1 < len2)) {
                return false;
            }
            i += len1;
            j += len2;
        }
        
        return i == s.length() && j == word.length();
    }
    
    private int getRepeatedLength(String s, int idx) {
        int j = idx;
        while (j < s.length() && s.charAt(j) == s.charAt(idx)) {
            j++;
        }
        return j - idx;
    }
}


/*
Time complexity: O(m + n), m is the length of S, n is the sum lengths of words
Space complexity: O(1)
*/

