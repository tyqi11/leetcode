/*

1. To solve this problem, it does not need special tricks. We can just count and
append to StringBuilder and count again. One thing to improve the speed is to
change StringBuilder to String only when we get the final result.

2. Make sure i starts from 2 in the for loop, as "1" is the start string and is
counted as 1 already.

*/

class Solution {
    public String countAndSay(int n) {
        StringBuilder sb = new StringBuilder("1");
        for (int i = 2; i <= n; i++) {
            StringBuilder temp = new StringBuilder();
            int start = 0;
            int end = 0;
            while (end < sb.length()) {
                while (end < sb.length() && sb.charAt(end) == sb.charAt(start)) {
                    end++;
                } // same numbers
                temp.append(end - start);
                temp.append(sb.charAt(start));
                start = end;
            }
            sb = temp;
        }
        return sb.toString();
    }
}

/*
Time complexity: sum of the lengths of n strings
Space complexity: depends on the longest string
*/