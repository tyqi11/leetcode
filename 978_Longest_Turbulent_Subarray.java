/*

1. The first solution is intuitive, but it changes the value of 
input array. The second one use only two pointers and is very
smart.

*/

class Solution {
    public int maxTurbulenceSize(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        } else if (A.length == 1) {
            return 1;
        }
        
        int max = 0;
        int cur = 0;
        for (int i = 0; i < A.length - 1; i++) {
            // mark trend using -1, 1, 0
            A[i] = Integer.compare(A[i], A[i + 1]);
            
            // count the trend
            if (i == 0 || A[i] == 0 || A[i] == A[i - 1]) {
                cur = A[i] == 0 ? 0 : 1;
            } else {
                cur++;
            }
            max = Math.max(max, cur + 1);
        }  
        
        return max;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/******************************************************/
// Solution 2
class Solution {
    public int maxTurbulenceSize(int[] A) {
        int inc = 1; // increasing length
        int dec = 1; // decreasing length
        int res = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i]) { // increase
                inc = dec + 1;
                dec = 1;
            } else if (A[i - 1] > A[i]) { // decrease
                dec = inc + 1;
                inc = 1;
            } else {
                inc = 1;
                dec = 1;
            }
            
            res = Math.max(res, Math.max(inc, dec));
        }
        
        return res;
    }
}