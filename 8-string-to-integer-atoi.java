class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        int len = str.length();
        // 1. trim leading white spaces
        int i = 0;
        while (i < len && str.charAt(i) == ' ') {
            i++;
        } // exit when 1) not ' ', 2) reach end
        
        if (i == len) {
            return 0;
        }
        
        // 2. check if first is Digit or '+' or '-'
        char c = str.charAt(i);
        int sign = 1;
        if (c == '+' || c == '-') {
            sign = (c == '+') ? 1 : -1;
            i++;
        }
        
        // 3. go through numbers, record digits to avoid overflow
        int base = 0;
        while (i < len && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = 10 * base + (str.charAt(i++) - '0');
        }
    
        return base * sign;
    }
}