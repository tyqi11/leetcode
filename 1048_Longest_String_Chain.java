/*

*/

class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        
        Map<String, Integer> map = new HashMap<>();
        int max = 1;
        for (String w : words) {
            int cnt = 1;
            for (int i = 0; i < w.length(); i++) {
                String s = w.substring(0, i) + w.substring(i + 1);
                cnt = Math.max(cnt, map.getOrDefault(s, 0) + 1);
            }
            map.put(w, cnt);
            max = Math.max(max, cnt);
        }
        
        return max;
    }
}


/*
Time complexity: O(nlogn + N), n is the length of words, N is the sum of characters
Space complexity: O(n), in any case, we will eventually put each word into the map
*/
