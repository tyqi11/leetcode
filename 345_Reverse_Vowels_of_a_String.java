/*
# Two pointers

1. We use two pointers to go from both sides to find vowels. Once we find one on
each side, we swap them. One thing to pay attention to is in the inner loop, we 
still need to make sure i < j to avoid two vowels change back to original.

*/

class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] str = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        String vowels = "aeiouAEIOU";
        while (i < j) {
            while (i < j && vowels.indexOf(str[i]) == -1) {
                i++;
            }
            while (j > i && vowels.indexOf(str[j]) == -1) {
                j--;
            }
            char temp = str[i];
            str[i++] = str[j];
            str[j--] = temp;
        }
        return new String(str);
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), for the char array
*/