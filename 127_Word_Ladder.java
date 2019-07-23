/*
# BFS (bidirectional)
@kleklokin
"The idea behind bidirectional search is to run two simultaneous searches — 
one forward from the initial state and the other backward from the goal — 
hoping that the two searches meet in the middle. The motivation is that 
b^(d/2) + b^(d/2) is much less than b^d. b is branch factor, d is depth. "

							----- section 3.4.6 in Artificial Intelligence 
					 - A modern approach by Stuart Russel and Peter Norvig

1. We use BFS but in a bidirectional way. We create two sets, one from beginWord, 
one from endWord. Always search from the smaller set until two sets meet
in the middle.

2. Assume beginSet is the smaller one. We iterate over each word in the set,
and each character in the word. Replace the character with 'a' to 'z' to check
if it is in the wordList. Thus we create a new nextSet with words one char
different from words in beginSet. Then beginSet = nextSet. 

3. When after replacing, one word is in the endSet, return the step till now.

*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Set<String> wordSet = new HashSet<>(wordList); // unused words
        Set<String> beginSet = new HashSet<>(); 
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        wordSet.remove(beginWord);
        wordSet.remove(endWord);
        
        int step = 1; // beginWord is counted in the final sequence
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            } // bidirectional search, always search in the smaller set

            step++; 
            Set<String> nextSet = new HashSet<>(); // one char different
            for (String word : beginSet) { // for each word in beginSet
                char[] chars = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) { // for each char in word
                        chars[i] = c;
                        String str = String.valueOf(chars);
                        if (endSet.contains(str)) {
                            return step;
                        } // beginSet and endSet meets
                        if (wordSet.contains(str)) {
                            nextSet.add(str);
                            wordSet.remove(str);
                        }
                    } // end replacing current char
                    chars[i] = old; // change current char back
                }
            }
            beginSet = nextSet; 
        }

        return 0;
    }
}

/*
Time complexity: O(n * len), n is the number of words, len is average length
Space complexity: O(n)
*/
