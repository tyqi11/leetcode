/*
# Two pointers

0. Similar idea to 821. Shortest Distance to a Character. We go from left to
right and then right to left.

1. One key point in the solution is: only when up[i] > 0 and down[i] > 0, we
count the max length. Because the numbers may be always ascending or descending
like [0, 1, 2, 3, 4], then max = 0, because the going down part is missing.

2. To optimize the space complexity, we can count up and down at the same time.
Clear previous results only when we are going down and find a going-up number.
This is the end of a mountain. Update result only when there are going up and 
down at the same time.


*/

class Solution {
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        
        int n = A.length;
        int[] up = new int[n];
        int[] down = new int[n];
        int max = 1;
        
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                up[i] = up[i - 1] + 1;
            }
        }
        
        for (int i = n - 2;i >=0; i--) {
            if (A[i] > A[i + 1]) {
                down[i] = down[i + 1] + 1;
            }
            if (up[i] > 0 && down[i] > 0) {
                max = Math.max(max, up[i] + down[i] + 1);
            } 
        }
        
        return max >= 3 ? max : 0;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), for up array and down array
*/

// Optimized to O(1) space complexity
class Solution {
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        
        int up = 0;
        int down = 0;
        int max = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] == A[i] || (A[i - 1] < A[i] && down > 0)) {
                up = 0;
                down = 0;
            } // end of a previous mountain
            
            if (A[i - 1] < A[i]) {
                up++;
            } else if (A[i - 1] > A[i]) {
                down++;
            }
            
            if (up > 0 && down > 0) {
                max = Math.max(max, up + down + 1);
            }
        }
        
        return max;
    }
}