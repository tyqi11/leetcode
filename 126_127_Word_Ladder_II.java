/*
# BFS + DFS

0. The basic logic behind is the same as in 127. Word Ladder.

1. First we iteratively search in beginSet to get a Set of words with one character 
different from words in beginSet(nextSet). Instead of counting step, we maintain
a map of current word and all its "next words". As we are doing bidirectional
search, we need to use a flip boolean to indicate direction and add into map accordingly.
When we the two ends meet in the middle, we change terminate = true and finish
searching after all the words in beginSet in this round. Why? Because once two ends
meet, this is the shortest path. But we cannot stop searching now, as there might
be other paths with the same length. We end searching after this round.

2. After BFS, we get a map of words and their "next words". Now we iterate over
beginSet and search for next words using DFS. Be sure to do the backtracking 
temp.remove(temp.size() - 1) to avoid unnecessary words in the result.


*/

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return res;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        HashMap<String, List<String>> map = new HashMap<>(); // <word, nextWords>
        bfs(wordSet, beginSet, endSet, map);
        
    //    List<String> temp = new ArrayList<>(beginSet); // why same as next lines
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        dfs(res, temp, beginWord, endWord, map);
        
        return res;
    }

    private void bfs(Set<String> wordSet, Set<String> beginSet, Set<String> endSet, Map<String, List<String>> map) {
        boolean flip = false;
        boolean terminate = false;
        
        while (!beginSet.isEmpty()) {
            wordSet.removeAll(beginSet);
            wordSet.removeAll(endSet);
            
            if (beginSet.size() > endSet.size()) {
                flip = !flip;
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            
            Set<String> nextSet = new HashSet<>();
            for (String word : beginSet) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {                  
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String str = String.valueOf(chars);
                        
                        String key = flip ? str : word; // if not flipped, word as key, str as val
                        String val = flip ? word : str;
                            
                        if (endSet.contains(str)) {
                            map.putIfAbsent(key, new ArrayList<String>());
                            map.get(key).add(val);
                            terminate = true;
                        } else if (wordSet.contains(str)) {
                            map.putIfAbsent(key, new ArrayList<String>());
                            map.get(key).add(val);
                            nextSet.add(str);
                        }
                    }
                    chars[i] = old;
                }
            }
            beginSet = nextSet;
            if (terminate == true) {
                return;
            }
        }
    }
    
    private void dfs(List<List<String>> res, List<String> temp, String beginWord, String endWord, Map<String, List<String>> map) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (!map.containsKey(beginWord)) {
            return;
        }
        for (String word : map.get(beginWord)) {
            temp.add(word);
            dfs(res, temp, word, endWord, map);
            temp.remove(temp.size() - 1);
        }
    }
}

/*
Time complexity: O()
Space complexity: O()
*/
