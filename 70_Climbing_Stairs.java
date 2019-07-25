/*
# Dynamic Programming

1. There are two ways to arrive at stair i: go two steps from i - 2 or go one step
from i - 1. So we need to keep two previous results and update at each step until n.

*/

class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int two = 1;
        int one = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = one;
            one = two + one;
            two = temp;
        }
        return one;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

