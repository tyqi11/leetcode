/*
# Two pointers

1. An easy way to solve the problem is to use two pointers. One the indicate the
current character of haystack, and one indicate the current character of needle.

2. An advanced way, the KMP agorithm finishes the problem in O(n + m) time. 
*/

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        } else if (haystack == null || haystack.length() == 0) {
            return -1;
        } 
        for (int i = 0; i < haystack.length() - needle.length() + 1 ; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                for (int j = 0; j < needle.length(); j++) {
                    if (needle.charAt(j) != haystack.charAt(i + j)) {
                        break;
                    }
                    if (j == needle.length() - 1) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}

/*
Time complexity: O(n * m), n is the length of haystack, m is the length of needle
Space complexity: (1)
*/