// hard to think of, but easy to understand and implement
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }
        int len = intervals.length;
        int[] start = new int[len];
        int[] end = new int[len];
        for (int i = 0; i < len; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        int rooms = 0;
        int endIndex = 0;
        for (int i = 0; i < len; i++) {
            if (start[i] < end[endIndex]) {
                rooms++;
            } else {
                endIndex++;
            }
        }
        return rooms;
    }
}

// easy to think of, but takes longer time
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }
        List<Integer> rooms = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                } else {
                    return Integer.compare(a[0], b[0]);
                }
            }
        });
        
        rooms.add(0);
        for (int[] interval : intervals) {
            int i = 0;
            for (i = 0; i < rooms.size(); i++) {
                if (interval[0] >= rooms.get(i)) {
                    rooms.set(i, interval[1]);
                    break;
                }
            }
            if (i == rooms.size()) {
                rooms.add(interval[1]);
            }
        }
        
        return rooms.size();
    }
}