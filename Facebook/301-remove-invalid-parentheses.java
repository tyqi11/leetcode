// runtime: 1ms. 100%
// memory usage: beat 100%

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s, ans, 0, 0, '(', ')');
        return ans;
    }
    
    private void dfs(String s, List<String> ans, int lastI, int lastJ, char open, char close) {
        int count = 0;
        int i = lastI;
        while (i < s.length() && count >= 0) {
            if (s.charAt(i) == open) {
                count++;
            } else if (s.charAt(i) == close) {
                count--;
            }
            i++;
        } // exit when 1) go through all chars, or 2) count = - 1
        
        // if go through all chars, no extra ')', reverse to check extra '('
        if (count >= 0) {
            String reversed = new StringBuilder(s).reverse().toString();
            if (open == '(') {
                dfs(reversed, ans, 0, 0, close, open);
            } else { // the second time to reverse, already the answer
                ans.add(reversed);
            }
        }
        
        if (count == -1) {
            i -= 1; // i - 1 is the first extra parenthese
            for (int j = lastJ; j <= i; j++) {
                if (s.charAt(j) == close && (j == lastJ || s.charAt(j - 1) != close)) {
                    dfs(s.substring(0, j) + s.substring(j + 1), ans, i, j, open, close);
                }
            }
        }
        
    }
}