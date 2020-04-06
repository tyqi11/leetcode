/*
A very good summary of scheduling problems:
@@zzhai: https://leetcode.com/problems/meeting-rooms-ii/discuss/203658/HashMapTreeMap-resolves-Scheduling-Problem
*/

class MyCalendarThree {
    Map<Integer, Integer> calendar;
    
    public MyCalendarThree() {
        calendar = new TreeMap<>();    
    }
    
    public int book(int start, int end) {
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);
        
        int cnt = 0, k = 0;
        for (int v : calendar.values()) {
            cnt += v;
            k = Math.max(cnt, k);
            
        }
        
        return k;
    }
}

/*
Time complexity: O(n), O(logn) to put into TreeMap, and O(n) to find the max
Space complexity: O(n), for storing all previous bookings
*/