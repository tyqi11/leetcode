/*

1. We divide this problem into 5 smaller problems.
1.1 Skip empty spaces on both sides, like "  good    job     " -> "good    job"
1.2 skip extra empty spaces between words, like "good    job" -> "good job"
1.3 reverse the string as a whole, from "good job" -> " boj doog"
1.4 reverse each word, separated by one space, to "job good"
1.5 use the part of the original string to construct a new string

*/

class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length()  == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        // 1. skip empty spaces on both sides
        while (left < right && str[left] == ' ') {
            left++;
        }
        while (right > left && str[right] == ' ') {
            right--;
        }
        if (left == right && str[left] == ' ') {
            return ""; // input: "a", left = right but not empty
        }
        // 2. skip extra empty spaces between words
        int i = left + 1; // [left, i) is the result w/out extra spaces
        int j = left + 1; // we need j - 1, so j from left + 1 
        while (j <= right) { 
            if (str[j - 1] == ' ' && str[j] == ' ') {
                j++;
            } else {
                str[i++] = str[j++];
            }
        }
        right = i - 1;
        // 3. reverse the whole string
        reverse(str, left, right);
        // 4. reverse each word
        i = left;
        for (j = left;j < right; j++) {
            if (str[j] == ' ') {
                reverse(str, i, j - 1);
                i = j + 1;
            } 
        }
        reverse(str, i, right); // do not forget the last part
        // 5. return the result, remember right + 1
        return new String(str).substring(left, right + 1);
    }
    
    private void reverse(char[] str, int left, int right) {
        while (left < right) {
            char temp = str[left];
            str[left++] = str[right];
            str[right--] = temp;
        }
    }       
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/