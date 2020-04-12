/*
Split the array until the current index into two parts:
****************@@@@@@@@@@@ lmax in the first part + current sum of m
first part    +    M length
or 
******************@@@@@@@@@ mMax in the first part + current sum of l 
first part    +    L length
the max of these two is the biggest until now.

In the code, sum[i - M] - sum[i - L - M] is the new L length we have IN THE FIRST PART.
Compare it with previous lMax gives us the biggest lMax in the first part.
*/

class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] sum = new int[A.length];
        sum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sum[i] = sum[i - 1] + A[i];
        }
        
        int res = sum[L + M - 1]; // first L + M elements, one possible res
        int lMax = sum[L - 1], mMax = sum[M - 1];
        for (int i = L + M; i < A.length; i++) {
            lMax = Math.max(lMax, sum[i - M] - sum[i - L - M]);
            mMax = Math.max(mMax, sum[i - L] - sum[i - L - M]);
            res = Math.max(res, Math.max(lMax + sum[i] - sum[i - M], mMax + sum[i] - sum[i - L]));
        }
        
        return res;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), for the prefixSum array
*/