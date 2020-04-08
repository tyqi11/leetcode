/*
# Piece Table
https://en.wikipedia.org/wiki/Piece_table

*/

class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, Integer> table = new HashMap<>();
        
        for (int i = 0; i < indexes.length; i++) {
            if (S.startsWith(sources[i], indexes[i])) {
                table.put(indexes[i], i);
            } // <pos_in_S, index_in_sources_and_targets>
        }
        
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < S.length()) {
            if (table.containsKey(i)) {
                // table.get(i) is the index of the sources and targets
                sb.append(targets[table.get(i)]);
                i += sources[table.get(i)].length();
            } else {
                sb.append(S.charAt(i++));
            }
        }
        
        return sb.toString();
    }
}

/*
Time complexity: O(n), although with startsWith, as no overlapping, it takes O(n)
Space complexity: O(n)
*/