/*

1. This problem is similar to 205 Isomorphic String. In 205, we need a map from
character to character, while in 290, we map from character to a string.

*/

class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(words[i])) {
                    return false;
                }
            } else {
                if (set.contains(words[i])) {
                    return false;
                }
                map.put(c, words[i]);
                set.add(words[i]);
            }
        }
        return true;          
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/