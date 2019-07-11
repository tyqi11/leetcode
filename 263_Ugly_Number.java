/*

1. Divide all the 2, 3, 5 factors and check what's left.

*/

class Solution {
    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/