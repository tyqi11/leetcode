class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<StringBuilder> strs = new ArrayDeque<>();
        strs.offerLast(new StringBuilder());
        
        int n = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                n = 10 * n + (c - '0');
            } else if (c == '[') {
                num.offerLast(n);
                n = 0;
                str.offerLast(new StringBuilder());
            } else if (c == ']') {
                StringBuilder temp = str.pollLast();
                int count = num.pollLast();
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    sb.append(temp);
                }
                str.peekLast().append(sb);
            } else {
                str.peekLast().append(c);
            }
        }
        return str.pollLast().toString();

    }
}