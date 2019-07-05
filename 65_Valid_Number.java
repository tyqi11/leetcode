/*

1. The key to solve this problem is to know that we can only validate a numer from the 
order of the parts we can see. Here we use boolean variables to indicate the orders.

2. This may not be a good problem for interview but it shows us a way to think about problems.

*/

class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                numberSeen = true;
            } else if (c == '.') {
                if (eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if (c == 'e') {
                if (eSeen || !numberSeen) {
                    return false;
                }
                eSeen = true;
                numberSeen = false;
            } else if (c == '-' || c == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return numberSeen;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/