/*

1. The first key point is to sort the 2D array according to the first element
of each 1D subarray. THIS IS AN IMPORTANT IMPLEMENTATION TO MASTER. 

2. Then we add the first interval and compare its ending with the next start.
If there is overlapping: cur[1] >= interval[0], the new ending should be the
larger number of these two intervals. Do this until an ending is smaller than
the next start. Add the interval which has no overlapping with previous ones,
and iterate the same way.

3. list.toArray(new int[list.size()][2]) is the way to change the list of int[]
to a 2D array.

*/

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return intervals;
        }   
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        List<int[]> list = new ArrayList<>();
        int[] cur = intervals[0];
        for (int[] in : intervals) {
            if (in[0] <= cur[1]) {
                cur[1] = Math.max(cur[1], in[1]);
            } else {
                list.add(cur);
                cur = in;
            }
        }
        list.add(cur);
        
        return list.toArray(new int[list.size()][2]);
    }
}

/*
Time complexity: O(nlogn)
Space complexity: O(n)
*/