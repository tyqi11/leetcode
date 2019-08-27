/*

1. https://leetcode.com/problems/number-of-digit-one/discuss/64382/JavaPython-one-pass-solution-easy-to-understand

2. https://leetcode.com/problems/number-of-digit-one/discuss/64390/AC-short-Java-solution

*/

class Solution {
    public int countDigitOne(int n) {
        int count = 0;
        
        int q = n, x = 1, ans = 0;
        while (q != 0) {
            int digit = q % 10;
            q = q / 10;
            ans += q * x;
            if (digit == 1) {
                ans += n % x + 1;
            } else if (digit > 1) {
                ans += x;
            }
            x *= 10;
        }
        return ans;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/

/**********************************************************/
// Solution 2
// why k has to be long?
// why count(int) += a(long) + b(long) is fine, but 
//     q(int) = n(int) / k(long) is wrong?
class Solution {
    public int countDigitOne(int n) {
        int count = 0;
        
        for (long k = 1; k <= n; k *= 10) {
            long q = n / k;
            long r = n % k;
            
            count += (q + 8) / 10 * k + (q % 10 == 1 ? r + 1 : 0);
        }
        return count;
    }
}