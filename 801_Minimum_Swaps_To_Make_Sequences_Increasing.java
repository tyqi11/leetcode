/*
# Dynamic Programming
Going from left to right, at each step, we are pursuing the same result. This
leads us to think about dynamic programming solutions.

@wangzi6147
https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119835/Java-O(n)-DP-Solution
*/

class Solution {
    public int minSwap(int[] A, int[] B) {
        int swap = 1, noSwap = 0;
        for (int i = 1; i < A.length; i++) {
            // MUST NOT swap, swap results in A[i - 1] >= A[i] || B[i - 1] >= B[i]
            // to keep the pattern, if pre swap, current swap, if pre noSwap, current noSwap
            if (A[i - 1] >= B[i] || B[i - 1] >= A[i]) {
                swap++;
            // MUST swap, noSwap results in failure
            } else if (A[i - 1] >= A[i] || B[i - 1] >= B[i]) {
                int temp = swap;
                swap = noSwap + 1;
                noSwap = temp;
            // other situations, fine for swap or not, change based on previous min
            } else {
                int min = Math.min(swap, noSwap);
                swap = min + 1;
                noSwap = min;
            }
        }
        
        return Math.min(swap, noSwap);
    }
}




/*
Time complexity: O(n)
Space complexity: O(1)
*/
