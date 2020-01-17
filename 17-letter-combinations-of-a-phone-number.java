class Solution {   
    String[] buttons = new String[]{"","", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};       

    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        
        dfs(digits, 0, new StringBuilder(), res);
        return res;
    }
    
    private void dfs(String digits, int p, StringBuilder sb, List<String> res) {
        if (p == digits.length()) {
            res.add(sb.toString());
            return;
        }
        
        int len = sb.length();
        int n = digits.charAt(p) - '0';
        for (char c : buttons[n].toCharArray()) {
            dfs(digits, p + 1, sb.append(c), res);
            sb.setLength(len);
        }
    }
}

/*
Time complexity: O(), exponential
Space complexity: O(n), n is the length of the input string
*/

