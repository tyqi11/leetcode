class Solution {
    public int longestArithSeqLength(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int maxLen = 0;
        int n = A.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for (int j = 0; j < n; j++) {
            dp[j] = new HashMap<Integer, Integer>();
            for (int i = 0; i < j; i++) {
                int diff = A[j] - A[i];
                dp[j].put(diff, dp[i].getOrDefault(diff, 1) + 1);
                maxLen = Math.max(maxLen, dp[j].get(diff));
            }
            
        }
        
        return maxLen;
    }
}