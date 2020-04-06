/*
# TreeMap
(TreeSet is the same usage, saving the booking as an int array or subclass.)

*/
// Solution 1: TreeMap
class MyCalendar {
    TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer floorKey = calendar.floorKey(start);
        if (floorKey != null && calendar.get(floorKey) > start) {
            return false;
        } // start in the middle of the previous booking
        Integer ceilingKey = calendar.ceilingKey(start);
        if (ceilingKey != null && ceilingKey < end) {
            return false;
        } // end in the middle of the next booking
        calendar.put(start, end); // O(logn)
        return true;
    }
}



/*
Time complexity: O()
Space complexity: O()
*/