/*
# Dynamic Programming
Detailed solution by @Kjer:
https://leetcode.com/problems/numbers-with-repeated-digits/discuss/258212/Share-my-O(logN)-C%2B%2B-DP-solution-with-proof-and-explanation

As most numbers contain repeated digits, we should count the ones without repeating digits.
*/

class Solution {
    public int numDupDigitsAtMostN(int N) {
        if (N < 10) {
            return 0;
        }
        
        int k = 0; // digits of N
        for (int i = N; i > 0; i /= 10) {
            k++;
        }
        
        int[] digits = new int[k];
        for (int i = 0, j = N; i < k; i++, j /= 10) {
            digits[k - 1 - i] = j % 10;
        } // save N as digits in an array
        
        // step1: from 1 to 999..999, one digit less than N
        // e.g. N = 12345, here we deal with 1 - 9999
        int baseSum = 0;
        int[] base = new int[k - 1];  // one digit fewer
        for (int i = 0; i < k - 1; i++) {
            base[i] = (i == 0) ? 9 : base[i - 1] * (10 - i);
            baseSum += base[i];
        } // [9, 81, 81 * 8, 648 * 7, ...]
        
        // step2: 
        int[] count = new int[10]; // count how many times 0~9 appears
        int[] rest = new int[k]; 
        boolean repeated = false;
        for (int i = 0; i < k; i++) {
            rest[i] = (i == 0) ? 9 : rest[i - 1] * (10 - i);
            
            // if already repeated, same as previous
            // else, 
            if (!repeated) {
                int diff = 0;
                for (int j = digits[i] + 1; j < 10; j++) {
                    diff += (count[j] == 0) ? 1 : 0;
                }
                rest[i] -= diff;
                count[digits[i]]++;
                if (count[digits[i]] > 1) {
                    repeated = true;
                }
            }
        }
        
        return N - (baseSum + rest[k - 1]);
    }
}



/*
Time complexity: O(logN), I do not understand.
Space complexity: O(logN), do not understand, either.
*/
