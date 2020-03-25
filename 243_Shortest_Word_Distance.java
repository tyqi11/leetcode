/*
# Two pointers

*/

class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int first = -1, second = -1;
        
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (second != -1) {
                    minDist = Math.min(minDist, i - second);
                }
                first = i;
            } else if (words[i].equals(word2)) {
                if (first != -1) {
                    minDist = Math.min(minDist, i - first);
                }
                second = i;
            }
        }
        
        return minDist;
    }
}


/*
Time complexity: O(n)
Space complexity: O(1)
*/

/*************************************************************************/
// Also two pointers, but in a different way.
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int idx = -1;
        
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (idx != -1 && !words[i].equals(words[idx])) {
                    minDist = Math.min(minDist, i - idx);
                }
                idx = i;
            }
        }
        
        return minDist;
    }
}