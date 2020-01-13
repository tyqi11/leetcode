/*
# Sliding Window
*/

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] count = new int[26];
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }
        int matching = 0;
        
        int i = 0, j = 0;
        while (j < s2.length()) {
            char c = s2.charAt(j++);
            if (count[c - 'a'] > 0) {
                matching++;
            }
            count[c - 'a']--;
            while (j - i > s1.length()) {
                char c2 = s2.charAt(i++);
                count[c2 - 'a']++;
                if (count[c2 - 'a'] > 0) {
                    matching--;
                }
            }
            if (matching == s1.length()) {
                return true;
            }
        }
        
        return false;
    }
}

/*
Time complexity: O(m + n), m and n are the lengths of the two strings
Space complexity: O(1), O(26) for the count array
*/