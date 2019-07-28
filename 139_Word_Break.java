/*
# Dynamic Programming (brute force)

1. The most intuitive way to solve the problem is to iterate over each partition.
This way, we get all the results to get the last one. 

2. A similar way is to do BFS by using a Queue to track all valid end indices.

3. In both ways, we can optimize the time complexity in the inner loop. Instead of
traversing j from i + 1 to the end of the string, we can only check from i + 1 to
i + maximumLengthOfWordsInWordDict. To get the maximum length, we keep a len variable
when adding words into a HashSet.  

*/

// Solution 1: brute force

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.contains(s)) {
            return true;
        }
        Set<String> wordSet = new HashSet<>();
        int len = 0;
        for (String word : wordDict) {
            wordSet.add(word);
            len = Math.max(len, word.length());
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // wordSet does contain substring(0, 0)
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= len; j++) {
                if (i - j >= 0 && dp[i - j] && wordSet.contains(s.substring(i - j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

/*
Time complexity: O()
Space complexity: O()
*/

/***********************************************************/
// Solution 2:
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.contains(s)) {
            return true;
        }
        Set<String> wordSet = new HashSet<>();
        int len = 0;
        for (String word : wordDict) {
            wordSet.add(word);
            len = Math.max(len, word.length());
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(0); // indices in q are valid ends of words
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 1; i <= len && cur + i <= s.length(); i++) {
                if (!visited.contains(cur + i) && wordSet.contains(s.substring(cur, cur + i))) {
                    q.offer(cur + i);
                    visited.add(cur + i);
                    if (cur + i == s.length()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/