/*

1. When n < 0, it is not wise to make it -n as it may cause overflow. The integer
range is [-2^31, 2^31 - 1].

*/

class Solution {
    public double myPow(double x, int n) {
        if (n == 0 && x != 0) {
            return 1;
        }
        double res = 1;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) { // odd number
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/