/*
# Backtracking (with memorization)

*/

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();        
        
        // 1. check validity, same as LC 139. Word Break
        if (!canBreak(s, set, wordDict)) {
            return new ArrayList<String>();
        }
        
        // 2. get the list of strings
        return dfs(s, set);
    }
    
    private boolean canBreak(String s, Set<String> set, List<String> wordDict) {
        int len = 0;
        for (String word : wordDict) {
            set.add(word);
            len = Math.max(len, word.length());
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= len; j++) {
                if (i - j >= 0 && dp[i - j] && set.contains(s.substring(i - j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    Map<String, List<String>> map = new HashMap<>();

    private List<String> dfs(String s, Set<String> set) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        for (String word : set) {
            if (s.startsWith(word)) {
                List<String> subList = dfs(s.substring(word.length()), set);
                for (String sub : subList) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}

/*
Time complexity: O(n^3)
Space complexity: O()
*/