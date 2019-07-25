/*
# Greedy

1. "The problem is the same as 'Given a collection of intervals, find the maximum 
number of intervals that are non-overlapping.' "

2. We sort the intervals by their end points, because we want as many non-overlapping 
intervals as possible, so we want intervals to end earlier than later.

*/

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        }); // sort by end points, O(nlogn)
        
        int count = 1;
        int end = intervals[0][1]; // first end point
        for (int i = 1; i < intervals.length; i++) { // start from second
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }
        return intervals.length - count;
    }
}


/*
Time complexity: O(nlogn), for Arrays.sort()
Space complexity: O(1), if in-place sort, O(n) if copy the intervals array
*/