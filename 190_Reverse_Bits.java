/*

1. A typical bit operation problem. Get the Least Significant Bit and add it 
to the result.

*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 1; i <= 32; i++) {
            int cur = n & 1;
            res <<= 1;
            if (cur == 1) {
                res |= cur; // faster than res += 1;
            }
            n >>= 1;
            if (n == 0) { 
                res <<= (32 - i);
                return res;
            } // not necessary, but to make it end faster
        }
        return res;
    }
}

/*
Time complexity: O(1), O(32)
Space complexity: O(1)
*/