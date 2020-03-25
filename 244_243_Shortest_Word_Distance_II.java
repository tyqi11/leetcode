/*
# Two Pointers

*/

class WordDistance {

    Map<String, List<Integer>> map; // <word, indices as a list>
    
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.putIfAbsent(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        
        int i = 0, j = 0;
        int min = Integer.MAX_VALUE;
        while (i < l1.size() && j < l2.size()) {
            int cur = Math.abs(l1.get(i) - l2.get(j));
            min = Math.min(min, cur);
            if (l1.get(i) < l2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        
        return min;
    }
}


/*
Time complexity: construction: O(n), searching: O(n)
Space complexity: O(n)
*/
