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
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() > 12) {
            return res;
        }
        dfs(res, s, new StringBuilder(), 0, 0);
        return res;
    }
    
    private void dfs(List<String> res, String s, StringBuilder sb, int idx, int part) {
        if (idx == s.length() && part == 4) {
            res.add(sb.toString());
            return;
        } else if (idx == s.length() || part == 4) {
            return;
        }
        
        for (int i = 1; i <= 3 && i + idx <= s.length(); i++) { // length of current part
            int num = Integer.valueOf(s.substring(idx, idx + i)); // each part < 255
            if (i == 1 || (i == 2 && num >= 10) || (i == 3 && num >= 100 && num <= 255)) {
                sb.append(num);
                if (part < 3) {
                    sb.append('.');
                }
                dfs(res, s, sb, idx + i, part + 1);
                if (part < 3) {
                    sb.deleteCharAt(sb.length() - 1); // IMP, delete '.'
                }
                sb.delete(sb.length() - i, sb.length()); // IMP, delete chars
            }
        }     
    }
}

/*
Time complexity: O()
Space complexity: O()
*/
