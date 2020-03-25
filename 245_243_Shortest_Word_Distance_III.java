/*
Change from 243 Shortest Word Distance I, and add the situation when word1.equals(word2).
*/

class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        boolean sameWord = false;
        if (word1.equals(word2)) {
            sameWord = true;
        }
        
        int idx = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (idx != -1 && (sameWord || !words[i].equals(words[idx]))) {
                    minDist = Math.min(minDist, i - idx);
                }
                idx = i;
            }
        }
        
        return minDist;
    }
}


/*
Time complexity: O(n)
Space complexity: O(1)
*/
