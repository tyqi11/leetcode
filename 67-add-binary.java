// runtime: 1ms, 100%
// memory usage: beat 100%

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int sum, digitA, digitB, carry = 0;
        
        while (i >= 0 || j >= 0) {
            digitA = (i >= 0) ? a.charAt(i--) - '0' : 0;
            digitB = (j >= 0) ? b.charAt(j--) - '0' : 0;

            sum = carry + digitA + digitB;
            ans.append(sum % 2);
            carry = sum / 2;
        }
        
        if (carry == 1) {
            ans.append('1');
        }
        
        return ans.reverse().toString();
    }
}