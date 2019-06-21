/*

1. Comparing to the previous question, there are two changes: the 2D array is 
sorted and it has no overlapping. So we need to find where is the start of the
overlapping between one inverval and the newInterval, and where is the end.
We record the place where we need to add the overlapping interval. Before and 
after that, we simply get and add the interval from the array.

*/


class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> list = new ArrayList<>();
        int pos = 0; // record the position to add newInterval
        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) {
                list.add(interval);
                pos++; // before overlapping
            } else if (newInterval[1] < interval[0]) {
                list.add(interval); // after overlapping
            } else { // during overlapping
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        list.add(pos, newInterval);
        return list.toArray(new int[list.size()][2]);
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/