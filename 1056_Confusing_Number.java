/*

*/

class Solution {
    public boolean confusingNumber(int N) {
        int rotated = 0;
        int original = N;
        
        while (N > 0) {
            int digit = N % 10;
            if (digit == 2 || digit == 3 || digit == 4 || digit == 5 || digit == 7) {
                return false;
            } else if (digit == 6) {
                digit = 9;
            } else if (digit == 9) {
                digit = 6;
            }
            rotated  = 10 * rotated + digit;
            N = N / 10;
        }
        
        return rotated != original;
    }
}


/*
Time complexity: O(m), m is the number of digits of N
Space complexity: O(1)
*/
