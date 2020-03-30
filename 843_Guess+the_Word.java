/*
This is an interactive problem.
Solution by @lee215
https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison

For one secret word with six character, the chance we guess one with no correct 
character is (25/26)^6 = 79%. To guess more efficiently, we guess the word which matches
with most other words.

*/
// Solutoin 1: guess the one with least 0 matches
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0; i < 10; i++) { // 10 chances to guess
            HashMap<String, Integer> count = new HashMap<>(); // <word, 0-match-number>
            for (String w1 : wordlist) {
                for (String w2 : wordlist) {
                    if (countMatch(w1, w2) == 0) {
                        count.put(w1, count.getOrDefault(w1, 0) + 1);
                    }
                }
            }
            
            String guess = "";
            int min = Integer.MAX_VALUE;
            for (String w : wordlist) {
                if (count.getOrDefault(w, 0) == 0) {
                    guess = w;
                    break;
                }
                if (count.get(w) < min) {
                    min = count.get(w);
                    guess = w;
                }
            }
            
            int res = master.guess(guess);
            List<String> list = new ArrayList<>();
            for (String w : wordlist) {
                if (countMatch(guess, w) == res) {
                    list.add(w);
                }
            }
            
            wordlist = list.toArray(new String[list.size()]);
        }
    }
    
    private int countMatch(String s, String t) {
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            if (s.charAt(i) == t.charAt(i)) {
                cnt++;
            }
        }
        
        return cnt;
    }
}



/*
Time complexity: O()
Space complexity: O()
*/

/************************************************************************/
// Solution 2: guess the one with most matches
/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        for (int chance = 0; chance < 10; chance++) { // 10 chances to guess
            int[][] count = new int[6][26]; // [pos][char]
            for (String w : wordlist) {
                for (int i = 0; i < 6; i++) {
                    count[i][w.charAt(i) - 'a']++;
                }
            }
            String guess = wordlist[0];
            int max = 0;
            for (String w : wordlist) {
                int match = 0;
                for (int i = 0; i < 6; i++) {
                    match += count[i][w.charAt(i) - 'a'];
                }
                if (match > max) {
                    max = match;
                    guess = w;
                }
            }
            
            // the rest is the same. including helper function: countMatch
            int res = master.guess(guess);
            List<String> list = new ArrayList<>();
            for (String w : wordlist) {
                if (countMatch(guess, w) == res) {
                    list.add(w);
                }
            }
            
            wordlist = list.toArray(new String[list.size()]); 
        }
    }
    
    private int countMatch(String s, String t) {
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            if (s.charAt(i) == t.charAt(i)) {
                cnt++;
            }
        }
        
        return cnt;
    }
} 