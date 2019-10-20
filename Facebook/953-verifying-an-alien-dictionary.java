// runtime: 0ms, beat 100%
// memory usage: beat 100%

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];        
        for (int i = 0; i < 26; i++) {
            map[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            if (!isSorted(first, second, map)) {
                return false;
            }
        } 
        return true;
    }
    
    private boolean isSorted(String first, String second, int[] map) {
        int cur = 0;
        int len = Math.min(first.length(), second.length());
        while (cur < len) {
            char c1 = first.charAt(cur);
            char c2 = second.charAt(cur);
            if (map[c1 - 'a'] < map[c2 - 'a']) {
                return true;
            } else if (map[c1 - 'a'] > map[c2 - 'a']) {
                return false;
            }
            cur++;
        } // exit when finish searching the shorter string 
        if (first.length() == len) {
            return true;
        } else {
            return false;
        }
    }
}

/*
Time complexity: O(1) + O(N), N is the sum of characters in all words
Space complexity: O(1), for the map array
*/