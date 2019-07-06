/*

1. This is a follow up of 76. Maximum Window Substring. In 76, there are two strings. 
In this problem, we match words instead of characters, and only matching words
are allowed in the result.

2. We have similar intuitions. Explanations are in the comment.

*/

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }
        // 1. Put all words in *words* array in a HashMap to track appearances
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int len = words[0].length(); // length of each word in words

        // 2. Iterate over each start point, by offset from beginning
        for (int offset = 0; offset < len; offset++) {
            int count = 0;
            Map<String, Integer> curMap = new HashMap<>();
            // i is the start word index, j is the last word index 
            for (int i = offset, j = offset; j <= s.length() - len; j += len) {
                String word = s.substring(j, j + len); // new word on the right
                String temp = "";
                // 2.1 if this is a word from *words*
                if (map.containsKey(word)) {
                	// 2.1.1 put into curMap
                    curMap.put(word, curMap.getOrDefault(word, 0) + 1);
                    // 2.1.2 if this is a word we need, show in count variable
                    if (curMap.get(word) <= map.get(word)) { 
                        count++;
                    } 
                    // 2.1.3 if we have more current word than we need, cut from left
                    while (curMap.get(word) > map.get(word)) {
                        temp = s.substring(i, i + len); // leftmost word
                        curMap.put(temp, curMap.get(temp) - 1);
                        i += len;
                        if (curMap.get(temp) < map.get(temp)) {
                            count--;
                        } // this leftmost one may not be the current word, which makes us
                          // lack of a required word again
                    }
                    // 2.1.4 when we find a result
                    if (count == words.length) { 
                        res.add(i); // add it to the final result list
                        temp = s.substring(i, i + len);
                        curMap.put(temp, curMap.get(temp) - 1);
                        i += len; // move the start point one word right
                        count--;
                    }
                } else {
                // 2.2 if this is not a word from *words*, reset the start to one word right
                    curMap.clear();
                    count = 0;
                    i = j + len;
                }
            } // finish this offset
        }        
        return res;
    }
}

/*
Time complexity: O(kn), k is the length of each word in words array
Space complexity: O()
*/