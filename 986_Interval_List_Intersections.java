/*
# Two Pointers

Move one pointer each time and compare start and end to determine if an interval
exists.

*/

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return new int[0][0];
        }
        List<int[]> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        int start, end;
        while (i < A.length && j < B.length) {
            start = Math.max(A[i][0], B[j][0]);
            end = Math.min(A[i][1], B[j][1]);
            if (start <= end) {
                list.add(new int[]{start, end});
            } 
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}

/*
Time complexity: O()
Space complexity: O()
*/