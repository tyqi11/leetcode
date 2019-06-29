/*

1. I made quite a few error when solving this problem. The key was that I ignored
the situation when there are empty spaces after the last word. So we cannot just
get the index of the space before the last word, but need to count the length of
the word, or as shown in my code, record the index of the last character of the 
last word.

*/

class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        int lastChar = i;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                return lastChar - i;
            }
            i--;
        }
        return lastChar + 1;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/