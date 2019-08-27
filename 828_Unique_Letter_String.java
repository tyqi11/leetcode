/*
@lee215, 
https://leetcode.com/problems/unique-letter-string/discuss/128952/One-pass-O(N)-Straight-Forward
               01  4  7 9
1. In a String xAxxAxxAxx, 
1.1. the first A is a unique character in xA, xAx, xAxx, A, Ax, Axx, that is to separate
like [x[A]x]x]A, two places before and three places after, (1 - (-1)) * (4 - 1) = 6.

1.2 the second A is a unique characer in (4 - 1) * (7 - 4) = 9 substrings.

1.3 the third A is a unique character in (7 - 4) * (10 - 7) = 9 substrings.


*/

class Solution {
    public int uniqueLetterString(String S) {
        int[][] index = new int[26][2];
        for (int[] in : index) {
            Arrays.fill(in, -1);
        }
        int res = 0;
        int n = S.length();
        int mod = (int)Math.pow(10, 9) + 7;
        
        // all but last unique character
        for (int i = 0; i < n; i++) {
            int c = S.charAt(i) - 'A';
            res = (res + (i - index[c][1]) * (index[c][1] - index[c][0])) % mod;
            index[c][0] = index[c][1];
            index[c][1] = i;
        }
        
        // for the last unique character
        for (int c = 0; c < 26; c++) {
            res = (res + (n - index[c][1]) * (index[c][1] - index[c][0])) % mod;
        }
        return res;
    }
}


/*
Time complexity: O(n), n = S.length()
Space complexity: O(1), O(52)
*/