/*

find the pattern of steps, which is 1, 1, 2, 2, 3, 3, ...
you add the steps every two times, that is when you go east or west
for each position, you check if it is within the range of the array, and add 
to the result if it is

*/

class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        List<int[]> list = new ArrayList<>();
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int len = 0; // <len> steps
        int d = 0; // direction in dirs
        
        list.add(new int[]{r0, c0});
        while (list.size() < R * C) {
            if (d == 0 || d == 2) { // when go east or west
                len++;
            }  
            for (int i = 0; i < len; i++) {
                r0 += dirs[d][0];
                c0 += dirs[d][1];
                if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) {
                    list.add(new int[]{r0, c0});
                }
            }
            d = (d + 1) % 4;
        }
        return list.toArray(new int[list.size()][2]);
    }
}

/*
Time complexity: O(R * C)
Space complexity: O(R *C)
*/