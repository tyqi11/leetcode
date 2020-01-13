/*
# Dynamic Programming

*/

class Solution {
    public int rotatedDigits(int N) {
        int count = 0;
        int[] dp = new int[N + 1]; // default 0: invalid
        for (int n = 0; n <= N; n++) {
            if (n < 10) {
                if (n == 0 || n == 1 || n == 8) {
                    dp[n] = 1; // valid and same
                } else if (n == 2 || n == 5 || n == 6 || n == 9) {
                    dp[n] = 2; // valid and different
                    count++;
                }
            } else {
                int last = dp[n % 10];
                int rest = dp[n / 10];
                if (last == 1 && rest == 1) {
                    dp[n] = 1;
                } else if (last >= 1 && rest >= 1) {
                    dp[n] = 2;
                    count++;
                }
            }
        }
        
        return count;
    }
}


/*
Time complexity: O(m * n)
Space complexity: O(1)
*/