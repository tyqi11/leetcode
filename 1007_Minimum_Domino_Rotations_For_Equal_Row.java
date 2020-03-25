/*
Find the differences and the same elements to check if they can make a row.
There is only one possibility when countA[i] + countB[i] - same[i] == n.
*/

class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] same = new int[7];
        
        int n = A.length;
        
        for (int i = 0; i < n; i++) {
            countA[A[i]]++;
            countB[B[i]]++;
            
            if (A[i] == B[i]) {
                same[A[i]]++;
            }
        }
        
        for (int i = 1; i <= 6; i++) {
            if (countA[i] + countB[i] - same[i] == n) {
                return n - Math.max(countA[i], countB[i]);
            }
        }
        
        return -1;
    }
}


/*
Time complexity: O(n)
Space complexity: O(1), O(3 * 7) = O(1)
*/
