/*

1. To meet the requirements, we can think from an opposit way. We find the characters
that appear less than k times first, and break the long string into shorter ones, in which 
letters appear >= k times when counting in the longer string. When we go deep into the 
shorter string and do the same thing until we narrow down to a string where all the 
letters meet the needs. The length of this string will be an answer.

2. Another interesting way is to use a bit mask to track if each character meets the k
times requirement. If one letter shows up but it shows up < k times, we use a 1 on the 
corresponding bit. If a letter shows >= k times, we change 1 to 0. For letters never
appear, we consider them as satisfying. When all the bits in mask is 0, that is mask == 0,
we get an answer.

*/

// Solution 1: easy to understand, O(n)
class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        return helper(s, k, 0, s.length());
    }
    
    private int helper(String s, int k, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int[] count = new int[26]; // lowercase letters only
        for (int i = start; i < end; i++) {
            count[s.charAt(i) - 'a']++;
        }
        boolean flag = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] < k && count[i] > 0) {
                flag = false;
                break;
            }
        } // if exits with flag = true, each letter satisfies the requirement
        if (flag == true) {
            return end - start;
        }
        int longest = 0;
        int i = start;
        for (int j = start; j < end; j++) {
            if (count[s.charAt(j) - 'a'] < k) {
                longest = Math.max(longest, helper(s, k, i, j));
                i = j + 1;
            }
        }
        longest = Math.max(longest, helper(s, k, i, end));
        return longest;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/

/*******************************************************************/
// Solution 2: O(n^2), slow but new with bit operation
class Solution {
    public int longestSubstring(String s, int k) {
        int max = 0;
        int i = 0;
        while (i <= s.length() - k) {
            int[] count = new int[26]; // lowercase letters only
            int mask = 0; // bit mask
            int maxStart = i;
            for (int j = i; j < s.length(); j++) {
                int idx = s.charAt(j) - 'a';
                count[idx]++;
                if (count[idx] < k) {
                    mask |= (1 << idx);
                } else {
                    mask &= (~(1 << idx));
                }
                if (mask == 0) {
                    max = Math.max(max, j - i + 1);
                    maxStart = j;
                }
            }
            i = maxStart + 1;
        }
        return max;
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(n)
*/

/***************************************************************/
// Solution 3: two pointers,  O(n)
// not quiet agree yet, 
// https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/87739/Java-Strict-O(N)-Two-Pointer-Solution
