/*
# Bidirectional BFS
This is similar to 127 Word Ladder. 127 gives a dictionary and we must follow the route
given by the dictionary. Here in 752, we must bypass the route given by the deadends.
These are opposite conditions.

*/

class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        
        if (dead.contains("0000") || dead.contains(target)) {
            return -1;
        }
        
        begin.add("0000");
        end.add(target);
        
        int turns = 0;
        Set<String> temp;
        while (!begin.isEmpty() && !end.isEmpty()) {
            if (begin.size() > end.size()) {
                temp = begin;
                begin = end;
                end = temp;
            }
            
            temp = new HashSet<>();
            for (String code : begin) {
                if (end.contains(code)) {
                    return turns;
                }
                dead.add(code); // avoid visiting this code again
                char[] chars = code.toCharArray();
                for (int i = 0; i < 4; i++) {
                    char c = chars[i];
                    String s1 = code.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + code.substring(i + 1);
                    String s2 = code.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + code.substring(i + 1);
                    
                    if (!dead.contains(s1)) {
                        temp.add(s1);
                    }
                    
                    if (!dead.contains(s2)) {
                        temp.add(s2);
                    }
                }
            }
            turns++; // finish turning one slot
            begin = temp;
        } // end while
        
        return -1;
    }
}
 


/*
Time complexity: one-direction BFS: O(8^5), bidirectional: (8^(5/2) + 8^(5/2))
Space complexity: O()
*/
