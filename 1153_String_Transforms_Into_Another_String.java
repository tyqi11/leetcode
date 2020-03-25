/*
# Graph
# The out degree of a node is <= 1
# https://leetcode.com/problems/string-transforms-into-another-string/discuss/399352/Complete-Logical-Thinking-(This-is-why-only-check-if-str2-has-unused-character)
*/

class Solution {
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if (map.containsKey(c1) && map.get(c1) != c2) {
                return false;
            }
            map.put(c1, c2);
        }
        
        HashSet<Character> set = new HashSet<>(map.values());
        return set.size() < 26;
    }
}


/*
Time complexity: O(m + n), m is the length of str1, n is the length of str2
Space complexity: O(1), O(26) = O(1)
*/

