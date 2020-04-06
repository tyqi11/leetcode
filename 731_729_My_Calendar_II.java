/*
# TreeMap, like in 729 My Calendar I.
# TreeMap, like in Meeting Rooms.
*/
// Solution 1:
// Why we initialize an empty overlaps list for a new book request?
// Because previous overlaps are useless for us.
// 1. If there was triple overlaps, that book failed and was not added to booking.
// 2. For the current booking, it compares with all the previous bookings, so we 
// will never miss a triple overlap.
class MyCalendarTwo {
    List<int[]> bookings;

    public MyCalendarTwo() {
        bookings = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        List<int[]> overlaps = new ArrayList<>();
        for (int[] b : bookings) {
            int s = Math.max(b[0], start), e = Math.min(b[1], end);
            if (s < e) { // found overlap
                for (int[] o : overlaps) {
                    // if overlaps with previous overlaps
                    if (Math.max(o[0], s) < Math.min(o[1], e)) {
                        return false;
                    }
                }
                overlaps.add(new int[]{s, e});
            }
        }
        bookings.add(new int[]{start, end});
        return true;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/

/******************************************************************************/
// Solution 2: like in Meeting Rooms. (A general way to solve scheduling problems)
// Slow because iterating over all the values each time.
class MyCalendarTwo {
    TreeMap<Integer, Integer> calendar;

    public MyCalendarTwo() {
        calendar = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);
        
        int cnt = 0, k = 0;
        for (int v : calendar.values()) {
            cnt += v;
            k = Math.max(k, cnt);
            if (k > 2) { // cannot be booked, cancel previous commands
                calendar.put(start, calendar.get(start) - 1);
                calendar.put(end, calendar.get(end) + 1);
                return false;
            }
        }
        
        return true;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/