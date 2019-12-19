class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        char[] ch = s.toCharArray();
        // 1. delete leading and trailing spaces
        int i = 0;
        while (i < s.length() && ch[i] == ' ') {
            i++;
        } // exit: i == s.length() || ch[i] != ' '
        if (i == s.length()) {
            return "";
        }
        int j = s.length() - 1;
        while (ch[j] == ' ') {
            j--;
        } // exit when ch[j] != ' '
        if (i == j) {
            return String.valueOf(ch[i]);
        }
        
        // 2.remove extra multiple spaces between words
        int left = i;
        int p = i + 1;
        int q = i + 1;
        while (q <= j) {
            if (ch[q] == ' ' && ch[q - 1] == ' ') {
                q++;
            } else {
                ch[p++] = ch[q++];
            }
        }
        
        int right = p - 1;
        
        // 3. reverse the whole string
        reverse(ch, left, right);
        
        // 4. reverse each word
        for (i = left, j = left; j < right; j++) {
            if (ch[j] == ' ') {
                reverse(ch, i, j - 1);
                i = j + 1;
            }
        }
        reverse(ch, i, j);
        return new String(ch).substring(left, right + 1);
    }
    
    private void reverse(char[] str, int left, int right) {
        while (left < right) {
            char temp = str[left];
            str[left++] = str[right];
            str[right--] = temp;
        }
    }
}