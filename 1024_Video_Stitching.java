/*
# Dynamic Programming

1. We first sort the array and arrange them with the ascending order of begin time.

2. To avoid one extra count++ after the loop, we initiate count as 1. Before the loop,
we have finished the editing from 0 to 0. This is important! We cannot initialize
start point as clips[0][0] as it may start from the middle, which means we can 
never get a complete video.

3. Now we iterate all the clips. If the new start time is later than old end time,
there will be a gap between old end and new start. So we return -1. If new start
time == old start time, we just need to update the end time. BUT, if new start
time > old start time, there might be a gap between two start times. So we count
one more. And also, the new start time comes to the old end time. This means that,
with previous clips, we reach old end time already. So we only need to care about
latter moments.  

4. After adding a new clip, if the end time exceeds the T time we need, we can return,
When we leave the loop, if we never reaches the end >= T statement, it shows that
all clips are not enough to satisfy the requirements, so we return -1.

*/

class Solution {
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        int count = 1;
        int start = 0;
        int end = 0;
        for (int[] clip : clips) {
            if (clip[0] > end) {
                return -1;
            }
            if (clip[0] > start) {
                count++;
                start = end;  /// IMP
            }
            end = Math.max(end, clip[1]);
            if (end >= T) {
                return count;
            }
        }
        return -1;
        
    }
}

/*
Time complexity: O(n), n is number of clips
Space complexity: O(1)
*/