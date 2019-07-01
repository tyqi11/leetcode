/*

1. Special notice to the follow up, unicode characters so much that it is unnecessary
to save a place for each of them. So when we need to consider unicode characters,
we use hashmap.

*/

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26]; 
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/**************************************************************/
/* 
Follow Up:
What if the inputs contain unicode characters? How would you adapt your solution 
to such case?

Answer: 
Use a hash table instead of a fixed size counter. Imagine allocating a large size 
array to fit the entire range of unicode characters, which could go up to more 
than 1 million. A hash table is a more generic solution and could adapt to any 
range of characters.

*/