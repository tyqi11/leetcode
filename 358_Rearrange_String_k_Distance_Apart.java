class Solution {
    public String rearrangeString(String s, int k) {
        int[] count = new int[26];
        int[] nextIndex = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int charIndex = getNextChar(count, nextIndex, i);
            if (charIndex == -1) {
                return "";
            }
            sb.append((char) ('a' + charIndex));
            nextIndex[charIndex] = i + k;
            count[charIndex]--;            
        }
        return sb.toString();
    }
    
    private int getNextChar(int[] count, int[] nextIndex, int pos) {
        int max = 0;
        int res = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] > max && nextIndex[i] <= pos) {
                res = i;
                max = count[i];
            }
        }
        return res;
    }
}