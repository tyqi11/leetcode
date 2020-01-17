/*
# DFS (backtracking)

1. In each part of the IP address, take care that there should be no leading
0s. So it is two digits, num >= 10, and if it is three digits, 100 <= num <= 255.

2. When do backtracking, don't forget to remove the dot between numbers.


IPv4 addresses are canonically represented in dot-decimal notation, which 
consists of four decimal numbers, each ranging from 0 to 255, separated by dots 
("."), e.g.,172.16.254.1;
Besides, leading zeros in the IPv4 is invalid. For example, the address 
172.16.254.01 is invalid.

*/

class Solution {
    List<String> res = new LinkedList<>();
    
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }
        backtrack(s, new StringBuilder(), 0, 1);
        return res;
    }
    
    private void backtrack(String s, StringBuilder sb, int p, int part) {
        if (part == 5 && p == s.length()) {
            res.add(sb.toString());
            return;
        } else if (part == 5 || p == s.length()) {
            return;
        }
        
        int len = sb.length();
        for (int i = 1; i <= 3 && p + i <= s.length(); i++) {
            int num = Integer.parseInt(s.substring(p, p + i));
            if (i == 2 && num < 10) {
                continue;
            }
            if (i == 3 && (num < 100 || num > 255)) {
                continue;
            }
            sb.append(num);
            if (part < 4) {
                sb.append('.');
            }
            backtrack(s, sb, p + i, part + 1);
            sb.setLength(len);
        }
    }
}

/*
Time complexity: O()
Space complexity: O()
*/
