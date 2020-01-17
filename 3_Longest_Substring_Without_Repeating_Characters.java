/*
update: a better solution using two pointers is added below

1. To get a substring, we need to know the start index and end index. To get the longest,
we need an extra max variable. When iterating over all the characters in the string, if
the current character appeared at index i before, the substring can only start from i + 1.
And from i + 1 to current index, we get the length of the substring and compare with 
the max. 

2. Next time we find another repeating character, the start index is updated to, not i + 1,
but Math.max(start, i + 1) because we still need to follow the rules set by previous
characters.

*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        int[] count = new int[256];
        Arrays.fill(count, -1);
        int max = 0;
        int start = 0; // [start, i] is the result length
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i);
            if (count[idx] != -1) {
                start = Math.max(start, count[idx] + 1);
            }
            count[idx] = i;
            max = Math.max(max, i - start + 1);
        }
        return max;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/*************************************************/
// Two pointer
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] count = new int[128];
        int maxLen = 1;
        int i = 0, j = 0;
        while (j < s.length()) {
            count[s.charAt(j)]++;
            while (count[s.charAt(j)] > 1) {
                count[s.charAt(i++)]--;
            }
            maxLen = Math.max(maxLen, j - i + 1);
            j++;
        }
        
        return maxLen;
    }
}