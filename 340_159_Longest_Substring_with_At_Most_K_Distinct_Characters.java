class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int distinct = 0;
        int ans = 0;
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (count[c] == 0) {
                distinct++;
            }
            count[c]++;
            while (distinct > k && i <= j) {
                count[s.charAt(i)]--;
                if (count[s.charAt(i)] == 0) {
                    distinct--;
                }
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}