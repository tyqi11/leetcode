/*
You should never treat a "easy" like it is easy. For this problem, the critical
part is when to append the extra dash. The dash should be appended right before
we find a new part but the last part if full. So this code block should be before
sb.append(c).

*/

class Solution {
    public String licenseKeyFormatting(String S, int K) {        
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (c == '-') {
                continue;
            }
            if (c >= 'a' && c <= 'z') {
                c += 'A' - 'a';
            }
            if (j == K) {
                sb.append('-');
                j = 0;
            }
            sb.append(c);
            j++;
        }
        
        return sb.reverse().toString();
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/