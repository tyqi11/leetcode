/*

1. We use a brute force way to solve this problem, which takes no extra space. 

*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != c) {
                    return strs[j].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}

/*
Time complexity: O(minLen * n)
Space complexity: O(1)
*/