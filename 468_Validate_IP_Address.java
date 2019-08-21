/*

Take care of all situations.

*/

class Solution {
    public String validIPAddress(String IP) {
        int dot = IP.indexOf('.');
        int colon = IP.indexOf(':');
        if (dot != -1 && colon != -1) {
            return "Neither";
        } else if (dot != -1) {
            return check4(IP);
        } else {
            return check6(IP);
        }
    }
    
    private String check4(String ip) {
        // 1. check number of dots
        int cnt = 0;
        for (char c : ip.toCharArray()) {
            if (c == '.') {
                cnt++;
            }
        }
        if (cnt != 3) {
            return "Neither";
        }
        
        // 2. check number of parts
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return "Neither";
        }
        
        // 3. check each part
        for (String s : parts) {
            // 3.1 length
            if (s.length() == 0 || s.length() > 3) {
                return "Neither";
            }
            // 3.2 all digits
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return "Neither";
                }
            }
            // 3.3 [0, 255], and no leading 0
            int num = Integer.valueOf(s);
            if (!String.valueOf(num).equals(s) || num < 0 || num > 255) {
                return "Neither";
            }
        }
        return "IPv4";
    }
    
    private String check6(String ip) {
        // 1. check number of colons
        int cnt = 0;
        for (char c : ip.toCharArray()) {
            if (c == ':') {
                cnt++;
            }
        }
        if (cnt != 7) {
            return "Neither";
        }
        
        // 2. check number of parts
        ip = ip.toUpperCase();
        String[] parts = ip.split(":");
        if (parts.length != 8) {
            return "Neither";
        }
        
        // 3. each part
        for (String s : parts) {
            // 3.1 length
            if (s.length() == 0 || s.length() > 4) {
                return "Neither";
            }
            
            // 3.2 0 ~ 9 or A ~ F (toUpperCase() first)
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i)) && (s.charAt(i) < 'A' || s.charAt(i) > 'F')) {
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/