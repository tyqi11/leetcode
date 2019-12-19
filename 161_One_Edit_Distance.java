class Solution {
    public boolean isOneEditDistance(String s, String t) {        
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(i).equals(t.substring(i + 1))
                    || s.substring(i + 1).equals(t.substring(i))
                    || s.substring(i + 1).equals(t.substring(i + 1));
            }
        }
        
        return s.length() + 1 == t.length() || (t.length() + 1 == s.length());
    }
}
